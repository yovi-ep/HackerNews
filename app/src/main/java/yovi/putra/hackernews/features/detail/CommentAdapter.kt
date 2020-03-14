package yovi.putra.hackernews.features.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.comment_item.view.*
import kotlinx.android.synthetic.main.story_item.view.tv_commenter
import yovi.putra.hackernews.R
import yovi.putra.hackernews.core.utils.fromHtml
import yovi.putra.hackernews.core.utils.toDateString
import yovi.putra.hackernews.data.entity.Comment

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.VHolder>() {

    private var item = mutableListOf<Comment>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder =
        VHolder(LayoutInflater
            .from(parent.context).inflate(R.layout.comment_item, parent, false))

    fun addItem(data: Comment?) {
        data?.let {
            item.add(it)
            this.notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: VHolder, position: Int) =
        holder.binding(item[position])

    class VHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun binding(data: Comment) {

            containerView.apply {
                tv_commenter.text = data.by
                tv_text.text = data.text?.fromHtml()
                tv_date.text = data.time?.toDateString()
            }
        }
    }
}