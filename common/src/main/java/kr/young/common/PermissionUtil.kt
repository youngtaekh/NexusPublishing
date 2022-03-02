package kr.young.common

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionUtil {
    companion object {
        private const val TAG = "PermissionUtil"
        const val REQUEST_CODE = 0

        @JvmStatic
        fun check(context: Context, permission: String): Boolean {
            return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        }

        @JvmStatic
        fun check(context: Context, permissions: Array<out String>): Boolean {
            for (permission in permissions) {
                if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
            return true
        }

        @JvmStatic
        fun request(activity: Activity, permissions: Array<out String>) {
            ActivityCompat.requestPermissions(
                activity,
                permissions,
                REQUEST_CODE
            )
        }
    }
}
