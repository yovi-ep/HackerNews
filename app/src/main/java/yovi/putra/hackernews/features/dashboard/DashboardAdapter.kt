package yovi.putra.hackernews.features.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.story_item.view.*
import yovi.putra.hackernews.R
import yovi.putra.hackernews.core.utils.fromHtml
import yovi.putra.hackernews.core.utils.toDateString
import yovi.putra.hackernews.data.entity.Story

class DashboardAdapter(private val listener: (Story) -> Unit)
    : RecyclerView.Adapter<DashboardAdapter.VHolder>() {

    private var item = mutableListOf<Story>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder =
        VHolder(LayoutInflater
            .from(parent.context).inflate(R.layout.story_item, parent, false))

    fun addItem(data: Story?) {
        data?.let {
            item.add(it)
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
                tv_title.text = data.title?.fromHtml()
                tv_commenter.text = (data.kids?.size ?: 0).toString()
                tv_score.text = (data.score ?: 0).toString()
                tv_date.text = data.time?.toDateString()
                setOnClickListener { listener(data) }
            }
        }
    }
}