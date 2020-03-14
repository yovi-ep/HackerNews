package yovi.putra.hackernews.features.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.story_item.view.*
import yovi.putra.hackernews.R
import yovi.putra.hackernews.data.entity.Story

class DashboardAdapter(private val listener: (Story) -> Unit)
    : RecyclerView.Adapter<DashboardAdapter.VHolder>() {

    private var item = mutableListOf<Story>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder =
        VHolder(LayoutInflater
            .from(parent.context).inflate(R.layout.story_item, parent, false))

    fun setItem(data: List<Story>?) {
        data?.let {
            item.addAll(it)
            this.notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: VHolder, position: Int) =
        holder.binding(item[position], listener)

    class VHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun binding(data: Story, listener: (Story) -> Unit) {

            containerView.apply {
                tv_title.text = data.title
                tv_comment.text = (data.kids ?: 0).toString()
                tv_score.text = (data.score ?: 0).toString()
                setOnClickListener { listener(data) }
            }
        }
    }
}