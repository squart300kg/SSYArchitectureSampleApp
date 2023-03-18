package com.example.starter.di

import com.example.starter.repository.ExampleRepository
import com.example.starter.repository.YoutubeRepositoryImp
import org.koin.dsl.module

val repositoryModule = module {
    factory <ExampleRepository> { YoutubeRepositoryImp(get()) }
}