package kr.young.assets

import kr.young.assets.Constants.Companion.BINANCE

class AssetsManager {

    fun getSystemStatus() {
        BinanceAssets.getSystemStatus()
    }

    fun getCoinInfo() {
        BinanceAssets.getCoinInfo()
    }

    fun getAssets(key: String) {
        when (key) {
            BINANCE -> BinanceAssets.getSystemStatus()
        }
    }

    private object Holder {
        val INSTANCE = AssetsManager()
    }

    companion object {
        private const val TAG = "AssetsManager"
        val instance: AssetsManager by lazy { Holder.INSTANCE }
    }
}
