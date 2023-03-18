package com.example.starter.ui.forthTab

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.starter.R
import com.example.starter.base.BaseFragment
import com.example.starter.databinding.ForthTabFragmentBinding

class ForthTabFragment : BaseFragment<ForthTabFragmentBinding>(R.layout.forth_tab_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("bottomId", "Forth Loaded")
    }

}