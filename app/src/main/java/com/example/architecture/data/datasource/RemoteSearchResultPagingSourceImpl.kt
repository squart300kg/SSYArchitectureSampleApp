package com.example.architecture.data.datasource

import android.content.Context
import androidx.paging.PagingState
import com.example.architecture.R
import com.example.architecture.data.api.BaseApi
import com.example.architecture.data.model.SortType
import com.example.architecture.data.model.response.VideoResponseModel
import com.example.architecture.ui.model.SearchResultItem
import com.example.architecture.util.convertFormatTo
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

const val MAX_PAGE_COUNT_FOR_VIDEO_API = 15
const val MAX_DOCUMENT_SIZE_FOR_VIDEO_API = 30

class RemoteSearchResultPagingSourceImpl @Inject constructor(
    private val baseApi: BaseApi,
    @ApplicationContext private val context: Context,
): RemoteSearchResultPagingSource() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchResultItem> {
        try {
            val nextPage = params.key ?: 1

            val searchResultForVideoApi = mutableListOf<SearchResultItem>()
            if (nextPage in 1..MAX_PAGE_COUNT_FOR_VIDEO_API) {
                baseApi.fetchVideos(
                    keyWord = keyWord,
                    sortType = SortType.RECENCY.value,
                    page = nextPage,
                    size = MAX_DOCUMENT_SIZE_FOR_VIDEO_API
                ).also { videoApiResponse ->
                    videoApiResponse.documents convertUiStatesTo searchResultForVideoApi
                }
            }

            val resultUiState = searchResultForVideoApi
                .distinctBy(SearchResultItem::thumbnailUrl)
                .sortedWith(compareByDescending(SearchResultItem::date)
                    .thenByDescending(SearchResultItem::time))

            return LoadResult.Page(
                data = resultUiState,
                prevKey = null,
                nextKey = if (nextPage > MAX_PAGE_COUNT_FOR_VIDEO_API) null
                          else nextPage + 1
            )

        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SearchResultItem>) = null

    private infix fun List<VideoResponseModel.VideoDocument>.convertUiStatesTo(uiStates: MutableList<SearchResultItem>) {
        forEach { document ->
            uiStates.add(
                SearchResultItem(
                    thumbnailUrl = document.thumbnail,
                    date = document.dateTime convertFormatTo context.resources.getString(
                        R.string.searchResultDateFormat),
                    time = document.dateTime convertFormatTo context.resources.getString(R.string.searchResultTimeFormat),
                    title = document.title,
                    playTime = document.playTime,
                    author = document.author,
                    isFavorite = false
                )
            )
        }
    }
}