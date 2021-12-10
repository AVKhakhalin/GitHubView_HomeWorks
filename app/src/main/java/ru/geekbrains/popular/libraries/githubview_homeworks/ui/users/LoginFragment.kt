package ru.geekbrains.popular.libraries.githubview_homeworks.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import ru.geekbrains.popular.libraries.githubview_homeworks.databinding.FragmentLoginBinding
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.base.BackButtonListener

class LoginFragment(
    private val login: String,
    private val presenter: UsersPresenter
): MvpAppCompatFragment(), BackButtonListener {

    // binding
    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = _binding!!
    //endregion

    companion object {
        fun newInstance(login: String, presenter: UsersPresenter) = LoginFragment(login, presenter)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /** Установка отображение логина пользователя */
        binding.userLogin.text = login
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}