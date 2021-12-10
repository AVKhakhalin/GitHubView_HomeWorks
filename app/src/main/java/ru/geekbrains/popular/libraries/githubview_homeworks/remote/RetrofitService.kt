package ru.geekbrains.popular.libraries.githubview_homeworks.remote

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel

interface RetrofitService {

    @GET("/users")
    fun getUsers(): Single<List<GithubUserModel>>
}