package com.gallery.images

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.gallery.GalleryHelper
import com.gallery.R
import com.gallery.base.BaseActivity
import kotlinx.android.synthetic.main.activity_images.*

class ImagesActivity : BaseActivity() {

    private lateinit var mImages: ArrayList<Image>
    private lateinit var mAdapter: ImagesAdapter

    companion object {
        private val ARG_ALBUM_NAME = "arg_album_name"

        /**
         * This method is used to lauch/start the ImagesActivity.
         * [context] it is an instance of the caller.
         * [albumName] it is the name of the album for which you want to see all the images.
         */
        fun launchActivity(context: Context, albumName: String) {
            val intent = Intent(context, ImagesActivity::class.java)
            intent.putExtra(ARG_ALBUM_NAME, albumName)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)

        supportActionBar?.title = intent.getStringExtra(ARG_ALBUM_NAME)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        init()
    }

    /**
     * This method is used to initialize the views.
     */
    private fun init() {
        mImages = ArrayList()
        mAdapter = ImagesAdapter(mImages)
        act_images_recycler_view.layoutManager = GridLayoutManager(this, 3)
        act_images_recycler_view.adapter = mAdapter

        setupImages()
    }

    /**
     * This method is used to get all the images of the selected album
     * and show it in the adapter.
     */
    private fun setupImages() {
        val albumName = intent.getStringExtra(ARG_ALBUM_NAME)
        mImages.addAll(GalleryHelper.getImagesOfAlbum(this, albumName))
        if (mImages.isNotEmpty()) {
            mAdapter.notifyDataSetChanged()
        }
    }
}
