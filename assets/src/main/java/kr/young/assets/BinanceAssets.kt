package kr.young.assets

import android.annotation.SuppressLint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kr.young.assets.Constants.Companion.BINANCE_API_KEY
import kr.young.assets.Constants.Companion.BINANCE_API_URL
import kr.young.assets.binance.BinanceWalletAPI
import kr.young.assets.binance.SignatureGenerator
import kr.young.assets.util.Query
import kr.young.common.DateUtil
import kr.young.common.DebugLog
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.Timestamp

class BinanceAssets {
    companion object {
        private const val TAG = "BinanceAssets"
        private const val SYSTEM_STATUS = "systemStatus"
        private const val COIN_INFO = "coinInfo"
        private const val ACCOUNT_SNAPSHOT = "accountSnapshot"

        private fun getService(baseUrl: String): BinanceWalletAPI {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(BinanceWalletAPI::class.java)
        }

        @SuppressLint("CheckResult")
        @JvmStatic
        private fun restThread(type: String, paramMap: MutableMap<String, Any>?) {
            val ob = Observable.just(BINANCE_API_URL)
            ob.subscribeOn(Schedulers.computation())
                .map {
                    val service = getService(it)
                    when (type) {
                        SYSTEM_STATUS -> { service.getSystemStatus() }
                        COIN_INFO -> { service.getCoinInfo(BINANCE_API_KEY, paramMap!!) }
                        ACCOUNT_SNAPSHOT -> { service.getSystemStatus() }
                        else -> { service.getSystemStatus() }
                    }
                }
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    DebugLog.i("$TAG 123", it.toString())
                }
        }

        @SuppressLint("CheckResult")
        @JvmStatic
        fun getSystemStatus() {
            val ob = Observable.just(BINANCE_API_URL)
            ob.subscribeOn(Schedulers.computation())
                .subscribe {
                    val service = getService(it)
                    service.getSystemStatus()
                        .doOnError { DebugLog.e(TAG, it.message!!) }
                        .subscribeBy(
                            onNext = {
                                DebugLog.i(TAG, it.toString())
                            },
                            onError = { it.printStackTrace() }
                        )
                }
        }

        @SuppressLint("CheckResult")
        @JvmStatic
        fun getCoinInfo() {
            val timestamp = System.currentTimeMillis()
            val paramMap = mutableMapOf<String, Any>()
            paramMap["timestamp"] = timestamp
            val query = Query.getQueryString(paramMap)
            val signature = SignatureGenerator.getSignature(query)
            paramMap["signature"] = signature

            val ob = Observable.just(BINANCE_API_URL)
            ob.subscribeOn(Schedulers.computation())
                .subscribe {
                    val service = getService(it)
                    service.getCoinInfo(BINANCE_API_KEY, paramMap)
                        .doOnError { DebugLog.e(TAG, it.message!!) }
                        .subscribeBy(
                            onNext = {
                                DebugLog.i(TAG, it.toString())
                            },
                            onError = {
                                DebugLog.i(TAG, it.message!!)
//                                it.printStackTrace()
                            }
                        )
                }
        }

        @JvmStatic
        fun getAccountSnapshot(type: String) {
            getAccountSnapshot(type, null, null, null, null)
        }

        @SuppressLint("CheckResult")
        @JvmStatic
        fun getAccountSnapshot(type: String, startTime: Long?, endTime: Long?, limit: Int?, recvWindow: Long?) {

        }
    }
}
