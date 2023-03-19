package com.example.kakao.uilayer.ext

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.kakao.R
import com.example.kakao.uilayer.ui.search.SearchResultFragment
import com.example.kakao.uilayer.ui.locker.MyLockerFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.init(
    fragmentManager: FragmentManager,
    containerId: Int,
) {

    val idToFragmentMap = SparseArray<Fragment>().apply {
        append(R.id.searchResultFragment, SearchResultFragment())
        append(R.id.myLockerFragment, MyLockerFragment())
    }

    fragmentManager.beginTransaction()
        .replace(containerId, idToFragmentMap[R.id.searchResultFragment])
        .commit()

    setOnItemSelectedListener { selectItem ->
        if (selectItem.itemId == selectedItemId) {
            return@setOnItemSelectedListener true
        }

        fragmentManager.beginTransaction()
            .replace(containerId, idToFragmentMap[selectItem.itemId])
            .commit()

        true
    }
}