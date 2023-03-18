package com.example.kakao.di

import com.example.kakao.repository.ExampleRepository
import com.example.kakao.repository.YoutubeRepositoryImp
import org.koin.dsl.module

val repositoryModule = module {
    factory <ExampleRepository> { YoutubeRepositoryImp(get()) }
}