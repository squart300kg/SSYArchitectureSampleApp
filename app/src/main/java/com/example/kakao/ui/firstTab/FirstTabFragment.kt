package com.example.kakao.ui.firstTab

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.kakao.R
import com.example.kakao.base.BaseFragment
import com.example.kakao.databinding.FirstTabFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstTabFragment : BaseFragment<FirstTabFragmentBinding>(R.layout.first_tab_fragment) {

    private val firstTabViewModel: FirstTabViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("bottomId", "First Loaded")

        binding {

        }

    }

}