package ru.geekbrains.poplib.mvp.presenter

import moxy.MvpPresenter
import ru.geekbrains.poplib.mvp.model.entity.GithubRepository
import ru.geekbrains.poplib.mvp.view.RepositoryView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class RepositoryPresenter() : MvpPresenter<RepositoryView>() {

    private lateinit var router: Router
    private lateinit var githubRepository: GithubRepository

    constructor(_githubRepository: GithubRepository, _router: Router) : this() {
        router = _router
        githubRepository = _githubRepository
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setId(githubRepository.id ?: "")
        viewState.setTitle(githubRepository.name ?: "")
        viewState.setForksCount((githubRepository.forksCount ?: 0).toString())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        System.out.println("onDestroy presenter")
    }
}
