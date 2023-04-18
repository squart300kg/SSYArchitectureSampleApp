package com.example.kakao.data.datasource

import android.content.SharedPreferences
import android.util.Log
import com.example.kakao.data.model.response.ModifySuccessModel
import com.example.kakao.data.di.LOCAL_IMAGE_ITEMS
import com.example.kakao.ui.model.SearchResultItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LocalImageDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    suspend fun saveImage(imageUiState: SearchResultItem) {
        fetchImages().collectLatest { localImages ->
            val localImages = localImages.toMutableSet()
            val updatedImages = localImages.apply { add(imageUiState.copy(isFavorite = true)) }
            sharedPreferences.edit().putString(LOCAL_IMAGE_ITEMS, Gson().toJson(updatedImages)).apply()
        }
    }

    suspend fun deleteImage(imageUiState: SearchResultItem) {
        fetchImages().collectLatest { localImages ->
            val localImages = localImages.toMutableSet()
            val updatedImages = localImages.apply { remove(imageUiState) }
            sharedPreferences.edit().putString(LOCAL_IMAGE_ITEMS, Gson().toJson(updatedImages)).apply()
        }
    }

    fun fetchImages(): Flow<List<SearchResultItem>> {
        val itemImageUiStatesJsonString = sharedPreferences.getString(LOCAL_IMAGE_ITEMS, null)
        val itemImageUiStatesModels: List<SearchResultItem> = Gson().fromJson(itemImageUiStatesJsonString,
            object : TypeToken<List<SearchResultItem>>() {}.type
        )

        return callbackFlow {
            trySend(itemImageUiStatesModels)

            val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, _ ->
                Log.i("localImageTest", itemImageUiStatesModels.toString())
                trySend(itemImageUiStatesModels)
            }
            sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
            awaitClose { sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener) }
        }
    }
}