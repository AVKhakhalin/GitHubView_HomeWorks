package ru.geekbrains.popular.libraries.githubview_homeworks.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.geekbrains.popular.libraries.githubview_homeworks.App
import ru.geekbrains.popular.libraries.githubview_homeworks.db.dao.RepoDao
import ru.geekbrains.popular.libraries.githubview_homeworks.db.dao.UserDao
import ru.geekbrains.popular.libraries.githubview_homeworks.db.model.RoomGithubRepo
import ru.geekbrains.popular.libraries.githubview_homeworks.db.model.RoomGithubUser

@Database(
    entities = [
        RoomGithubUser::class,
        RoomGithubRepo::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract val userDao: UserDao

    abstract val repositoryDao: RepoDao

    companion object {
        private const val DB_NAME = "database.db"

        val instance by lazy {
            Room.databaseBuilder(App.instance, AppDatabase::class.java, DB_NAME)
                .build()
        }
    }
}