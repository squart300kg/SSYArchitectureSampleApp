package com.example.starter.ui.thirdTab

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.starter.R
import com.example.starter.base.BaseFragment
import com.example.starter.databinding.ThirdTabFragmetBinding

class ThirdTabFragment : BaseFragment<ThirdTabFragmetBinding>(R.layout.third_tab_fragmet) {

    private val thirdTabViewModel: ThirdTabViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("bottomId", "Third Loaded")

    }

}