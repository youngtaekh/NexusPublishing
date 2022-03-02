package kr.young.common

import android.app.ActivityManager
import android.content.Context

class ApplicationUtil {
    companion object {
        @JvmStatic
        fun isAppOnForeground(context: Context): Boolean {
            val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val processList = manager.runningAppProcesses?: return false
            val name = context.packageName
            for (process in processList) {
                if (process.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                    && process.processName == name) {
                    return true
                }
            }
            return false
        }
    }
}
