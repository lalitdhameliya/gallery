package com.gallery.images

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.gallery.R
import com.gallery.swipeimages.SwipeImageActivity
import com.gallery.util.extensions.inflate
import kotlinx.android.synthetic.main.item_images.view.*

/**
 * Created by Lalit on 10-03-2018.
 */
class ImagesAdapter(private val images: ArrayList<Image>) : RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder
            = ImageViewHolder(parent.inflate(R.layout.item_images))

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) = holder.bind()
    override fun getItemCount(): Int = images.size

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() = with(itemView) {
            val image = images[position]
            Glide.with(context).load(image.imagePath).placeholder(R.drawable.ic_launcher_background).into(item_images_iv_image)

            item_images_root.setOnClickListener {
                SwipeImageActivity.launchActivity(context,images,position)
            }
        }
    }
}