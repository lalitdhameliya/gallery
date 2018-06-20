package com.gallery.swipeimages

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

import com.gallery.R
import com.gallery.images.Image
import com.gallery.util.extensions.hide
import kotlinx.android.synthetic.main.fragment_swipe_image.*
import java.io.File
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 * Use the [SwipeImageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SwipeImageFragment : Fragment() {

    private var mImage: Image? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mImage = arguments.getParcelable(ARG_IMAGE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_swipe_image, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this)
                .load(mImage?.imagePath)
                .listener(object : RequestListener<String?, GlideDrawable?> {
                    override fun onException(e: Exception?, model: String?, target: Target<GlideDrawable?>?, isFirstResource: Boolean): Boolean {
                        frag_swipe_image_progress_bar.hide()
                        return false
                    }

                    override fun onResourceReady(resource: GlideDrawable?, model: String?, target: Target<GlideDrawable?>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                        frag_swipe_image_progress_bar.hide()
                        return false
                    }
                })
                .placeholder(R.drawable.ic_launcher_background)
                .into(frag_swipe_iv_image)
        frag_swipe_image_iv_delete.setOnClickListener {
            val file = File(mImage?.imagePath)
            if (file.exists()) {
                file.delete()
            }
        }
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_IMAGE = "param1"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SwipeImageFragment.
         */
        fun newInstance(image: Image): SwipeImageFragment {
            val fragment = SwipeImageFragment()
            val args = Bundle()
            args.putParcelable(ARG_IMAGE, image)
            fragment.arguments = args
            return fragment
        }
    }
}