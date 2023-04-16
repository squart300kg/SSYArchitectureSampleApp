package com.example.kakao.ui.ui.search

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.fragment.app.viewModels
import com.example.kakao.R
import com.example.kakao.databinding.SearchResultFragmentBinding
import com.example.kakao.ui.adapter.ImageAdapter
import com.example.kakao.ui.adapter.ImageAdapterType
import com.example.kakao.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchResultFragment : BaseFragment<SearchResultFragmentBinding>(R.layout.search_result_fragment) {

    private val viewModel: SearchResultViewModel by viewModels()
    private val imageAdapter by lazy { ImageAdapter(
        imageAdapterType = ImageAdapterType.SEARCH_RESULT,
        onSaveImage = (viewModel::saveImageToLocal),
        onDeleteImage = (viewModel::deleteImageToLocal)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            searchVm = viewModel
            imageAdt = imageAdapter

            initSearchClickListener()

            rvSearchResult.apply {
                setHasFixedSize(true)
                adapter = imageAdapter
            }
        }
    }

    private fun SearchResultFragmentBinding.initSearchClickListener() {
        btnSearch.setOnClickListener {
            viewModel.search("${etSearch.text}")
        }

        binding.etSearch.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                viewModel.search("${etSearch.text}")
                return@setOnKeyListener true
            }
            false
        }
    }
}