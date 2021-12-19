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
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.main.MainActivity
import javax.inject.Inject

class ReposPresenter @Inject constructor(
    private val router: Router,
    private val repo: GithubRepoRepository,
    private val mainActivity: MainActivity?
): MvpPresenter<ReposView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()
    }

    private fun loadData() {
        mainActivity?.let { mainActivity ->
            val userModel: GithubUserModel = mainActivity.getGithubUserModel()
            userModel?.let { userModel ->
                repo.getRepos(userModel)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { viewState.showLoading() }
                    .subscribe(
                        { repos ->
                            viewState.showRepos(repos)
                            viewState.hideLoading()
                            mainActivity.setReposModel(repos)
                        }, {
                            Log.e("mylogs",
                                "Ошибка при получении репозиториев",
                                it
                            )
                            viewState.hideLoading()
                        }
                    )
            }
        }
    }

    fun onRepoClicked(repo: GithubRepoModel) {
        mainActivity?.let { mainActivity ->
            mainActivity.setGithubRepoModel(repo)
        }
        router.navigateTo(AppScreens.forksScreen())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}