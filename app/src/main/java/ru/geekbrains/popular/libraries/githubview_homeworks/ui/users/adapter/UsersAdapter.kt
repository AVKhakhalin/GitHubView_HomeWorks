package ru.geekbrains.popular.libraries.githubview_homeworks.ui.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.popular.libraries.githubview_homeworks.databinding.ItemUserBinding
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.users.UserItemView
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.users.UsersPresenter

class UsersAdapter(
    private val presenter: UsersPresenter.UsersListPresenter
): RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener() }
        }
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount(): Int {
        return presenter.getCount()
    }

    inner class UserViewHolder(private val vb: ItemUserBinding): RecyclerView.ViewHolder(vb.root),
        UserItemView {

        override var pos: Int = -1

        override fun setLogin(login: String) {
            vb.tvLogin.text = login
        }
    }
}