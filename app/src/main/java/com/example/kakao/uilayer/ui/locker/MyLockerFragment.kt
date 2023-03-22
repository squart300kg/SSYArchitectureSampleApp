package com.example.kakao.uilayer.ui.locker

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.kakao.R
import com.example.kakao.databinding.MyLockerFragmentBinding
import com.example.kakao.uilayer.adapter.ImageAdapter
import com.example.kakao.uilayer.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyLockerFragment : BaseFragment<MyLockerFragmentBinding>(R.layout.my_locker_fragment) {

    private val viewModel: MyLockerViewModel by viewModels()
    private val imageAdapter by lazy { ImageAdapter(
        onDeleteImage = (viewModel::deleteImageToLocal)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            rvMyLocker.apply {
                setHasFixedSize(true)
                adapter = imageAdapter
            }
        }

        // TODO: 데이터바인딩 + 바인딩어댑터?
        //  ㄴ> 만약, 상태유지시, 로딩과 에러메시지 Base로 빼는법 고민
        //  ㄴ> 만약, 상태유지시, STARTED or RESUME 고민
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.uiState.collect { uiState ->
                        imageAdapter.submitList(uiState.items)
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
                            Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

}