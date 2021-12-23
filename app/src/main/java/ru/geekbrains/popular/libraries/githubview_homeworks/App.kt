package ru.geekbrains.popular.libraries.githubview_homeworks

import android.app.Application
import ru.geekbrains.popular.libraries.githubview_homeworks.di.components.AppComponent
import ru.geekbrains.popular.libraries.githubview_homeworks.di.components.DaggerAppComponent
import ru.geekbrains.popular.libraries.githubview_homeworks.di.components.GithubForksSubcomponent
import ru.geekbrains.popular.libraries.githubview_homeworks.di.modules.ContextModule
import ru.geekbrains.popular.libraries.githubview_homeworks.di.scope.containers.ForksScopeContainer

class App: Application(), ForksScopeContainer {
    /** Исходные данные */ //region
    // appComponent
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }
    // forksSubcomponent
    var forksSubcomponent: GithubForksSubcomponent? = null
    //endregion

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }

    companion object {
        private var _instance: App? = null
        val instance
            get() = _instance!!
    }

    /** Методы ForksScopeContainer */ //region
    override fun initForksSubcomponent() = appComponent.forksSubcomponent().also {
        forksSubcomponent = it
    }
    override fun destroyForksSubcomponent() {
        forksSubcomponent = null
    }
    //endregion
}