//package com.example.kakao.uilayer.ext
//
//import android.util.Log
//import android.util.SparseArray
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import com.example.kakao.R
//import com.example.kakao.uilayer.ui.search.SearchResultFragment
//import com.example.kakao.uilayer.ui.locker.MyLockerFragment
//import com.google.android.material.bottomnavigation.BottomNavigationView
//
//// TODO: 로직 전체적으로 점검
////  1. 너무 복잡한건 아닌지?
////  2. backpressed는 어떻게 넣을건지?
////  3. 로직 수정 후, viewModelScope호출 테스트( ex. searchViewModel.search() 호출 )
//
//// TODO: 화면회전시 소스라이브 OOM때와 동일현상 발생함
//fun BottomNavigationView.init(
//    fragmentManager: FragmentManager,
//    containerId: Int,
//    onFinished: () -> Unit
//) {
//
//    val idToFragmentMap = SparseArray<Fragment>().apply {
//        append(R.id.searchResultFragment, SearchResultFragment())
//        append(R.id.myLockerFragment, MyLockerFragment())
//    }
//
//    fragmentManager.beginTransaction(
//        containerId = containerId,
//        targetId = R.id.searchResultFragment,
//        targetFragment = idToFragmentMap[R.id.searchResultFragment]
//    )
//    Log.i("loateTest", "oncreate")
//
//    setOnItemSelectedListener { selectItem ->
//
//        if (selectItem.itemId == selectedItemId) {
//            return@setOnItemSelectedListener true
//        }
//
//        fragmentManager.beginTransaction(
//            containerId = containerId,
//            targetId = selectItem.itemId,
//            targetFragment = idToFragmentMap[selectItem.itemId])
//
//        true
//    }
//
//    fragmentManager.addOnBackStackChangedListener {
//        if (fragmentManager.backStackEntryCount == 0) {
//            onFinished()
//        } else {
//            val currentVisibleFragment = fragmentManager
//                .getBackStackEntryAt(fragmentManager.backStackEntryCount.minus(1))
//                .name?.toInt() ?: R.id.searchResultFragment
//            menu.findItem(currentVisibleFragment).isChecked = true
//        }
//    }
//}
//
//private fun FragmentManager.beginTransaction(
//    containerId: Int,
//    targetId: Int,
//    targetFragment: Fragment
//) {
//    beginTransaction()
//        .replace(containerId, targetFragment, "$targetId")
//        .addToBackStack("$targetId")
//        .commit()
//}