package com.devdreamerx.cinehub.ui.searchfragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.devdreamerx.cinehub.R
import com.devdreamerx.cinehub.data.ShowsRepository
import com.devdreamerx.cinehub.model.SearchSpecificShow
import com.devdreamerx.cinehub.ui.home.recyclerview.adapter.ShowViewHolder
import com.devdreamerx.cinehub.ui.searchfragment.OnSearchViewItemClickListener

class SearchSpecificShowAdapter(
    private val repository: ShowsRepository,
    private val onClickListener: OnSearchViewItemClickListener
) :
    RecyclerView.Adapter<ShowViewHolder>() {

    private var specificShows: ArrayList<SearchSpecificShow?> = arrayListOf()

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val createdView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.show_item, parent, false)
        return ShowViewHolder(createdView)
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        val specificShow: SearchSpecificShow? = specificShows[position]
        holder.bindSearchScreenShows(specificShow, repository, onClickListener)
    }

    override fun getItemCount(): Int {
        return specificShows.size
    }

    fun updateShows(listShows: List<SearchSpecificShow?>?) {
        notifyItemRangeRemoved(0, this.specificShows.size)
        this.specificShows.clear()
        this.specificShows.addAll(ArrayList(listShows))
        this.notifyItemRangeInserted(0, this.specificShows.size)
    }
}