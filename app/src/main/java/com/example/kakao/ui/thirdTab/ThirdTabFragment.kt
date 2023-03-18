package com.example.kakao.ui.thirdTab

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.kakao.R
import com.example.kakao.base.BaseFragment
import com.example.kakao.databinding.ThirdTabFragmetBinding

class ThirdTabFragment : BaseFragment<ThirdTabFragmetBinding>(R.layout.third_tab_fragmet) {

    private val thirdTabViewModel: ThirdTabViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("bottomId", "Third Loaded")

    }

}