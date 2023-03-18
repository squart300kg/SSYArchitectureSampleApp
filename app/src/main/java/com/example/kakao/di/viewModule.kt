package com.example.kakao.di

import android.util.SparseArray
import androidx.fragment.app.Fragment
import com.example.kakao.R
import com.example.kakao.ui.firstTab.FirstTabFragment
import com.example.kakao.ui.secondTab.SecondTabFragment
import org.koin.dsl.module

val viewModule = module {
    single {
        SparseArray<Fragment>().apply {
            append(R.id.firstTabFragment, FirstTabFragment())
            append(R.id.secondTabFragment, SecondTabFragment())
        }
    }
}