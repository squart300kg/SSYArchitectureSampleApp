package com.example.kakao.ext

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.kakao.R
import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.init(
    fragmentManager: FragmentManager,
    containerId: Int,
    idToFragmentMap: SparseArray<Fragment>
) {

    fragmentManager.beginTransaction(containerId, R.id.firstTabFragment, idToFragmentMap[R.id.firstTabFragment])

    setOnItemSelectedListener {  item ->

        if (item.itemId == selectedItemId) {
            return@setOnItemSelectedListener true
        }

        fragmentManager.beginTransaction(containerId, item.itemId, idToFragmentMap[item.itemId])

        true
    }

    fragmentManager.addOnBackStackChangedListener {
        val currentVisibleFragment = fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount.minus(1)).name?.toInt() ?: R.id.firstTabFragment
        menu.findItem(currentVisibleFragment).isChecked = true
    }
}

private fun FragmentManager.beginTransaction(containerId: Int, targetId: Int, targetFragment: Fragment) {
    beginTransaction()
        .replace(containerId, targetFragment, "$targetId")
        .addToBackStack("$targetId")
        .setReorderingAllowed(true)
        .setPrimaryNavigationFragment(targetFragment)
        .commit()
}