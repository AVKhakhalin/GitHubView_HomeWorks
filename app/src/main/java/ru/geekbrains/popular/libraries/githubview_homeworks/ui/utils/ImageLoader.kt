package ru.geekbrains.popular.libraries.githubview_homeworks.ui.utils

interface ImageLoader<T> {
    fun loadInto(url: String, container: T)
}