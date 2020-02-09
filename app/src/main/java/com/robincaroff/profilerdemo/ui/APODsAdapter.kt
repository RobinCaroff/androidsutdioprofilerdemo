package com.robincaroff.profilerdemo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.robincaroff.profilerdemo.R
import com.robincaroff.profilerdemo.model.APOD
import kotlinx.android.synthetic.main.item_apod.view.*

class APODsAdapter(private val onAPODClicked: ((APOD) -> Unit)) :
    RecyclerView.Adapter<APODsAdapter.ViewHolder>() {

    private var apods: List<APOD> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_apod, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = apods.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(apods[position], onAPODClicked)
    }

    fun updateData(apodList: List<APOD>) {
        apods = apodList
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val title: TextView by lazy { itemView.textview_apod_title }
        private val explanation: TextView by lazy { itemView.textview_apod_explanation }
        private val image: AppCompatImageView by lazy { itemView.imageview_apod_image }

        fun bindViewHolder(apod: APOD, onAPODClicked: ((APOD) -> Unit)) {
            title.text = apod.title
            explanation.text = apod.explanation

            Glide.with(itemView.context)
                .load(apod.url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(image)

            itemView.setOnClickListener { onAPODClicked(apod) }
        }
    }
}
