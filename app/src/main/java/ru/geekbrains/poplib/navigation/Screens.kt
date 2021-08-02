package ru.geekbrains.poplib.navigation

import androidx.fragment.app.Fragment
import ru.geekbrains.poplib.di.repository.RepositorySubcomponent
import ru.geekbrains.poplib.mvp.model.entity.GithubRepository
import ru.geekbrains.poplib.mvp.model.entity.GithubUser
import ru.geekbrains.poplib.ui.App
import ru.geekbrains.poplib.ui.fragment.RepositoryFragment
import ru.geekbrains.poplib.ui.fragment.UserFragment
import ru.geekbrains.poplib.ui.fragment.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class UsersScreen() : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }

    class UserScreen(val user: GithubUser) : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            var repositorySubcomponent: RepositorySubcomponent? =
                App.instance.initRepositorySubcomponent()
            return UserFragment.newInstance(user).apply {
                repositorySubcomponent?.inject(this)
            }
        }
    }

    class RepositoryScreen(val user: GithubRepository) : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            var repositorySubcomponent: RepositorySubcomponent? =
                App.instance.initRepositorySubcomponent()
            return RepositoryFragment.newInstance(user).apply {
                repositorySubcomponent?.inject(this)
            }
        }
    }
}