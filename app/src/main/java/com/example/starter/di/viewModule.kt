package com.example.starter.di

import android.util.SparseArray
import androidx.fragment.app.Fragment
import com.example.starter.R
import com.example.starter.ui.firstTab.FirstTabFragment
import com.example.starter.ui.forthTab.ForthTabFragment
import com.example.starter.ui.secondTab.SecondTabFragment
import com.example.starter.ui.thirdTab.ThirdTabFragment
import org.koin.dsl.module

val viewModule = module {
    single { SparseArray<Fragment>().apply {
        append(R.id.firstTabFragment, FirstTabFragment())
        append(R.id.secondTabFragment, SecondTabFragment())
        append(R.id.thirdTabFragment, ThirdTabFragment())
        append(R.id.forthTabFragment, ForthTabFragment()) }
    }

}