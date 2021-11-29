package ru.geekbrains.popular.libraries.githubview_homeworks.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.geekbrains.popular.libraries.githubview_homeworks.App
import ru.geekbrains.popular.libraries.githubview_homeworks.R
import ru.geekbrains.popular.libraries.githubview_homeworks.databinding.ActivityMainBinding
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.base.BackButtonListener

class MainActivity: MvpAppCompatActivity(), MainView {
    /** Задание переменных */ //region
    // binding
    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding!!
    // navigator
    private val navigator = AppNavigator(this@MainActivity, R.id.container)
    // moxyPresenter
    private val presenter by moxyPresenter { MainPresenter(App.instance.router) }
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigationHolder.removeNavigator()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backPressed()
    }
}