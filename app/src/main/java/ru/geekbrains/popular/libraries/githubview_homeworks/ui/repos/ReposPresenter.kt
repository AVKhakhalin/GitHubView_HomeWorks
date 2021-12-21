package ru.geekbrains.popular.libraries.githubview_homeworks.ui.repos

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.geekbrains.popular.libraries.githubview_homeworks.App
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.GithubRepoRepository
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.UserChooseRepository
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubRepoModel
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel
import ru.geekbrains.popular.libraries.githubview_homeworks.screens.AppScreens
import javax.inject.Inject

class ReposPresenter @Inject constructor(
    private val router: Router,
    private val repo: GithubRepoRepository,
    private val appScreens: AppScreens
) : MvpPresenter<ReposView>() {
    /** ИСХОДНЫЕ ДАННЫЕ */ //region
    // userChoose
    private val userChoose: UserChooseRepository = App.instance.appComponent.userChoose()
    //endregion

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()
    }

    private fun loadData() {
        val userModel: GithubUserModel = userChoose.getGithubUserModel()
        userModel?.let { userModel ->
            repo.getRepos(userModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.showLoading() }
                .subscribe(
                    { repos ->
                        viewState.showRepos(repos)
                        viewState.hideLoading()
                        userChoose.setReposModel(repos)
                    }, {
                        Log.e(
                            "mylogs",
                            "Ошибка при получении репозиториев",
                            it
                        )
                        viewState.hideLoading()
                    }
                )
        }
    }

    fun onRepoClicked(repo: GithubRepoModel) {
        userChoose.setGithubRepoModel(repo)
        router.navigateTo(appScreens.forksScreen())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}