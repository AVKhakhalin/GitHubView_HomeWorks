package ru.geekbrains.popular.libraries.githubview_homeworks.ui.repos

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.GithubRepoRepository
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubRepoModel
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel
import ru.geekbrains.popular.libraries.githubview_homeworks.screens.AppScreens

class ReposPresenter(
    private val userModel: GithubUserModel,
    private val router: Router,
    private val repo: GithubRepoRepository,
) : MvpPresenter<ReposView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()
    }

    private fun loadData() {
        repo.getRepos(userModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading() }
            .subscribe(
                { repos ->
                    viewState.showRepos(repos)
                    viewState.hideLoading()
                }, {
                    Log.e(this::class.java.simpleName, "Ошибка при получении репозиториев", it)
                    viewState.hideLoading()
                }
            )
    }

    fun onRepoClicked(repo: GithubRepoModel) {
        // todo
        Log.d("mylogs", "${repo.forksCount}")
        router.navigateTo(
            AppScreens.forksScreen(userModel, repoModel = repo, this@ReposPresenter)
        )

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}