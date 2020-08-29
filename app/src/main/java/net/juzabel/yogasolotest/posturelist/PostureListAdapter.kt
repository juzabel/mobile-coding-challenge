package net.juzabel.yogasolotest.posturelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.clear
import coil.load
import kotlinx.android.synthetic.main.item_posture.view.*
import net.juzabel.domain.feature.posturelist.model.Posture
import net.juzabel.yogasolotest.R

class PostureListAdapter : RecyclerView.Adapter<PostureListAdapter.PostureListViewHolder>() {
    var list: List<Posture> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostureListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_posture, parent, false)
        return PostureListViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PostureListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class PostureListViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {

        fun bind(posture: Posture) {
            v.itemPostureImage.load(posture.picture) {
                memoryCacheKey(posture.id)
                crossfade(true)
                placeholder(R.color.colorAccent)
            }
            v.itemPostureName.text = posture.name
            v.itemPostureDescription.text = posture.description
        }
    }
}