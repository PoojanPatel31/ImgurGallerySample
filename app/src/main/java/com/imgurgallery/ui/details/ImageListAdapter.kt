package com.imgurgallery.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.imgurgallery.databinding.ImageRowBinding
import com.imgurgallery.models.Image

class ImageListAdapter(private val images: MutableList<Image> = mutableListOf()) :
    RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>() {

    private val glideReqOption = RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder(
            ImageRowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(images[position])
    }

    fun loadData(list: List<Image>) {
        images.clear()
        images.addAll(list)
        notifyItemRangeChanged(0, images.size)
    }

    inner class ImageViewHolder(private val row: ImageRowBinding) : ViewHolder(row.root) {

        fun onBind(image: Image) {
            Glide.with(row.image)
                .applyDefaultRequestOptions(glideReqOption).load(image.link)
                .into(row.image)
        }
    }

}