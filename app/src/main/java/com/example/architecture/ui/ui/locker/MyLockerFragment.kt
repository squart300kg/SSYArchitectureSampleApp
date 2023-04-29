package com.example.architecture.ui.ui.locker

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.architecture.R
import com.example.architecture.databinding.MyLockerFragmentBinding
import com.example.architecture.ui.adapter.SearchResultAdapter
import com.example.architecture.ui.adapter.SearchResultAdapterType
import com.example.architecture.ui.base.BaseFragment
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