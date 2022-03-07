package kr.young.common

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    fun test() {
        val date = Date()
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:SS.sss", Locale.getDefault())
        val str = simpleDateFormat.format(date)
        DebugLog.i("DateUtil", str)
    }

    companion object {
        private const val TAG = "DateUtil"
        private const val FORMATTED_STRING = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"

        @JvmStatic
        fun toFormattedString(time: Long): String {
            return toFormattedString(time, FORMATTED_STRING)
        }

        @JvmStatic
        fun toFormattedString(time: Long, formattedString: String): String {
            val date = Date(time)
            val dateFormat = SimpleDateFormat(formattedString, Locale.getDefault())
//            dateFormat.timeZone = TimeZone.getTimeZone("GMT+09:00")
            val dateString = dateFormat.format(date)
            DebugLog.i(TAG, dateString)
            return dateString
        }
    }
}
