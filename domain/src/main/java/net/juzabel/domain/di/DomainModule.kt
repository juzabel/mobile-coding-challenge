package net.juzabel.domain.di

import net.juzabel.domain.core.Result
import net.juzabel.domain.core.UseCase
import net.juzabel.domain.feature.posturelist.CachePostureDetailListUseCase
import net.juzabel.domain.feature.posturedetail.GetPostureDetailUseCase
import net.juzabel.domain.feature.posturedetail.model.PostureDetail
import net.juzabel.domain.feature.posturelist.PostureListUseCase
import net.juzabel.domain.feature.posturelist.model.Posture
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule = module {
    factory<UseCase<Unit, Result<List<Posture>>>>(named(DomainModule.POSTURE_LIST_USE_CASE)) {
        PostureListUseCase(get())
    }

    factory<UseCase<Unit, Result<List<PostureDetail>>>>(named(DomainModule.CACHE_POSTURE_DETAIL_LIST_USE_CASE)) {
        CachePostureDetailListUseCase(get())
    }

    factory<UseCase<String, Result<PostureDetail>>>(named(DomainModule.GET_POSTURE_DETAIL_USE_CASE)) {
        GetPostureDetailUseCase(get())
    }
}

object DomainModule {
    const val POSTURE_LIST_USE_CASE = "POSTURE_LIST_USE_CASE"
    const val CACHE_POSTURE_DETAIL_LIST_USE_CASE = "CACHE_POSTURE_DETAIL_LIST_USE_CASE"
    const val GET_POSTURE_DETAIL_USE_CASE = "GET_POSTURE_DETAIL_USE_CASE"
}