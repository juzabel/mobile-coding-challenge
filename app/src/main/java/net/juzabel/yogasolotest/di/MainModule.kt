package net.juzabel.yogasolotest.di

import net.juzabel.domain.core.Result
import net.juzabel.domain.core.UseCase
import net.juzabel.domain.feature.posturelist.model.Posture
import net.juzabel.yogasolotest.posturelist.PostureListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


var mainModule = module {
    viewModel {
        PostureListViewModel(get<UseCase<Unit, Result<List<Posture>>>>())
    }
}