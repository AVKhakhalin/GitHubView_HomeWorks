package ru.geekbrains.popular.libraries.githubview_homeworks.ui.utils.resources

import android.content.Context
import javax.inject.Inject

class ResourcesProviderImpl @Inject constructor(
    private val appContext: Context
) : ResourcesProvider {
    override fun getString(id: Int): String {
        return appContext.getString(id)
    }
}