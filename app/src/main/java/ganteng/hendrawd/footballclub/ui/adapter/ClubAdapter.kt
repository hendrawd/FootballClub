package ganteng.hendrawd.footballclub.ui.adapter

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import ganteng.hendrawd.footballclub.model.ClubItem
import ganteng.hendrawd.footballclub.ui.ankolayout.ClubItemUI
import ganteng.hendrawd.footballclub.util.SingleLiveEvent
import ganteng.hendrawd.footballclub.util.loadWithGlide
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.sdk25.coroutines.onClick

class ClubAdapter(
        private val clubItemList: List<ClubItem>,
        private val clubItemClickObserver: SingleLiveEvent<SparseArray<*>>
) : RecyclerView.Adapter<ClubAdapter.ViewHolder>(), View.OnClickListener {
    override fun onClick(v: View?) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(ClubItemUI(), parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(clubItemList[position], clubItemClickObserver)
    }

    override fun getItemCount(): Int = clubItemList.size

    class ViewHolder(private val ui: ClubItemUI, parent: ViewGroup) : RecyclerView.ViewHolder(ui.createView(AnkoContext.create(parent.context, parent))) {
        fun bindItem(clubItem: ClubItem, clubItemClickObserver: SingleLiveEvent<SparseArray<*>>) {
            ui.tvName.text = clubItem.name
            ui.ivLogo.loadWithGlide(clubItem.image)
            itemView.onClick {
                val data = SparseArray<Any>()
                data.put(0, clubItem)
                data.put(1, ui.ivLogo)
                data.put(2, ui.tvName)
                clubItemClickObserver.value = data
            }
        }
    }
}