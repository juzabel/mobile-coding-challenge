package net.juzabel.domain.di

import net.juzabel.domain.core.Result
import net.juzabel.domain.core.UseCase
import net.juzabel.domain.feature.posturelist.PostureListUseCase
import net.juzabel.domain.feature.posturelist.model.Posture
import org.koin.dsl.module

val domainModule = module {
    factory<UseCase<Unit, Result<List<Posture>>>> { PostureListUseCase(get()) }
}