package ru.geekbrains.popular.libraries.githubview_homeworks.di.components

import dagger.Component
import ru.geekbrains.popular.libraries.githubview_homeworks.di.modules.BindingModule
import ru.geekbrains.popular.libraries.githubview_homeworks.di.modules.ContextModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        BindingModule::class,
        ContextModule::class
    ]
)

interface AppComponent {
}