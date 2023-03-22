package com.example.kakao.uilayer

import android.os.Bundle
import androidx.activity.viewModels
import com.example.kakao.R
import com.example.kakao.uilayer.base.BaseActivity
import com.example.kakao.databinding.ActivityMainBinding
import com.example.kakao.uilayer.ui.locker.MyLockerFragment
import com.example.kakao.uilayer.ui.locker.MyLockerViewModel
import com.example.kakao.uilayer.ui.search.SearchResultFragment
import com.example.kakao.uilayer.ui.search.SearchResultViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val searchResultViewModel: SearchResultViewModel by viewModels()
    private val myLockerViewModel: MyLockerViewModel by viewModels()

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
            .apply {
                fragments.forEach { fragment ->
                    add(containerId, fragment)
                }
                hide(fragments[1])
            }
            .show(fragments[0])
            .commit()

        setOnItemSelectedListener { selectItem ->
            supportFragmentManager.beginTransaction()
                .apply {
                    when (selectItem.itemId) {
                        R.id.searchResultFragment -> {
                            show(fragments[0])
                            hide(fragments[1])

                            searchResultViewModel.reflectLocalImageWhenTabMove()
                        }
                        R.id.myLockerFragment -> {
                            show(fragments[1])
                            hide(fragments[0])

                            myLockerViewModel.fetchLocalImages()
                        }
                    }
                }.commit()

            true
        }
    }
}
