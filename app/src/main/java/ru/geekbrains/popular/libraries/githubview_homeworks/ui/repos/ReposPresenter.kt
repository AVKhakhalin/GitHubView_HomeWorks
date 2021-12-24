package ru.geekbrains.popular.libraries.githubview_homeworks.ui.repos

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.geekbrains.popular.libraries.githubview_homeworks.R
import ru.geekbrains.popular.libraries.githubview_homeworks.di.scope.containers.ReposScopeContainer
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.GithubRepoRepository
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.UserChooseRepository
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubRepoModel
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel
import ru.geekbrains.popular.libraries.githubview_homeworks.screens.AppScreens
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.utils.resources.ResourcesProvider
import javax.inject.Inject

class ReposPresenter @Inject constructor(
    private val router: Router,
    private val repo: GithubRepoRepository,
    private val appScreens: AppScreens,
    private val userChoose: UserChooseRepository,
    private val reposScopeContainer: ReposScopeContainer,
    private val resourcesProvider: ResourcesProvider
) : MvpPresenter<ReposView>() {

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
                            resourcesProvider.getString(R.string.error_not_repos_List),
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

    /** Уничтожение ReposSubcomponent при уничтожении данного презентера */
    override fun onDestroy() {
        reposScopeContainer.destroyGithubReposSubcomponent()
        super.onDestroy()
    }
}