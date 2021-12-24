package ru.geekbrains.popular.libraries.githubview_homeworks.ui.users

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.popular.libraries.githubview_homeworks.App
import ru.geekbrains.popular.libraries.githubview_homeworks.R
import ru.geekbrains.popular.libraries.githubview_homeworks.databinding.FragmentUsersBinding
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.UserChooseRepository
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.base.BackButtonListener
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.users.adapter.UsersAdapter
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.utils.binding.viewBinding
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.utils.imageloader.GlideImageLoader

class UsersFragment : MvpAppCompatFragment(R.layout.fragment_users), UsersView, BackButtonListener {

    /** Задание переменных */ //region
    // userChoose
    private val userChoose: UserChooseRepository = App.instance.appComponent.userChoose()

    // presenter
    private val presenter by moxyPresenter {
        App.instance.initGithubUsersSubcomponent()
        App.instance.usersSubcomponent?.provideUsersPresenter()!!
    }

    // binding
    private val binding by viewBinding<FragmentUsersBinding>()

    // adapter
    private val adapter by lazy {
        UsersAdapter(
            presenter.usersListPresenter,
            GlideImageLoader()
        )
    }
    //endregion

    companion object {
        fun newInstance() = UsersFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /** Установка списка пользователей */
        binding.usersListRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.usersListRecycler.adapter = adapter
    }

    override fun showLoading() {
        binding.loadingView.visibility = View.VISIBLE
        binding.usersListRecycler.visibility = View.GONE
    }

    override fun hideLoading() {
        binding.loadingView.visibility = View.INVISIBLE
        binding.usersListRecycler.visibility = View.VISIBLE
    }

    override fun updateList(users: List<GithubUserModel>) {
        adapter.submitList(users)
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }

    override fun onResume() {
        super.onResume()

        presenter.setUsers(userChoose.getUsersModel())
    }
}