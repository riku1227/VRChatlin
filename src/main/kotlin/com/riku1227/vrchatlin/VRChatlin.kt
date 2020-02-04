package com.riku1227.vrchatlin

import android.content.Context
import android.content.SharedPreferences
import com.riku1227.vrchatlin.service.VRChatAPIService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class VRChatlin(private val context: Context) {
    companion object {
        const val API_BASE_URL = "https://api.vrchat.cloud/api/"
        private var vrchatlin: VRChatlin? = null

        fun get(context: Context): VRChatlin {
            if(vrchatlin == null) {
                vrchatlin = VRChatlin(context)
            }
            return vrchatlin!!
        }
    }

    private var vrChatAPIService: VRChatAPIService? = null
    private val moshi = Moshi.Builder()
                            .add(KotlinJsonAdapterFactory())
                            .build()
    private var cookieJar: VRChatlinCookieJar? = null

    fun getMoshi(): Moshi {
        return moshi
    }

    fun APIService(initPreferences: SharedPreferences? = null, initOkHttpClient: OkHttpClient? = null): VRChatAPIService {
        if(vrChatAPIService == null) {
            val preferences = initPreferences ?: context.getSharedPreferences("vrchat_api_cookie", Context.MODE_PRIVATE)
            cookieJar = VRChatlinCookieJar(preferences)

            val okHttpClient = if(initOkHttpClient == null) {
                OkHttpClient.Builder()
                    .cookieJar(cookieJar!!)
                    .build()
            } else {
                initOkHttpClient
            }

            val retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(
                    okHttpClient
                )
                .build()
            vrChatAPIService = retrofit.create(VRChatAPIService::class.java)
        }
        return vrChatAPIService!!
    }

    fun setCookie(key: String, cookieStr: String) {
        cookieJar?.setCookie(key, cookieStr)
    }
}