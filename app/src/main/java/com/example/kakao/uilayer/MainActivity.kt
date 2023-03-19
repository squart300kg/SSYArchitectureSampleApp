package com.example.kakao.uilayer

import android.os.Bundle
import com.example.kakao.R
import com.example.kakao.uilayer.base.BaseActivity
import com.example.kakao.databinding.ActivityMainBinding
import com.example.kakao.uilayer.ext.init
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