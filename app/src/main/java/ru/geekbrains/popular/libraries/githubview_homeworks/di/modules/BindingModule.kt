package ru.geekbrains.popular.libraries.githubview_homeworks.di.modules

import dagger.Module
import dagger.Provides
import ru.geekbrains.popular.libraries.githubview_homeworks.databinding.ActivityMainBinding
import javax.inject.Singleton

@Module
class BindingModule {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding!!

    @Singleton
    @Provides
    fun provideBindingClass(): ActivityMainBinding {
        return binding
    }
}