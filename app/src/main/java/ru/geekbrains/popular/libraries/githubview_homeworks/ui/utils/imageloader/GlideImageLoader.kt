package ru.geekbrains.popular.libraries.githubview_homeworks.ui.utils.imageloader

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class GlideImageLoader : ImageLoader<ImageView> {

    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .asBitmap()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .load(url)
            .circleCrop()
            .into(container)
    }
}