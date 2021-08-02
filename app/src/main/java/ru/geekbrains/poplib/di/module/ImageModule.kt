package ru.geekbrains.poplib.di.module

import android.widget.ImageView
import dagger.Module
import dagger.Provides
import ru.geekbrains.poplib.mvp.model.cache.image.IImageCache
import ru.geekbrains.poplib.mvp.model.cache.image.room.RoomImageCache
import ru.geekbrains.poplib.mvp.model.entity.room.Database
import ru.geekbrains.poplib.mvp.model.image.IImageLoader
import ru.geekbrains.poplib.mvp.model.network.INetworkStatus
import ru.geekbrains.poplib.ui.App
import ru.geekbrains.poplib.ui.image.GlideImageLoader
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
class ImageModule {

    @Named("cacheDir")
    @Singleton
    @Provides
    fun cacheDir(app: App): File = app.cacheDir

    @Singleton
    @Provides
    fun imageCache(database: Database,  @Named("cacheDir") cacheDir: File): IImageCache = RoomImageCache(database, cacheDir)

    @Singleton
    @Provides
    fun imageLoader(cache: IImageCache, networkStatus: INetworkStatus): IImageLoader<ImageView> = GlideImageLoader(cache, networkStatus)

}