package com.example.kakao.ui

import android.os.Bundle
import android.util.SparseArray
import androidx.fragment.app.Fragment
import com.example.kakao.R
import com.example.kakao.base.BaseActivity
import com.example.kakao.databinding.ActivityMainBinding
import com.example.kakao.ext.init
import com.example.kakao.ui.firstTab.FirstTabFragment
import com.example.kakao.ui.secondTab.SecondTabFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding.run {
            bottomNavigation.init(
                fragmentManager = supportFragmentManager,
                containerId = mainNavFragment.id,
                idToFragmentMap = SparseArray<Fragment>().apply {
                    append(R.id.firstTabFragment, FirstTabFragment())
                    append(R.id.secondTabFragment, SecondTabFragment())
                })
        }
    }
}