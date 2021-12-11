package ru.geekbrains.popular.libraries.githubview_homeworks.ui.forks

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.popular.libraries.githubview_homeworks.App
import ru.geekbrains.popular.libraries.githubview_homeworks.databinding.FragmentForksBinding
import ru.geekbrains.popular.libraries.githubview_homeworks.databinding.FragmentReposBinding
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.GithubRepoRepositoryImpl
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubRepoModel
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel
import ru.geekbrains.popular.libraries.githubview_homeworks.remote.ApiHolder
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.base.BackButtonListener
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.repos.ReposPresenter

class ForksFragment(
    private val userModel: GithubUserModel,
    private val repoModel: GithubRepoModel,
    private val presenter: ReposPresenter
): MvpAppCompatFragment(), BackButtonListener {
    private var _binding: FragmentForksBinding? = null
    private val binding
        get() = _binding!!

    companion object {
        fun newInstance(userModel: GithubUserModel, repoModel: GithubRepoModel,
                        presenter: ReposPresenter) =
            ForksFragment(userModel, repoModel, presenter)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentForksBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.repoTitle.text = "Информация о репозитории пользователя \"${userModel.login}\":"
        binding.repoId.text = "ID: ${repoModel.id}"
        binding.repoName.text = "${repoModel.name}"
        binding.repoOwnerId.text = "ID владельца: ${repoModel.owner.id}"
        binding.forksNumber.text = "Количество форков: ${repoModel.forksCount}"
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }
}