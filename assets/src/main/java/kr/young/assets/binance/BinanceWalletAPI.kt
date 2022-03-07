package kr.young.assets.binance

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface BinanceWalletAPI {
    @GET("/sapi/v1/system/status")
    fun getSystemStatus(): Observable<JsonObject>

    @GET("/sapi/v1/capital/config/getall")
    fun getCoinInfo(
        @Header("X-MBX-APIKEY") apiKey: String,
        @QueryMap params: MutableMap<String, Any>
    ): Observable<JsonArray>

    @GET("/sapi/v1/accountSnapshot")
    fun getAccountSnapshot(
        @Header("X-MBX-APIKEY") apiKey: String,
        @QueryMap params: MutableMap<String, Any>
        //"SPOT", "MARGIN", "FUTURES"
//        @Query("type") type: String,
//        @Query("startTime") startTime: Long?,
//        @Query("endTime") endTime: Long?,
//        @Query("limit") limit: Int?,
//        @Query("recvWindow") recvWindow: Long?,
//        @Query("timestamp") timestamp: Long,
//        @Query("signature") signature: String
    )

    //recvWindow: Long?, timestamp: Long
    @POST("/sapi/v1/account/disableFastWithdrawSwitch")
    fun disableFastWithdrawSwitch(
        @Header("X-MBX-APIKEY") apiKey: String,
        @FieldMap params: MutableMap<String, Any>
    ): Observable<JsonObject>

    //recvWindow: Long?, timestamp: Long
    @POST("/sapi/v1/account/disableFastWithdrawSwitch")
    fun enableFastWithdrawSwitch(
        @Header("X-MBX-APIKEY") apiKey: String,
        @FieldMap params: MutableMap<String, Any>
    ): Observable<JsonObject>

    //coin: String, withdrawOrderId: String?, network: String?, address: String
    //addressTag: String?, amount: Decimal, transactionFeeFlag: Boolean?, name: String,
    //walletType: Integer?, recvWindow: Long?, timestamp: Long
    @POST("/sapi/v1/capital/withdraw/apply")
    fun withdraw(
        @Header("X-MBX-APIKEY")
        @FieldMap params: MutableMap<String, Any>
    ): Observable<JsonObject>

    //coin: String?, status: Int?, startTime: Long?, endTime: Long?, offset: Int?
    //limit: Int?, recvWindow: Long?, timestamp: Long
    @GET("/sapi/v1/capital/deposit/hisrec")
    fun depositHistory(
        @Header("X-MBX-APIKEY")
        @QueryMap params: MutableMap<String, Any>
    ): Observable<JsonArray>

    //recvWindow: Long?, timestamp: Long
    @GET("/sapi/v1/account/status")
    fun getAccountStatus(
        @Header("X-MBX-APIKEY") apiKey: String,
        @QueryMap params: MutableMap<String, Any>
    )
}
