package com.gallery.base

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

/**
 * Created by Lalit on 10-03-2018.
 */
@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    private val MY_PERMISSIONS_REQUEST = 1001
    lateinit var permissionListener: PermissionListener

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when (id) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    fun requestPermission(permissions: Array<String>, permissionListener: PermissionListener) {
        this.permissionListener = permissionListener
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                permissions[0])
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    permissions[0])) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        permissions,
                        MY_PERMISSIONS_REQUEST)

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            permissionListener.onPermissionGranted()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    permissionListener.onPermissionGranted()
                } else {
                    permissionListener.onPermissionDenied()
                }
                return
            }
            else -> {
                // Ignore all other requests.
            }
        }
    }
}