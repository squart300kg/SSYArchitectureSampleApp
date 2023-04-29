package com.example.kakao.ui.ui.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.fragment.app.viewModels
import com.example.kakao.R
import com.example.kakao.databinding.SearchResultFragmentBinding
import com.example.kakao.ui.adapter.SearchResultAdapter
import com.example.kakao.ui.adapter.SearchResultAdapterType
import com.example.kakao.ui.base.BaseFragment
import com.example.kakao.ui.ext.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultFragment : BaseFragment<SearchResultFragmentBinding>(R.layout.search_result_fragment) {

    private val viewModel: SearchResultViewModel by viewModels()
    private val searchResultAdapter by lazy { SearchResultAdapter(
        searchResultAdapterType = SearchResultAdapterType.SEARCH_RESULT,
        onUpdateSearchResultModelToLocal = (viewModel::updateSearchResultToLocal),
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            searchVm = viewModel
            searchResultAdt = searchResultAdapter

            initSearchClickListener()

            rvSearchResult.apply {
                setHasFixedSize(true)
                adapter = searchResultAdapter
            }
        }
    }

    private fun SearchResultFragmentBinding.initSearchClickListener() {
        btnSearch.setOnClickListener {
            viewModel.search("${etSearch.text}")
            requireActivity().hideKeyboard()
        }

        binding.etSearch.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                viewModel.search("${etSearch.text}")
                requireActivity().hideKeyboard()
                return@setOnKeyListener true
            }
            false
        }
    }
}