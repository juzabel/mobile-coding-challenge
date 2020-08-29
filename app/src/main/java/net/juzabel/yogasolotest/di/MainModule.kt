package net.juzabel.yogasolotest.di

import net.juzabel.yogasolotest.posturelist.PostureListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


var mainModule = module {
    viewModel {
        PostureListViewModel()
    }
}