package com.example.kakao.ui.forthTab

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.kakao.R
import com.example.kakao.base.BaseFragment
import com.example.kakao.databinding.ForthTabFragmentBinding

class ForthTabFragment : BaseFragment<ForthTabFragmentBinding>(R.layout.forth_tab_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("bottomId", "Forth Loaded")
    }

}