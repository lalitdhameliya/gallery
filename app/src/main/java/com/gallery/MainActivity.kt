package com.gallery

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.gallery.album.Album
import com.gallery.album.AlbumAdapter
import com.gallery.base.BaseActivity
import com.gallery.base.PermissionListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), PermissionListener {
    private lateinit var mAlbums: ArrayList<Album>
    private lateinit var mAdapter: AlbumAdapter

    override fun onPermissionGranted() {
        mAlbums.addAll(GalleryHelper.getAlbums(this))
        if (mAlbums.isNotEmpty()) {
            mAdapter.notifyDataSetChanged()
        }
    }

    override fun onPermissionDenied() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        mAlbums = ArrayList()
        mAdapter = AlbumAdapter(mAlbums)
        act_main_album_recycler_view.layoutManager = GridLayoutManager(this, 3)
        act_main_album_recycler_view.adapter = mAdapter

        requestPermission(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE), this)
    }
}
