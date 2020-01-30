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

    private var preferences: SharedPreferences? = null
    private var vrChatAPIService: VRChatAPIService? = null
    private val moshi = Moshi.Builder()
                            .add(KotlinJsonAdapterFactory())
                            .build()

    fun setSharedPreferences(sharedPreferences: SharedPreferences) {
        preferences = sharedPreferences
    }

    fun getMoshi(): Moshi {
        return moshi
    }

    fun APIService(): VRChatAPIService {
        if(vrChatAPIService == null) {
            if(preferences == null) {
                preferences = context.getSharedPreferences("vrchat_api_cookie", Context.MODE_PRIVATE)
            }
            val retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(
                    OkHttpClient.Builder()
                        .cookieJar(VRChatlinCookieJar(preferences!!))
                        .build()
                )
                .build()
            vrChatAPIService = retrofit.create(VRChatAPIService::class.java)
        }
        return vrChatAPIService!!
    }
}