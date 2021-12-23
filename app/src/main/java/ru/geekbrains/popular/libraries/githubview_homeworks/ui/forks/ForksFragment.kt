package ru.geekbrains.popular.libraries.githubview_homeworks.ui.forks

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.popular.libraries.githubview_homeworks.App
import ru.geekbrains.popular.libraries.githubview_homeworks.databinding.FragmentForksBinding
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.UserChooseRepository
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.base.BackButtonListener

class ForksFragment: MvpAppCompatFragment(), ForksView, BackButtonListener {
    /** ЗАДАНИЕ ПЕРЕМЕННЫХ */ //region
    // binding
    private var _binding: FragmentForksBinding? = null
    private val binding
        get() = _binding!!
    // presenter
    private val presenter by moxyPresenter {
        App.instance.initForksSubcomponent()
        App.instance.forksSubcomponent?.provideForksPresenter()!!
    }
    // userChoose
    private val userChoose: UserChooseRepository = App.instance.appComponent.userChoose()
    //endregion

    companion object {
        fun newInstance() = ForksFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForksBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.repoTitle.text =
            "Информация о репозитории пользователя \"${userChoose.getGithubUserModel().login}\":"
        binding.repoId.text = "ID: ${userChoose.getGithubRepoModel().id}"
        binding.repoName.text = "${userChoose.getGithubRepoModel().name}"
        binding.repoOwnerId.text = "ID владельца: ${userChoose.getGithubRepoModel().owner.id}"
        binding.forksNumber.text = "Количество форков: ${userChoose.getGithubRepoModel().forksCount}"
    }

    override fun onResume() {
        super.onResume()

        binding.repoTitle.text =
            "Информация о репозитории пользователя \"${userChoose.getGithubUserModel().login}\":"
        binding.repoId.text = "ID: ${userChoose.getGithubRepoModel().id}"
        binding.repoName.text = "${userChoose.getGithubRepoModel().name}"
        binding.repoOwnerId.text = "ID владельца: ${userChoose.getGithubRepoModel().owner.id}"
        binding.forksNumber.text = "Количество форков: ${userChoose.getGithubRepoModel().forksCount}"
    }

    override fun backPressed(): Boolean {
        presenter?.let { presenter ->
            presenter.backPressed()
        }
        return true
    }
}