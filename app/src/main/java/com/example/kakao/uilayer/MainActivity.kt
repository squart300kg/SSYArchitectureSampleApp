package com.example.kakao.uilayer

import android.os.Bundle
import com.example.kakao.R
import com.example.kakao.databinding.ActivityMainBinding
import com.example.kakao.uilayer.base.BaseActivity
import com.example.kakao.uilayer.ui.locker.MyLockerFragment
import com.example.kakao.uilayer.ui.search.SearchResultFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val fragments = listOf(SearchResultFragment(), MyLockerFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {
            bottomNavigation.init(
                containerId = mainNavFragment.id
            )
        }
    }

    private fun BottomNavigationView.init(containerId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(containerId, fragments[0])
            .addToBackStack(null)
            .commit()

        setOnItemSelectedListener { selectItem ->
            supportFragmentManager.beginTransaction()
                .apply {
                    when (selectItem.itemId) {
                        R.id.searchResultFragment -> {
                            replace(containerId, fragments[0])
                        }
                        R.id.myLockerFragment -> {
                            replace(containerId, fragments[1])
                        }
                    }
                }
                .addToBackStack(null)
                .commit()

            true
        }
    }
}
