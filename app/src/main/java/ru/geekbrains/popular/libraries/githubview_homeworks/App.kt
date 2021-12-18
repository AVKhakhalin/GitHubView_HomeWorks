package ru.geekbrains.popular.libraries.githubview_homeworks

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import ru.geekbrains.popular.libraries.githubview_homeworks.di.components.AppComponent
import ru.geekbrains.popular.libraries.githubview_homeworks.di.components.DaggerAppComponent
import ru.geekbrains.popular.libraries.githubview_homeworks.di.modules.ContextModule

class App: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }

//    private val cicerone: Cicerone<Router> by lazy {
//        Cicerone.create()
//    }

//    val navigationHolder
//        get() = cicerone.getNavigatorHolder()

//    val router
//        get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }

    companion object {
        private var _instance: App? = null
        val instance
            get() = _instance!!
    }
}