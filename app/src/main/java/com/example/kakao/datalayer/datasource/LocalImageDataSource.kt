package com.example.kakao.datalayer.datasource

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources.NotFoundException
import com.example.kakao.R
import com.example.kakao.di.LOCAL_IMAGE_ITEMS
import com.example.kakao.uilayer.model.ItemImageUiState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalImageDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val sharedPreferences: SharedPreferences
) {

    fun saveImage(imageUiState: ItemImageUiState): Flow<Unit> {
        return fetchImages().map { localImages ->
            if (localImages.contains(imageUiState).not()) {
                val updatedImages = localImages.toMutableList().apply { add(imageUiState) }
                sharedPreferences.edit().putString(LOCAL_IMAGE_ITEMS, Gson().toJson(updatedImages)).apply()
            } else {
                throw CloneNotSupportedException(context.resources.getString(R.string.ErrorDuplicatedLocalImageSelect))
            }
        }
    }

    fun deleteImage(imageUiState: ItemImageUiState): Flow<Unit> {
        return fetchImages().map { images ->
            if (images.contains(imageUiState)) {
                val updatedImages = images.toMutableList().apply { remove(imageUiState) }
                sharedPreferences.edit().putString(LOCAL_IMAGE_ITEMS, Gson().toJson(updatedImages)).apply()
            } else {
                throw NotFoundException(context.resources.getString(R.string.ErrorNotFoundImageSelect))
            }
        }
    }

    fun fetchImages(): Flow<List<ItemImageUiState>> {
        val itemImageUiStatesJsonString = sharedPreferences.getString(LOCAL_IMAGE_ITEMS, null)
        return flow {
            if (itemImageUiStatesJsonString != null) {
                emit(Gson().fromJson(
                    itemImageUiStatesJsonString,
                    object : TypeToken<List<ItemImageUiState>>() {}.type
                ))
            } else {
                emit(emptyList())
            }
        }
    }

}