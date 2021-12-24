package ru.geekbrains.popular.libraries.githubview_homeworks.ui.repos

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.popular.libraries.githubview_homeworks.App
import ru.geekbrains.popular.libraries.githubview_homeworks.R
import ru.geekbrains.popular.libraries.githubview_homeworks.databinding.FragmentReposBinding
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.UserChooseRepository
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubRepoModel
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.base.BackButtonListener
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.utils.binding.viewBinding

class ReposFragment : MvpAppCompatFragment(R.layout.fragment_repos), ReposView, BackButtonListener {
    /** ЗАДАНИЕ ПЕРЕМЕННЫХ */ //region
    // userChoose
    private val userChoose: UserChooseRepository = App.instance.appComponent.userChoose()

    // presenter
    private val presenter by moxyPresenter {
        App.instance.initGithubReposSubcomponent()
        App.instance.reposSubcomponent?.provideReposPresenter()!!
    }

    // binding
    private val binding by viewBinding<FragmentReposBinding>()

    // adapter
    private val adapter by lazy {
        ReposAdapter { presenter.onRepoClicked(it) }
    }
    //endregion

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /** Установка заголовка окна */
        binding.reposTitle.text = "${
            requireActivity().getString(R.string.repos_fragment_forks_title_text)
        } \"${
            userChoose.getGithubUserModel().login
        }\":"
        /** Установка списка репозиториев пользователя */
        binding.reposRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.reposRecycler.adapter = adapter
    }

    override fun showLoading() {
        binding.loadingView.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.loadingView.visibility = View.INVISIBLE
    }

    override fun showRepos(repos: List<GithubRepoModel>) {
        adapter.submitList(repos)
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }

    companion object {
        fun newInstance() = ReposFragment()
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()

        binding.reposTitle.text =
            "${requireActivity().getString(R.string.repos_fragment_forks_title_text)} \"${
                userChoose.getGithubUserModel().login
            }\":"
        binding.reposRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.reposRecycler.adapter = adapter
    }
}