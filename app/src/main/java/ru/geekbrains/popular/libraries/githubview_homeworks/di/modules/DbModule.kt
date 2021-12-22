package ru.geekbrains.popular.libraries.githubview_homeworks.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrains.popular.libraries.githubview_homeworks.db.AppDatabase
import javax.inject.Singleton

private const val DB_NAME = "database.db"

@Module
class DbModule {
    @Singleton
    @Provides
    fun db(context: Context): AppDatabase =
        Room
            .databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .build()
}