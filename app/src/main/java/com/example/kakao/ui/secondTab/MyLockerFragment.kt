package com.example.kakao.ui.secondTab

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.kakao.R
import com.example.kakao.base.BaseFragment
import com.example.kakao.databinding.SecondTabFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyLockerFragment : BaseFragment<SecondTabFragmentBinding>(R.layout.second_tab_fragment) {

    private val myLockerViewModel: MyLockerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("bottomId", "Second Loaded")

    }

}