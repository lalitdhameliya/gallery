package com.gallery.album

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.gallery.R
import com.gallery.images.ImagesActivity
import com.gallery.util.extensions.inflate
import kotlinx.android.synthetic.main.item_album.view.*

/**
 * Created by Lalit on 10-03-2018.
 */
class AlbumAdapter(private val albums: ArrayList<Album>) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder
            = AlbumViewHolder(parent.inflate(R.layout.item_album))

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) = holder.bind()
    override fun getItemCount(): Int = albums.size

    inner class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() = with(itemView) {
            val album = albums[position]
            Glide.with(context).load(album.albumThumb).into(item_album_thumb)
            item_album_name.text = album.albumName

            item_album_root.setOnClickListener {
                ImagesActivity.launchActivity(context, album.albumName)
            }
        }
    }
}