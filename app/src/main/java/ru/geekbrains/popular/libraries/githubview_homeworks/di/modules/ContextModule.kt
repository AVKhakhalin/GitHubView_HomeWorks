package ru.geekbrains.popular.libraries.githubview_homeworks.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.main.MainActivity
import javax.inject.Singleton

@Module
class ContextModule(private val app: Application) {

    @Singleton
    @Provides
    fun app(): Context {
        return app
    }
}