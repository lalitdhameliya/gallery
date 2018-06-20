package com.gallery.swipeimages

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.gallery.R
import com.gallery.base.BaseActivity
import com.gallery.images.Image
import kotlinx.android.synthetic.main.activity_swipe_image.*

class SwipeImageActivity : BaseActivity() {

    companion object {
        private const val ARG_IMAGES_ARRAY = "arg_images_array"
        private const val ARG_POSITION = "arg_position"

        /**
         * This method is used to launch the SwipeImageActivity.
         * [context] an instance of the caller.
         * [position] a position which we will show an image for.
         */
        fun launchActivity(context: Context, images: ArrayList<Image>, position: Int) {
            val intent = Intent(context, SwipeImageActivity::class.java)
            intent.putParcelableArrayListExtra(ARG_IMAGES_ARRAY, images)
            intent.putExtra(ARG_POSITION, position)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_image)

        setSupportActionBar(act_swipe_image_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        init()
    }

    /**
     * This method is used to setup the view pager with the images
     * and move the current position to the selected position.
     */
    private fun init() {
        val images = intent.getParcelableArrayListExtra<Image>(ARG_IMAGES_ARRAY)
        val position = intent.getIntExtra(ARG_POSITION, 0)
        if (images == null) finish()
        act_swipe_image_view_pager.adapter = SwipePagerAdapter(supportFragmentManager, images)
        act_swipe_image_view_pager.currentItem = position
    }
}
