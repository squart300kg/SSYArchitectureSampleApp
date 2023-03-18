package com.example.starter.ui.secondTab

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.starter.R
import com.example.starter.base.BaseFragment
import com.example.starter.databinding.SecondTabFragmentBinding

class SecondTabFragment : BaseFragment<SecondTabFragmentBinding>(R.layout.second_tab_fragment) {

    private val secondTabViewModel: SecondTabViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("bottomId", "Second Loaded")

    }

}