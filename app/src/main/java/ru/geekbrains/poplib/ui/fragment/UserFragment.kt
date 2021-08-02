package ru.geekbrains.poplib.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.core.Scheduler
import kotlinx.android.synthetic.main.fragment_user.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.poplib.R
import ru.geekbrains.poplib.di.repository.RepositorySubcomponent
import ru.geekbrains.poplib.mvp.model.entity.GithubUser
import ru.geekbrains.poplib.mvp.model.repo.IGithubRepositoriesRepo
import ru.geekbrains.poplib.mvp.presenter.UserPresenter
import ru.geekbrains.poplib.mvp.view.UserView
import ru.geekbrains.poplib.ui.App
import ru.geekbrains.poplib.ui.BackButtonListener
import ru.geekbrains.poplib.ui.adapter.ReposotoriesRVAdapter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    @Inject
    lateinit var mainThreadScheduler: Scheduler
    @Inject
    lateinit var repositoriesRepo: IGithubRepositoriesRepo
    @Inject
    lateinit var router: Router

    companion object {
        private const val USER_ARG = "user"

        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    var repositorySubcomponent: RepositorySubcomponent? = null

    var adapter: ReposotoriesRVAdapter? = null
    val presenter: UserPresenter by moxyPresenter {
        repositorySubcomponent = App.instance.initRepositorySubcomponent()
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser
        UserPresenter(repositoriesRepo,router,mainThreadScheduler,user).apply {
            repositorySubcomponent?.inject(this)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        View.inflate(context, R.layout.fragment_user, null)

    override fun init() {
        rv_repositories.layoutManager = LinearLayoutManager(context)
        adapter = ReposotoriesRVAdapter(presenter.repositoriesListPresenter)
        rv_repositories.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()

    override fun release() {
        repositorySubcomponent = null
        App.instance.releaseRepositorySubcomponent()
    }
}