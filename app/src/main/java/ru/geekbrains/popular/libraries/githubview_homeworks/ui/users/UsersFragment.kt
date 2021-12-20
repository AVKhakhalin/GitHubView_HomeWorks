package ru.geekbrains.popular.libraries.githubview_homeworks.ui.users

import android.content.Context
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
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.cache.GithubUsersCacheImpl
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.retrofit.GithubUsersRetrofitImpl
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.base.BackButtonListener
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.main.MainActivity
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.users.adapter.UsersAdapter
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.utils.GlideImageLoader

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    /** Задание переменных */ //region
    // mainActivity
    private var mainActivity: MainActivity? = null
    private val presenter by moxyPresenter {
        UsersPresenter(
            router = App.instance.appComponent.routerInstance(),
            usersRepository = GithubUsersRepositoryImpl(
                networkStatus = App.instance.appComponent.networkStatus(),
                GithubUsersRetrofitImpl(
                    retrofitService = App.instance.appComponent.retrofit(),
                    db = App.instance.appComponent.db()
                ),
                GithubUsersCacheImpl(
                    db = App.instance.appComponent.db()
                )
            ),
            networkStatus = App.instance.appComponent.networkStatus(),
            appScreens = App.instance.appComponent.appScreens()
        )
    }

    // binding
    private var _binding: FragmentUsersBinding? = null
    private val binding
        get() = _binding!!

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

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = (context as MainActivity)
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
        /** Установка mainActivity в UsersPresenter */
        presenter.setMainActivity(mainActivity)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onResume() {
        super.onResume()

        mainActivity?.let { mainActivity ->
            mainActivity.getUsersModel()?.let { users ->
                presenter.setUsers(users)
            }
        }
    }
}