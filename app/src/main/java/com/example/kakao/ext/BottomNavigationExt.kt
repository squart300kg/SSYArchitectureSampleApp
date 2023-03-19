package com.example.kakao.ext

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.kakao.R
import com.example.kakao.ui.firstTab.SearchResultFragment
import com.example.kakao.ui.secondTab.MyLockerFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.init(
    fragmentManager: FragmentManager,
    containerId: Int,
) {

    val idToFragmentMap = SparseArray<Fragment>().apply {
        append(R.id.searchResultFragment, SearchResultFragment())
        append(R.id.myLockerFragment, MyLockerFragment())
    }

    fragmentManager.beginTransaction(
        containerId = containerId,
        targetId = R.id.searchResultFragment,
        targetFragment = idToFragmentMap[R.id.searchResultFragment]
    )

    setOnItemSelectedListener { selectItem ->
        if (selectItem.itemId == selectedItemId) {
            return@setOnItemSelectedListener true
        }

        fragmentManager.beginTransaction(
            containerId = containerId,
            targetId = selectItem.itemId,
            targetFragment = idToFragmentMap[selectItem.itemId]
        )

        true
    }

    fragmentManager.addOnBackStackChangedListener {
        val currentVisibleFragment = fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount.minus(1)).name?.toInt() ?: R.id.searchResultFragment
        menu.findItem(currentVisibleFragment).isChecked = true
    }
}

private fun FragmentManager.beginTransaction(
    containerId: Int,
    targetId: Int,
    targetFragment: Fragment
) {
    beginTransaction()
        .replace(containerId, targetFragment, "$targetId")
        .addToBackStack("$targetId")
        .setReorderingAllowed(true)
        .commit()
}