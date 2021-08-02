package ru.geekbrains.poplib.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrains.poplib.mvp.model.entity.GithubRepository
import ru.geekbrains.poplib.mvp.model.entity.GithubUser
import ru.geekbrains.poplib.mvp.model.repo.IGithubRepositoriesRepo
import ru.geekbrains.poplib.mvp.presenter.list.IRepositoryListPresenter
import ru.geekbrains.poplib.mvp.view.UserView
import ru.geekbrains.poplib.mvp.view.list.RepositoryItemView
import ru.geekbrains.poplib.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UserPresenter() :
    MvpPresenter<UserView>() {

    private lateinit var user: GithubUser
    private lateinit var mainThreadScheduler: Scheduler
    private lateinit var repositoriesRepo: IGithubRepositoriesRepo
    private lateinit var router: Router

    constructor(_repositoriesRepo: IGithubRepositoriesRepo, _router: Router, _mainThreadScheduler: Scheduler, _user: GithubUser) : this() {
        user = _user
        repositoriesRepo = _repositoriesRepo
        router = _router
        mainThreadScheduler = _mainThreadScheduler
    }

    class RepositoriesListPresenter : IRepositoryListPresenter {
        val repositories = mutableListOf<GithubRepository>()
        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null
        override fun getCount() = repositories.size

        override fun bindView(view: RepositoryItemView) {
            val repository = repositories[view.pos]
            repository.name?.let { view.setName(it) }
        }
    }

    val repositoriesListPresenter = RepositoriesListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        repositoriesListPresenter.itemClickListener = { itemView ->
            val repository = repositoriesListPresenter.repositories[itemView.pos]
            router.navigateTo(Screens.RepositoryScreen(repository))
        }
    }

    fun loadData() {
        repositoriesRepo.getRepositories(user)
            .observeOn(mainThreadScheduler)
            .subscribe({ repositories ->
                repositoriesListPresenter.repositories.clear()
                repositoriesListPresenter.repositories.addAll(repositories)
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
