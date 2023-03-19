package com.example.kakao.ui

import android.os.Bundle
import android.util.SparseArray
import androidx.fragment.app.Fragment
import com.example.kakao.R
import com.example.kakao.base.BaseActivity
import com.example.kakao.databinding.ActivityMainBinding
import com.example.kakao.ext.init
import com.example.kakao.ui.firstTab.SearchResultFragment
import com.example.kakao.ui.secondTab.MyLockerFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {
            bottomNavigation.init(
                fragmentManager = supportFragmentManager,
                containerId = mainNavFragment.id)
        }
    }
}