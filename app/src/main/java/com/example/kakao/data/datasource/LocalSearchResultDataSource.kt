package com.example.kakao.data.datasource

import android.content.SharedPreferences
import com.example.kakao.data.di.LOCAL_IMAGE_ITEMS
import com.example.kakao.ui.model.SearchResultItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LocalSearchResultDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun updateSearchResultToLocal(searchResultItem: SearchResultItem) {
        val searchResultModels = fetchSearchResultModels().toMutableSet()

        val updatedImages = if (searchResultItem in searchResultModels) {
            searchResultModels.apply { add(searchResultItem.copy()) }
        } else {
            searchResultModels.apply { add(searchResultItem.copy(isFavorite = true)) }
        }

        sharedPreferences.edit().putString(LOCAL_IMAGE_ITEMS, Gson().toJson(updatedImages)).apply()
    }

    val searchResultModels: Flow<List<SearchResultItem>>
        = flowOf(fetchSearchResultModels())

    private fun fetchSearchResultModels(): List<SearchResultItem> {
        val itemImageUiStatesJsonString = sharedPreferences.getString(LOCAL_IMAGE_ITEMS, null)
        val itemImageUiStatesModels: List<SearchResultItem> = Gson().fromJson(itemImageUiStatesJsonString,
            object : TypeToken<List<SearchResultItem>>() {}.type
        )
        return itemImageUiStatesModels
    }
}