package com.example.kakao.uilayer.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.kakao.BuildConfig
import com.example.kakao.R
import com.example.kakao.uilayer.base.BaseFragment
import com.example.kakao.databinding.SearchResultFragmentBinding
import com.example.kakao.uilayer.adapter.ImageAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultFragment : BaseFragment<SearchResultFragmentBinding>(R.layout.search_result_fragment) {

    private val viewModel: SearchResultViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            searchVm = viewModel

            Log.i("apiKey", BuildConfig.KakaoRestApiKey)

            btnSearch.setOnClickListener {
                viewModel.search("${etSearch.text}")
            }

            rvSearchResultRv.apply {
                setHasFixedSize(true)
                adapter = ImageAdapter()
            }
        }

    }

}