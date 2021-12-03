package ru.geekbrains.popular.libraries.githubview_homeworks.domain

import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel

class GithubUsersRepository {
    private val users = listOf(
        GithubUserModel("Иванов Иван Иванович"),
        GithubUserModel("Софико Мила Юрьевна"),
        GithubUserModel("Васильев Ильнар Мазарович"),
        GithubUserModel("Гунар Кирилл Викторович"),
        GithubUserModel("Мирзо Юсуп Алиевич"),
        GithubUserModel("Ким Илья Андреевич")
    )

    fun getUsers(): List<GithubUserModel> {
        return users
    }
}