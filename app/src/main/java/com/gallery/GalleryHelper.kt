package com.gallery

import android.content.Context
import android.provider.MediaStore
import com.gallery.album.Album
import com.gallery.images.Image
import java.io.File

/**
 * Created by Lalit on 10-03-2018.
 */
object GalleryHelper {
    /**
     * This method is used to get all the Albums from the device which contains the image file.
     * [context] an instance of the caller.
     */
    fun getAlbums(context: Context): ArrayList<Album> {
        val albums = ArrayList<Album>()
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Media.DATA)
        val orderBy = MediaStore.Images.Media.DATE_ADDED + " DESC"
        val cursor = context.contentResolver.query(uri, projection, null, null, orderBy)
        if (cursor != null) {
            var file: File
            while (cursor.moveToNext()) {
                val albumName = cursor.getString(cursor.getColumnIndex(projection[0]))
                val albumThumb = cursor.getString(cursor.getColumnIndex(projection[1]))
                file = File(albumThumb)
                if (file.exists() && !isAlbumExist(albums, albumName)) {
                    albums.add(Album(albumName, albumThumb))
                }
            }
            cursor.close()
        }
        return albums
    }

    /**
     * This method is used to get all the images of the given album.
     */
    fun getImagesOfAlbum(context: Context, bucketPath: String): ArrayList<Image> {
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val selection = MediaStore.Images.Media.BUCKET_DISPLAY_NAME + " =?"
        val orderBy = MediaStore.Images.Media.DATE_ADDED + " DESC"

        val images = ArrayList<Image>()
        val cursor = context.contentResolver.query(uri, projection, selection, arrayOf(bucketPath), orderBy)
        if (cursor != null) {
            var file: File
            while (cursor.moveToNext()) {
                val path = cursor.getString(cursor.getColumnIndex(projection[0]))
                file = File(path)
                if (file.exists() && !isImageExist(images, path)) {
                    images.add(Image(path))
                }
            }
            cursor.close()
        }
        return images
    }

    /**
     * It is used to check that whether the album already exist in the list or not.
     */
    private fun isAlbumExist(albums: ArrayList<Album>, albumName: String): Boolean {
        return albums.any { it.albumName == albumName }
    }

    /**
     * It is used to check that whether the image already exist in the list or not
     */
    private fun isImageExist(images: ArrayList<Image>, imagePath: String): Boolean {
        return images.any { it.imagePath == imagePath }
    }
}