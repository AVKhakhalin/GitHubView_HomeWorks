package ru.geekbrains.popular.libraries.githubview_homeworks.screens

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.users.LoginFragment
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.users.UsersFragment
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.users.UsersPresenter

object AppScreens {
    /** Вызов фрагмента со списком логинов пользователей */
    fun usersScreen() = FragmentScreen {
        UsersFragment.newInstance()
    }
    /** Вызов фрагмента с логином пользователя */
    fun loginScreen(login: String, presenter: UsersPresenter) = FragmentScreen {
        LoginFragment.newInstance(login, presenter)
    }
}