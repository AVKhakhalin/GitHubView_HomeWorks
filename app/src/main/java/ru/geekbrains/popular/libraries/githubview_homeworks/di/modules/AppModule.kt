package ru.geekbrains.popular.libraries.githubview_homeworks.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.geekbrains.popular.libraries.githubview_homeworks.App
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.utils.resources.ResourcesProvider
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.utils.resources.ResourcesProviderImpl
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

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

    @Singleton
    @Provides
    fun resProvider(resourcesProviderImpl: ResourcesProviderImpl): ResourcesProvider {
        return resourcesProviderImpl
    }
}