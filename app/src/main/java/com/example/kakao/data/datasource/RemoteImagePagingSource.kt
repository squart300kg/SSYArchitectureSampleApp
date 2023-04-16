package com.example.kakao.data.datasource

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kakao.R
import com.example.kakao.data.api.KakaoApi
import com.example.kakao.data.model.SortType
import com.example.kakao.data.model.response.Documents
import com.example.kakao.ui.model.SearchResultItem
import com.example.kakao.util.convertFormatTo
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import kotlin.math.max

const val MAX_PAGE_COUNT_FOR_IMAGE_API = 50
const val MAX_PAGE_COUNT_FOR_VIDEO_API = 15
val END_PAGING_COUNT = max(MAX_PAGE_COUNT_FOR_IMAGE_API, MAX_PAGE_COUNT_FOR_VIDEO_API)

const val MAX_DOCUMENT_SIZE_FOR_IMAGE_API = 80
const val MAX_DOCUMENT_SIZE_FOR_VIDEO_API = 30

class RemoteImagePagingSource @Inject constructor(
    private val kakaoApi: KakaoApi,
    private val context: Context,
    private val keyWord: String,
): PagingSource<Int, SearchResultItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchResultItem> {
        try {
            val nextPage = params.key ?: 1

            val uiStatesForImageApi = mutableListOf<SearchResultItem>()
            if (nextPage in 1..MAX_PAGE_COUNT_FOR_IMAGE_API) {
                kakaoApi.fetchImages(
                    keyWord = keyWord,
                    sortType = SortType.RECENCY.value,
                    page = nextPage,
                    size = MAX_DOCUMENT_SIZE_FOR_IMAGE_API
                ).also { imageApiResponse ->
                    imageApiResponse.documents convertUiStatesTo uiStatesForImageApi
                }
            }

            val uiStatesForVideoApi = mutableListOf<SearchResultItem>()
            if (nextPage in 1..MAX_PAGE_COUNT_FOR_VIDEO_API) {
                kakaoApi.fetchVideos(
                    keyWord = keyWord,
                    sortType = SortType.RECENCY.value,
                    page = nextPage,
                    size = MAX_DOCUMENT_SIZE_FOR_VIDEO_API
                ).also { videoApiResponse ->
                    videoApiResponse.documents convertUiStatesTo uiStatesForImageApi
                }
            }

            val resultUiState = (uiStatesForImageApi + uiStatesForVideoApi)
                .distinctBy(SearchResultItem::thumbnailUrl)
                .sortedWith(compareByDescending(SearchResultItem::date)
                    .thenByDescending(SearchResultItem::time))

            return LoadResult.Page(
                data = resultUiState,
                prevKey = null,
                nextKey = if (nextPage > END_PAGING_COUNT) null
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

    private infix fun List<Documents>.convertUiStatesTo(uiStates: MutableList<SearchResultItem>) {
        forEach { document ->
            uiStates.add(
                SearchResultItem(
                    thumbnailUrl = document.thumbnail,
                    date = document.dateTime convertFormatTo context.resources.getString(
                        R.string.searchResultDateFormat),
                    time = document.dateTime convertFormatTo context.resources.getString(R.string.searchResultTimeFormat),
                    isFavorite = false
                )
            )
        }
    }

}