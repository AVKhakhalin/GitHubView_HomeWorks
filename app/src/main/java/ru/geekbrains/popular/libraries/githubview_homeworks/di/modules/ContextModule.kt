package ru.geekbrains.popular.libraries.githubview_homeworks.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.geekbrains.popular.libraries.githubview_homeworks.App
import javax.inject.Singleton

@Module
class ContextModule(private val app: App) {

    @Singleton
    @Provides
    fun context(): Context {
        return app
    }

    @Singleton
    @Provides
    fun app(): App {
        return app
    }
}