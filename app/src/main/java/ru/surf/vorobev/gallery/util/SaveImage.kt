package ru.surf.vorobev.gallery.util

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.Dispatchers

private val requestOptions = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)

fun savePhoto (url: String, context: Context) {
    Glide.with(context).load(url).apply(requestOptions).submit()
}