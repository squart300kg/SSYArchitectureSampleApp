package com.example.starter.di

import com.example.starter.ui.firstTab.FirstTabViewModel
import com.example.starter.ui.forthTab.ForthTabViewModel
import com.example.starter.ui.secondTab.SecondTabViewModel
import com.example.starter.ui.thirdTab.ThirdTabViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { FirstTabViewModel() }
    viewModel { SecondTabViewModel() }
    viewModel { ThirdTabViewModel() }
    viewModel { ForthTabViewModel() }
}