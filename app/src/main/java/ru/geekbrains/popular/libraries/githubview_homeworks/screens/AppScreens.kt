package ru.geekbrains.popular.libraries.githubview_homeworks.screens

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.users.UsersFragment

object AppScreens {

    fun usersScreen() = FragmentScreen {
        UsersFragment()
    }
}