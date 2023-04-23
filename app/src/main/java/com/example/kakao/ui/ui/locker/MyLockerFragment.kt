package com.example.kakao.ui.ui.locker

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import com.example.kakao.R
import com.example.kakao.databinding.MyLockerFragmentBinding
import com.example.kakao.ui.adapter.SearchResultAdapter
import com.example.kakao.ui.adapter.ImageAdapterType
import com.example.kakao.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyLockerFragment : BaseFragment<MyLockerFragmentBinding>(R.layout.my_locker_fragment) {

    private val viewModel: MyLockerViewModel by viewModels()
    private val searchResultAdapter by lazy { SearchResultAdapter(
        imageAdapterType = ImageAdapterType.MY_LOCKER
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            lockerVm = viewModel
            searchResultAdt = searchResultAdapter

            rvMyLocker.apply {
                setHasFixedSize(true)
                adapter = searchResultAdapter
            }
        }
    }

}