package kr.young.common

import android.util.Log

class DebugLog {
    companion object {
        private const val MODULE_TAG = "DebugLog"
        @JvmStatic var isModuleTag = false
        @JvmStatic var isDebug = true

        @JvmStatic
        fun logInit(isDebug: Boolean) {
            logInit(this.isModuleTag, isDebug)
        }

        @JvmStatic
        fun logInit(isModuleTag: Boolean, isDebug: Boolean) {
            this.isModuleTag = isModuleTag
            this.isDebug = isDebug
        }

        @JvmStatic
        fun v(tag: String, message: String) {
            if (isDebug) {
                if (isModuleTag) {
                    Log.v(MODULE_TAG, message)
                } else {
                    Log.v(tag, message)
                }
            }
        }

        @JvmStatic
        fun d(tag: String, message: String) {
            if (isDebug) {
                if (isModuleTag) {
                    Log.d(MODULE_TAG, message)
                } else {
                    Log.d(tag, message)
                }
            }
        }

        @JvmStatic
        fun i(tag: String, message: String) {
            if (isDebug) {
                if (isModuleTag) {
                    Log.i(MODULE_TAG, message)
                } else {
                    Log.i(tag, message)
                }
            }
        }

        @JvmStatic
        fun w(tag: String, message: String) {
            if (isDebug) {
                if (isModuleTag) {
                    Log.w(MODULE_TAG, message)
                } else {
                    Log.w(tag, message)
                }
            }
        }

        @JvmStatic
        fun e(tag: String, message: String) {
            if (isDebug) {
                if (isModuleTag) {
                    Log.e(MODULE_TAG, message)
                } else {
                    Log.e(tag, message)
                }
            }
        }
    }
}
