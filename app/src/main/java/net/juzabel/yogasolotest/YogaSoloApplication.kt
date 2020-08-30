package net.juzabel.yogasolotest

import android.app.Application
import net.juzabel.data.di.dataModule
import net.juzabel.data.di.repositoryModule
import net.juzabel.domain.di.domainModule
import net.juzabel.yogasolotest.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class YogaSoloApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@YogaSoloApplication)
            modules(listOf(mainModule, domainModule, dataModule, repositoryModule))
        }
    }
}