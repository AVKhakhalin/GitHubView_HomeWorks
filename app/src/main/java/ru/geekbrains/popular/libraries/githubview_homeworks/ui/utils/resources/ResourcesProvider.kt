package ru.geekbrains.popular.libraries.githubview_homeworks.ui.utils.resources

import androidx.annotation.StringRes

interface ResourcesProvider {

    fun getString(@StringRes id: Int): String
}