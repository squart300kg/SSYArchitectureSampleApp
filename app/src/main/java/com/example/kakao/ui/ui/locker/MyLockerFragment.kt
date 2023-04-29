package com.example.kakao.ui.ui.locker

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.kakao.R
import com.example.kakao.databinding.MyLockerFragmentBinding
import com.example.kakao.ui.adapter.SearchResultAdapter
import com.example.kakao.ui.adapter.SearchResultAdapterType
import com.example.kakao.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyLockerFragment : BaseFragment<MyLockerFragmentBinding>(R.layout.my_locker_fragment) {

    private val viewModel: MyLockerViewModel by viewModels()
    private val searchResultAdapter by lazy { SearchResultAdapter(
        searchResultAdapterType = SearchResultAdapterType.MY_LOCKER
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