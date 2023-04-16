package com.example.kakao.data.datasource

import android.content.SharedPreferences
import com.example.kakao.data.model.response.ModifySuccessModel
import com.example.kakao.data.di.LOCAL_IMAGE_ITEMS
import com.example.kakao.ui.model.SearchResultItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

class LocalImageDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun saveImage(imageUiState: SearchResultItem): Flow<ModifySuccessModel> {
        return fetchImages().map { localImages ->
            val localImages = localImages.toMutableSet()
            val updatedImages = localImages.apply { add(imageUiState.copy(isFavorite = true)) }
            sharedPreferences.edit().putString(LOCAL_IMAGE_ITEMS, Gson().toJson(updatedImages)).apply()
            ModifySuccessModel.SAVE_SUCCESS
        }
    }

    fun deleteImage(imageUiState: SearchResultItem): Flow<ModifySuccessModel> {
        return fetchImages().map { localImages ->
            val localImages = localImages.toMutableSet()
            val updatedImages = localImages.apply { remove(imageUiState) }
            sharedPreferences.edit().putString(LOCAL_IMAGE_ITEMS, Gson().toJson(updatedImages)).apply()
            ModifySuccessModel.DELETE_SUCCESS
        }
    }

    private val mutex = Mutex()
    fun fetchImages(): Flow<List<SearchResultItem>> {
            return flow {
                mutex.withLock {
                    val itemImageUiStatesJsonString = sharedPreferences.getString(
                        LOCAL_IMAGE_ITEMS, null)
                    if (itemImageUiStatesJsonString != null) {
                        emit(Gson().fromJson(itemImageUiStatesJsonString,
                            object : TypeToken<List<SearchResultItem>>() {}.type
                        ))
                    } else {
                        emit(emptyList())
                    }
                }
        }
    }

}