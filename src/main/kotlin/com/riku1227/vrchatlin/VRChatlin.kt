package com.riku1227.vrchatlin

import com.riku1227.vrchatlin.service.VRChatAPINoAuthService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.CookieHandler
import java.net.CookieManager
import java.net.CookiePolicy

class VRChatlin {
    companion object {
        const val API_BASE_URL = "https://api.vrchat.cloud/api/"

        private var moshi: Moshi? = null
        private var vrChatAPINoAuthService: VRChatAPINoAuthService? = null

        private var vrChatlinApiKey = ""
        private var vrChatlinAuthKey = ""
        private var vrChatlinCfduid = ""

        fun setAuth(apiKey: String, authKey: String, cfduid: String? = null) {
            vrChatlinApiKey = apiKey
            vrChatlinAuthKey = authKey
            if(cfduid != null) {
                vrChatlinCfduid = cfduid
            }
        }

        fun getMoshi(): Moshi {
            if(moshi == null) {
                moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            }
            return moshi!!
        }

        fun NoAuthAPIService(): VRChatAPINoAuthService {
            if(vrChatAPINoAuthService == null) {
                if(CookieHandler.getDefault() == null) {
                    CookieHandler.setDefault(CookieManager(null, CookiePolicy.ACCEPT_ALL))
                }
                val retrofit = Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(OkHttpClient.Builder()
                        .cookieJar(JavaNetCookieJar(CookieHandler.getDefault()))
                        .build()
                    )
                    .build()
                vrChatAPINoAuthService = retrofit.create(VRChatAPINoAuthService::class.java)
            }
            return vrChatAPINoAuthService!!
        }
    }
}