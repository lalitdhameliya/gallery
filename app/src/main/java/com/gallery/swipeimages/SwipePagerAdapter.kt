package com.gallery.swipeimages

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.gallery.images.Image

/**
 * Created by Lalit on 11-03-2018.
 */
class SwipePagerAdapter(fragManager: FragmentManager, private val images: ArrayList<Image>)
    : FragmentStatePagerAdapter(fragManager) {
    override fun getItem(position: Int): Fragment {
        return SwipeImageFragment.newInstance(images[position])
    }

    override fun getCount(): Int = images.size
}