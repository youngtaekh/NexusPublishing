package kr.young.assets.util

import java.net.URLEncoder

class Query {
    companion object {
        private const val TAG = "Query"

        @JvmStatic
        fun urlEncodeUTF8(s: String): String {
            try {
                return URLEncoder.encode(s, "UTF-8")
            } catch (e: UnsupportedOperationException) {
                throw UnsupportedOperationException(e)
            }
        }

        @JvmStatic
        fun getQueryString(map: Map<String, Any>): String {
            val builder = StringBuilder()
            for (entry in map.entries) {
                if (builder.isNotEmpty()) {
                    builder.append("&")
                }
                builder.append(String.format("%s=%s", urlEncodeUTF8(entry.key), urlEncodeUTF8(entry.value.toString())))
            }
            return builder.toString()
        }
    }
}
