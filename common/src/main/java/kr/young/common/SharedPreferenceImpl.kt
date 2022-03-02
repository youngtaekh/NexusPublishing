package kr.young.common

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceImpl private constructor(mContext: Context) {
    private var sharedPreferences: SharedPreferences? = null

    init {
        sharedPreferences = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }

    fun putValue(key: String, value: Boolean) {
        val editor = sharedPreferences?.edit()
        editor?.putBoolean(key, value)
        editor?.apply()
    }

    fun putValue(key: String, value: Int) {
        val editor = sharedPreferences?.edit()
        editor?.putInt(key, value)
        editor?.apply()
    }

    fun putValue(key: String, value: Long) {
        val editor = sharedPreferences?.edit()
        editor?.putLong(key, value)
        editor?.apply()
    }

    fun putValue(key: String, value: Float) {
        val editor = sharedPreferences?.edit()
        editor?.putFloat(key, value)
        editor?.apply()
    }

    fun putValue(key: String, value: String) {
        val editor = sharedPreferences?.edit()
        editor?.putString(key, value)
        editor?.apply()
    }

    fun putValue(key: String, value: Set<String>) {
        val editor = sharedPreferences?.edit()
        editor?.putStringSet(key, value)
        editor?.apply()
    }

    fun remove(key: String) {
        val editor = sharedPreferences?.edit()
        editor?.remove(key)
        editor?.apply()
    }

    fun clear() {
        val editor = sharedPreferences?.edit()
        editor?.clear()
        editor?.apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences!!.getBoolean(key, false)
    }

    fun getInt(key: String): Int {
        return sharedPreferences!!.getInt(key, -1)
    }

    fun getLong(key: String): Long {
        return sharedPreferences!!.getLong(key, -1L)
    }

    fun getFloat(key: String): Float {
        return sharedPreferences!!.getFloat(key, -1f)
    }

    fun getString(key: String): String {
        return sharedPreferences!!.getString(key, "")!!
    }

    fun getStringSet(key: String): Set<String>? {
        return sharedPreferences!!.getStringSet(key, null)
    }

    companion object {
        private const val NAME = "spImpl.db"

        @Volatile
        private var instance: SharedPreferenceImpl? = null

        @JvmStatic
        fun getInstance(context: Context): SharedPreferenceImpl {
            if (instance == null) {
                instance = SharedPreferenceImpl(context.applicationContext)
            }
            return instance!!
        }
    }
}
