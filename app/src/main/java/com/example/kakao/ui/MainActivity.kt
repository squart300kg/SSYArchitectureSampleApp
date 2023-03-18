package com.example.kakao.ui

import android.os.Bundle
import android.util.SparseArray
import androidx.fragment.app.Fragment
import com.example.kakao.R
import com.example.kakao.base.BaseActivity
import com.example.kakao.databinding.ActivityMainBinding
import com.example.kakao.ext.init
import org.koin.android.ext.android.inject

class MainActivity: BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val idToFragmentMap: SparseArray<Fragment> by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding.run {
            bottomNavigation.init(
                fragmentManager = supportFragmentManager,
                containerId = mainNavFragment.id,
                idToFragmentMap = idToFragmentMap)
        }
    }
}