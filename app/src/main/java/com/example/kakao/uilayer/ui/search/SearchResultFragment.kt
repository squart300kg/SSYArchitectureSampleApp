package com.example.kakao.uilayer.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.kakao.R
import com.example.kakao.uilayer.base.BaseFragment
import com.example.kakao.databinding.SearchResultFragmentBinding
import com.example.kakao.uilayer.adapter.ImageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchResultFragment : BaseFragment<SearchResultFragmentBinding>(R.layout.search_result_fragment) {

    private val viewModel: SearchResultViewModel by viewModels()
    private val imageAdapter by lazy { ImageAdapter(
        onSaveImage = (viewModel::saveImageToLocal),
        onDeleteImage = (viewModel::deleteImageToLocal)
    )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            btnSearch.setOnClickListener {
                viewModel.search("${etSearch.text}")
            }

            rvSearchResultRv.apply {
                setHasFixedSize(true)
                adapter = imageAdapter
            }
        }

        // TODO: 데이터바인딩 + 바인딩어댑터?
        //  ㄴ> 만약, 상태유지시, 로딩과 에러메시지 Base로 빼는법 고민
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.searchResultUiState.collect { searchResultUiState ->
                        imageAdapter.submitList(searchResultUiState.items)
                    }
                }

                launch {
                    viewModel.isLoading.collect { isLoading ->
                        binding.loadingBar.isVisible = isLoading
                    }
                }

                launch {
                    viewModel.errorMessage.collect { errorMessage ->
                        errorMessage?.let { message ->
                            Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()

                        }
                    }
                }
            }
        }
    }
}