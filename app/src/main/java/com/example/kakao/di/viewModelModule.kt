package com.example.kakao.di

import com.example.kakao.ui.firstTab.FirstTabViewModel
import com.example.kakao.ui.forthTab.ForthTabViewModel
import com.example.kakao.ui.secondTab.SecondTabViewModel
import com.example.kakao.ui.thirdTab.ThirdTabViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { FirstTabViewModel() }
    viewModel { SecondTabViewModel() }
    viewModel { ThirdTabViewModel() }
    viewModel { ForthTabViewModel() }
}