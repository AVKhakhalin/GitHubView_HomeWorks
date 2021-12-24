package ru.geekbrains.popular.libraries.githubview_homeworks.ui.forks

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.popular.libraries.githubview_homeworks.App
import ru.geekbrains.popular.libraries.githubview_homeworks.R
import ru.geekbrains.popular.libraries.githubview_homeworks.databinding.FragmentForksBinding
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.UserChooseRepository
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.base.BackButtonListener
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.utils.viewBinding

class ForksFragment : MvpAppCompatFragment(R.layout.fragment_forks), ForksView, BackButtonListener {
    /** ЗАДАНИЕ ПЕРЕМЕННЫХ */ //region
    // binding
    private val binding by viewBinding<FragmentForksBinding>()

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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.repoTitle.text =
            "${
                requireActivity().getString(
                    R.string.forks_fragment_repo_title_information_text
                )
            } \"${
                userChoose.getGithubUserModel().login
            }\":"
        binding.repoId.text = "${requireActivity().getString(R.string.forks_fragment_id_text)} ${
            userChoose.getGithubRepoModel().id
        }"
        binding.repoName.text = "${
            requireActivity().getString(
                R.string.forks_fragment_name_repo_text
            )
        } ${
            userChoose.getGithubRepoModel().name
        }"
        binding.repoOwnerId.text = "${
            requireActivity().getString(
                R.string.forks_fragment_id_owner_text
            )
        } ${
            userChoose.getGithubRepoModel().owner.id
        }"
        binding.forksNumber.text = "${
            requireActivity().getString(
                R.string.forks_fragment_forks_number_text
            )
        } ${
            userChoose.getGithubRepoModel().forksCount
        }"
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()

        binding.repoTitle.text =
            "${
                requireActivity().getString(
                    R.string.forks_fragment_repo_title_information_text
                )
            } \"${
                userChoose.getGithubUserModel().login
            }\":"
        binding.repoId.text = "${requireActivity().getString(R.string.forks_fragment_id_text)} ${
            userChoose.getGithubRepoModel().id
        }"
        binding.repoName.text = "${
            requireActivity().getString(
                R.string.forks_fragment_name_repo_text
            )
        } ${
            userChoose.getGithubRepoModel().name
        }"
        binding.repoOwnerId.text = "${
            requireActivity().getString(
                R.string.forks_fragment_id_owner_text
            )
        } ${
            userChoose.getGithubRepoModel().owner.id
        }"
        binding.forksNumber.text = "${
            requireActivity().getString(
                R.string.forks_fragment_forks_number_text
            )
        } ${
            userChoose.getGithubRepoModel().forksCount
        }"
    }

    override fun backPressed(): Boolean {
        presenter?.let { presenter ->
            presenter.backPressed()
        }
        return true
    }
}