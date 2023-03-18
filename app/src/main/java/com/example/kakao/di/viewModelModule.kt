package com.example.kakao.di

import com.example.kakao.ui.firstTab.FirstTabViewModel
import com.example.kakao.ui.secondTab.SecondTabViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { FirstTabViewModel() }
    viewModel { SecondTabViewModel() }
}