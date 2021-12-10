package ru.geekbrains.popular.libraries.githubview_homeworks.ui.users

import android.util.Log
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
                router.navigateTo(
                    AppScreens.loginScreen(
                        (if (userItemView.pos < users.size) users[userItemView.pos].login
                        else usersFragment.resources.getString(R.string.error_not_user_name))
                    )
                )
            }
        }
    }

    private fun loadData() {
//        usersFragment?.let { usersFragment ->
//            usersRepository.getUsers()
//                .switchMap {
//                    return@switchMap Observable.just( it )}
//                .subscribe(
//                    {
//                        users = it
//                        usersFragment.getAdapter().submitList(it)
//                    },
//                    {
//                        Toast.makeText(usersFragment.requireContext(), "${
//                            usersFragment.resources.getString(R.string.error_get_list_users)}$it",
//                            Toast.LENGTH_LONG).show()
//                    }
//                )
//        }

//        usersFragment?.let { usersFragment ->
//            val service = ApiHolder.retrofitService
//            service.getUsers()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe { users: List<GithubUserModel> ->
//                   Toast.makeText(usersFragment.requireContext(), "Первый пользователь: ${
//                       users.firstOrNull()?.login}", Toast.LENGTH_LONG).show()
//                    Log.d("mylogs", "Первый пользователь: ${users.firstOrNull()?.login}")
//                }
//        }

        usersRepository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { /** viewState.showLoading() */ }
            .subscribe(
                { users ->
                    viewState.updateList(users)
                    /** viewState.hideLoading() */
                }, { e ->
                    Log.e("mylogs", "Ошибка при получении пользователей", e)
                    /** viewState.hideLoading() */
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
        }
    }
}