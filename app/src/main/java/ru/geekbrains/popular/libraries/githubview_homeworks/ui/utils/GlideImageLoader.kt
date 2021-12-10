package ru.geekbrains.popular.libraries.githubview_homeworks.ui.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader: ImageLoader<ImageView> {

    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .asBitmap()
            .load(url)
            .circleCrop()
            .into(container)
    }
}