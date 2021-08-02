package ru.geekbrains.poplib.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrains.poplib.mvp.model.entity.GithubUser
import ru.geekbrains.poplib.mvp.model.repo.IGithubUsersRepo
import ru.geekbrains.poplib.mvp.presenter.list.IUserListPresenter
import ru.geekbrains.poplib.mvp.view.UsersView
import ru.geekbrains.poplib.mvp.view.list.UserItemView
import ru.geekbrains.poplib.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UsersPresenter() : MvpPresenter<UsersView>() {

    @Inject lateinit var usersRepo: IGithubUsersRepo
    @Inject lateinit var router: Router
    @Inject lateinit var mainThreadScheduler: Scheduler

    constructor(_userRepo:IGithubUsersRepo, _router: Router, _mainThreadScheduler: Scheduler) : this() {
        usersRepo = _userRepo
        router = _router
        mainThreadScheduler = _mainThreadScheduler
    }

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let { view.loadAvatar(it) }
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            router.navigateTo(Screens.UserScreen(user))
        }
    }

    fun loadData() {
        usersRepo.getUsers()
            .observeOn(mainThreadScheduler)
            .subscribe({ users ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(users)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        viewState.release()
    }
}
