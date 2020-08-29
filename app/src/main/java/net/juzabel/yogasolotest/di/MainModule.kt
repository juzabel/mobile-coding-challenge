package net.juzabel.yogasolotest.di

import android.content.Context
import androidx.navigation.NavController
import net.juzabel.domain.di.DomainModule
import net.juzabel.yogasolotest.core.Navigator
import net.juzabel.yogasolotest.posturedetail.PostureDetailViewModel
import net.juzabel.yogasolotest.posturelist.PostureListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


var mainModule = module {

    factory<Navigator> { (context: Context, navController: NavController) ->
        Navigator(context, navController)
    }

    viewModel {
        PostureListViewModel(
            get(named(DomainModule.POSTURE_LIST_USE_CASE)),
            get(named(DomainModule.CACHE_POSTURE_DETAIL_LIST_USE_CASE))
        )
    }

    viewModel { (id: String) ->
        PostureDetailViewModel(id, get(named(DomainModule.GET_POSTURE_DETAIL_USE_CASE)))
    }
}