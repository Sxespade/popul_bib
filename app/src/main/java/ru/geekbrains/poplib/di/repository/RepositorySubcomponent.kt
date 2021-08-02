package ru.geekbrains.poplib.di.repository

import dagger.Subcomponent
import ru.geekbrains.poplib.di.repository.module.RepositoryModule
import ru.geekbrains.poplib.mvp.presenter.RepositoryPresenter
import ru.geekbrains.poplib.mvp.presenter.UserPresenter
import ru.geekbrains.poplib.ui.fragment.RepositoryFragment
import ru.geekbrains.poplib.ui.fragment.UserFragment

@RepositoryScope
@Subcomponent(
    modules = [
        RepositoryModule::class
    ]
)
interface RepositorySubcomponent {
    fun inject(userPresenter: UserPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)

    fun inject(userFragment: UserFragment)
    fun inject(userRepository: RepositoryFragment)
}