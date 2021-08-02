package ru.geekbrains.poplib.mvp.model.entity.room

import androidx.room.RoomDatabase
import ru.geekbrains.poplib.mvp.model.entity.room.dao.ImageDao
import ru.geekbrains.poplib.mvp.model.entity.room.dao.RepositoryDao
import ru.geekbrains.poplib.mvp.model.entity.room.dao.UserDao


@androidx.room.Database(entities = [RoomGithubUser::class, RoomGithubRepository::class, RoomCachedImage::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao
    abstract val imageDao: ImageDao

    companion object {
        const val DB_NAME = "database.db"
    }
}