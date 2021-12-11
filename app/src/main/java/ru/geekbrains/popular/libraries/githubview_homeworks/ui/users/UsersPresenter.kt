package ru.geekbrains.popular.libraries.githubview_homeworks.ui.users

import android.util.Log
import android.widget.Toast
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.geekbrains.popular.libraries.githubview_homeworks.R
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.GithubUsersRepository
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel
import ru.geekbrains.popular.libraries.githubview_homeworks.screens.AppScreens
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.base.IListPresenter

class UsersPresenter(
    private val router: Router,
    private val usersRepository: GithubUsersRepository,
    private val usersFragment: UsersFragment?
): MvpPresenter<UsersView>() {

    val usersListPresenter = UsersListPresenter()
    private var users: List<GithubUserModel> = listOf()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()

        usersFragment?.let { usersFragment ->
            usersListPresenter.itemClickListener = { userItemView ->
                Toast.makeText(usersFragment.requireContext(), "${userItemView.pos} ? ${users.size}", Toast.LENGTH_LONG).show()
                router.navigateTo(
//                    AppScreens.loginScreen(
//                        (if (userItemView.pos < users.size) users[userItemView.pos].login
//                        else usersFragment.resources.getString(R.string.error_not_user_name)),
//                        usersFragment.presenter
//                    )
                    AppScreens.repoScreen(userModel = GithubUserModel(users[userItemView.pos].login,
                        users[userItemView.pos].avatarUrl, users[userItemView.pos].reposUrl))
                )
            }
        }
    }

    private fun loadData() {
        usersRepository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading() }
            .subscribe(
                { users ->
                    this.users = users
                    viewState.updateList(users)
                    viewState.hideLoading()
                }, { e ->
                    Log.e("mylogs", "Ошибка при получении пользователей", e)
                    viewState.hideLoading()
                }
            )
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    class UsersListPresenter: IListPresenter<UserItemView> {

        val users = mutableListOf<GithubUserModel>()

        override var itemClickListener: (UserItemView) -> Unit = {}

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
            view.setAvatar(user.avatarUrl)
        }
    }
}