package ru.geekbrains.popular.libraries.githubview_homeworks

import android.app.Application
import ru.geekbrains.popular.libraries.githubview_homeworks.di.components.AppComponent
import ru.geekbrains.popular.libraries.githubview_homeworks.di.components.DaggerAppComponent
import ru.geekbrains.popular.libraries.githubview_homeworks.di.modules.ContextModule
import ru.geekbrains.popular.libraries.githubview_homeworks.di.modules.UserChooseModule
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.UserChooseRepositoryImpl

class App: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .userChooseModule(UserChooseModule(UserChooseRepositoryImpl()))
            .build()
    }

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