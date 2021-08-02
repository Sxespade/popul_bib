package ru.geekbrains.poplib.di.module

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrains.poplib.mvp.model.cache.IGithubRepositoriesCache
import ru.geekbrains.poplib.mvp.model.cache.IGithubUsersCache
import ru.geekbrains.poplib.mvp.model.cache.image.IImageCache
import ru.geekbrains.poplib.mvp.model.cache.image.room.RoomImageCache
import ru.geekbrains.poplib.mvp.model.cache.room.RoomGithubRepositoriesCache
import ru.geekbrains.poplib.mvp.model.cache.room.RoomGithubUsersCache
import ru.geekbrains.poplib.mvp.model.entity.room.Database
import ru.geekbrains.poplib.ui.App
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
        .build()

}
