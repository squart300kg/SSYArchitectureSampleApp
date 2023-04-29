package com.example.architecture.data.datasource

import android.content.SharedPreferences
import com.example.architecture.data.di.LOCAL_IMAGE_ITEMS
import com.example.architecture.ui.model.SearchResultItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LocalSearchResultDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
): LocalSearchResultDataSource {

    override fun updateSearchResult(searchResultItem: SearchResultItem) {
        val searchResultModels = fetchSearchResultModels().toMutableSet()

        val updatedImages = if (searchResultItem.copy(isFavorite = true) in searchResultModels) {
            searchResultModels.apply { remove(searchResultItem.copy(isFavorite = true)) }
        } else {
            searchResultModels.apply { add(searchResultItem) }
        }

        sharedPreferences.edit().putString(LOCAL_IMAGE_ITEMS, Gson().toJson(updatedImages)).apply()
    }

    override val searchResultModels: Flow<List<SearchResultItem>>
        = flow { emit(fetchSearchResultModels()) }

    private fun fetchSearchResultModels(): List<SearchResultItem> {
        val itemImageUiStatesJsonString = sharedPreferences.getString(LOCAL_IMAGE_ITEMS, null)
        val itemImageUiStatesModels: List<SearchResultItem>? = Gson().fromJson(itemImageUiStatesJsonString,
            object : TypeToken<List<SearchResultItem>>() {}.type
        )
        return if (itemImageUiStatesModels.isNullOrEmpty()) emptyList() else itemImageUiStatesModels
    }
}