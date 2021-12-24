package ru.geekbrains.popular.libraries.githubview_homeworks.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGithubUser(
    @PrimaryKey val id: String,
    val login: String,
    val avatarUrl: String,
    val reposUrl: String,
)