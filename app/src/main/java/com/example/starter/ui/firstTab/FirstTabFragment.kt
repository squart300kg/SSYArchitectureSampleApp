package com.example.starter.ui.firstTab

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.starter.R
import com.example.starter.base.BaseFragment
import com.example.starter.databinding.FirstTabFragmentBinding

class FirstTabFragment : BaseFragment<FirstTabFragmentBinding>(R.layout.first_tab_fragment) {

    private val firstTabViewModel: FirstTabViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("bottomId", "First Loaded")

    }

}