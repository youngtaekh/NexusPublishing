package kr.young.common

import android.util.Log

class Test {
    companion object {
        private const val TAG = "common.Test"
        @JvmStatic
        fun run(): String {
            Log.i(TAG, "run()")
            return TAG
        }
    }
}
