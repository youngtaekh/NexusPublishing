package kr.young.assets.binance

import kr.young.assets.Constants.Companion.BINANCE_SECRET_KEY
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class Hmac {

    companion object {
        private const val ALGORITHM = "HmacSHA256"
        private const val key = "nsHc6458"

        @JvmStatic
        fun getSignature(data: String): String {
            val hmacSha256: ByteArray
            try {
                val secretKeySpec = SecretKeySpec(BINANCE_SECRET_KEY.toByteArray(), ALGORITHM)
                val mac = Mac.getInstance(ALGORITHM)
                mac.init(secretKeySpec)
                hmacSha256 = mac.doFinal(data.toByteArray())
            } catch (e: Exception) {
                throw RuntimeException("Failed to calculate hmac-sha256", e)
            }
            return byteToString(hmacSha256)
        }

        @JvmStatic
        private fun byteToString(hashes: ByteArray): String {
            val buffer = StringBuffer()

            for (hash in hashes) {
                var d = hash.toInt()
                d += if (d < 0) 256 else 0
                if (d < 16) {
                    buffer.append("0")
                }
                buffer.append(d.toString(16))
            }
            return buffer.toString()
        }
    }
}
