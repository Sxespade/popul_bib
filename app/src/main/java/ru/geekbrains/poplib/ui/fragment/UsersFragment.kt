package ru.geekbrains.poplib.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_users.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.poplib.R
import ru.geekbrains.poplib.di.user.UserSubcomponent
import ru.geekbrains.poplib.mvp.presenter.UsersPresenter
import ru.geekbrains.poplib.mvp.view.UsersView
import ru.geekbrains.poplib.ui.App
import ru.geekbrains.poplib.ui.BackButtonListener
import ru.geekbrains.poplib.ui.adapter.UsersRVAdapter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    var userSubcomponent: UserSubcomponent? = null

    val presenter: UsersPresenter by moxyPresenter {
        userSubcomponent = App.instance.initUserSubcomponent()
        UsersPresenter().apply {
            userSubcomponent?.inject(this)
        }
    }

    var adapter: UsersRVAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        View.inflate(context, R.layout.fragment_users, null)

    override fun init() {
        rv_users.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter).apply {
            userSubcomponent?.inject(this)
        }
        rv_users.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()

    override fun release() {
        userSubcomponent = null
        App.instance.releaseUserSubcomponent()
    }
}