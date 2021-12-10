package ru.geekbrains.popular.libraries.githubview_homeworks.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.popular.libraries.githubview_homeworks.App
import ru.geekbrains.popular.libraries.githubview_homeworks.databinding.FragmentUsersBinding
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.GithubUsersRepositoryImpl
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel
import ru.geekbrains.popular.libraries.githubview_homeworks.remote.ApiHolder
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.base.BackButtonListener
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.users.adapter.UsersAdapter
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.utils.GlideImageLoader

class UsersFragment: MvpAppCompatFragment(), UsersView, BackButtonListener {

    /** Задание переменных */ //region
    val presenter by moxyPresenter {
        UsersPresenter(
            App.instance.router,
            GithubUsersRepositoryImpl(ApiHolder.retrofitService),
            this@UsersFragment
        )
    }
    // binding
    private var _binding: FragmentUsersBinding? = null
    private val binding
        get() = _binding!!
    // adapter
    private val adapter by lazy {
//        UsersAdapter(presenter.usersListPresenter)
        UsersAdapter(presenter.usersListPresenter,
        GlideImageLoader())
    }
    //endregion

    companion object {
        fun newInstance() = UsersFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    /** Получение адаптера */
    @JvmName("getAdapter1")
    fun getAdapter(): UsersAdapter {
        return adapter
    }
}