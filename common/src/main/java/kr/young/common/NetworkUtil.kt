package kr.young.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.net.NetworkRequest
import android.os.Handler
import android.os.Looper

class NetworkUtil(
    context: Context,
    private val listener: NetworkListener
): ConnectivityManager.NetworkCallback() {
    private var networkRequest: NetworkRequest? = null
    private var manager: ConnectivityManager? = null
    private var network: Network? = null
    private var handler: Handler? = null
    private val runnable = Runnable {
        this.listener.onConnected(manager, this.network!!)
    }

    init {
        networkRequest = NetworkRequest.Builder()
            .addTransportType(TRANSPORT_WIFI)
            .addTransportType(TRANSPORT_CELLULAR)
            .build()
        manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    fun register() {
        manager!!.registerNetworkCallback(networkRequest!!, this)
    }

    fun unregister() {
        manager!!.unregisterNetworkCallback(this)
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        DebugLog.d(TAG, "onLost(Network)")
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        DebugLog.d(TAG, "onAvailable(Network)")
        this.network = network

        if (handler == null) {
            handler = Handler(Looper.myLooper()!!)
        }
        handler!!.postDelayed(runnable, 1500)
    }

    companion object {
        private const val TAG = "NetworkUtil"
    }
}
