package com.example.kakao.uilayer.ui.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import com.example.kakao.R
import com.example.kakao.databinding.SearchResultFragmentBinding
import com.example.kakao.uilayer.adapter.ImageAdapter
import com.example.kakao.uilayer.adapter.ImageAdapterType
import com.example.kakao.uilayer.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
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