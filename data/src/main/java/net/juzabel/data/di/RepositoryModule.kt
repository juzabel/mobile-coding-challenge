package net.juzabel.data.di

import net.juzabel.data.feature.posturelist.datasource.PostureListLocalDataSource
import net.juzabel.data.feature.posturelist.datasource.PostureListLocalDataSourceImpl
import net.juzabel.data.feature.posturelist.datasource.PostureListRemoteDataSource
import net.juzabel.data.feature.posturelist.datasource.PostureListRemoteDataSourceImpl
import net.juzabel.data.feature.posturelist.repository.PostureListRepositoryImpl
import net.juzabel.domain.feature.posturelist.PostureListRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<PostureListRepository> { PostureListRepositoryImpl(get(), get()) }
    factory<PostureListRemoteDataSource>{ PostureListRemoteDataSourceImpl(get())}
    factory<PostureListLocalDataSource> { PostureListLocalDataSourceImpl(get()) }
}