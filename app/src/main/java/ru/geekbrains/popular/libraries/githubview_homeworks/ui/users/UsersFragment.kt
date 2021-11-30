package ru.geekbrains.popular.libraries.githubview_homeworks.ui.users

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.popular.libraries.githubview_homeworks.App
import ru.geekbrains.popular.libraries.githubview_homeworks.databinding.FragmentUsersBinding
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.GithubUsersRepository
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.base.BackButtonListener
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.users.adapter.UsersAdapter

class UsersFragment: MvpAppCompatFragment(), UsersView, BackButtonListener {

    /** Задание переменных */ //region
    private val presenter by moxyPresenter {
        UsersPresenter(
            App.instance.router,
            GithubUsersRepository()
        )
    }
    // binding
    private var _binding: FragmentUsersBinding? = null
    private val binding
        get() = _binding!!
    // adapter
    private val adapter by lazy {
        UsersAdapter(presenter.usersListPresenter)
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

        /** Анимированно отобразить список пользователей */
        showListUsers()
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    /** Анимация списка логинов пользователей */ //region
    fun showListUsers() {
        /** Начальная установка угла поля вывода для логина */
        val constraintLayout: ConstraintLayout = binding.usersListContainer
        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)
        constraintSet.setRotationX(binding.usersListRecycler.id,-90f)
        constraintSet.setTransformPivotY(binding.usersListRecycler.id,0f)
        constraintSet.applyTo(constraintLayout)

        /** Анимация появления списка пользвателей */
        Thread {
            // Исходные параметры
            val numberFrames: Int = 30
            val deltaTime: Long = 10L
            val deltaAngle: Float = (90 / numberFrames).toFloat()
            val handler = Handler(Looper.getMainLooper())

            repeat(numberFrames) { counter ->
                Thread.sleep(deltaTime)
                handler.post {
                    val constraintLayout = binding.usersListContainer
                    val constraintSet = ConstraintSet()
                    constraintSet.clone(constraintLayout)
                    constraintSet.setRotationX(
                        binding.usersListRecycler.id,
                        -90 + counter * deltaAngle
                    )
                    constraintSet.applyTo(constraintLayout)
                }
            }
        }.start()
    }
    //endregion
}