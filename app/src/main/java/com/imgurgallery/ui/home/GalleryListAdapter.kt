package com.imgurgallery.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.imgurgallery.R
import com.imgurgallery.models.GalleryImages
import com.imgurgallery.util.DateTimeUtil

class GalleryListAdapter(private val onClick: (gallery: GalleryImages) -> Unit) :
    RecyclerView.Adapter<GalleryListAdapter.RowViewHolder>() {

    companion object {
        const val gridSize = 3
        const val listSize = 1
    }

    private val galleries: MutableList<GalleryImages> = mutableListOf()
    private val glideReqOption = RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

    fun loadImages(images: List<GalleryImages>) {
        galleries.clear()
        galleries.addAll(images)
        notifyItemRangeChanged(0, galleries.size)
    }

    override fun getItemCount(): Int {
        return galleries.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder =
        LayoutInflater.from(parent.context).inflate(R.layout.gallery_row, parent, false).let {
            RowViewHolder(it)
        }

    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
        holder.onBind(galleries[position], onClick)
    }

    inner class RowViewHolder(private val view: View) : ViewHolder(view) {

        private val image: ImageView = view.findViewById(R.id.image)
        private val title: TextView = view.findViewById(R.id.title)
        private val postDate: TextView = view.findViewById(R.id.post_date)
        private val imageCount: TextView = view.findViewById(R.id.image_count)

        fun onBind(item: GalleryImages, onClick: (gallery: GalleryImages) -> Unit) {
            Glide.with(image).applyDefaultRequestOptions(glideReqOption)
                .load(item.images.first().link).into(image)
            title.text = item.gallery.title
            postDate.text = DateTimeUtil.format(item.gallery.datetime)
            if (item.images.count() > 1) {
                imageCount.visibility = View.VISIBLE
                imageCount.text = item.images.count().toString()
            } else {
                imageCount.visibility = View.GONE
            }

            view.setOnClickListener { onClick(item) }
        }
    }
}