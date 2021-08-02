package ru.geekbrains.poplib.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_repository.view.*
import ru.geekbrains.poplib.R
import ru.geekbrains.poplib.mvp.presenter.list.IRepositoryListPresenter
import ru.geekbrains.poplib.mvp.view.list.RepositoryItemView

class ReposotoriesRVAdapter(val presenter: IRepositoryListPresenter) :
    RecyclerView.Adapter<ReposotoriesRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false))

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer, RepositoryItemView {
        override var pos = -1
        override fun setName(text: String) = with(containerView) { tv_name.text = text }
    }
}