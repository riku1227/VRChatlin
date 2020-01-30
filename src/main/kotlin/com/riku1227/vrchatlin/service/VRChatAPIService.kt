package com.riku1227.vrchatlin.service

import com.riku1227.vrchatlin.model.VRChatRemoteConfig
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface VRChatAPIService {

    //Remote Config
    @GET("1/config")
    fun getRemoteConfig(): Single<VRChatRemoteConfig>

    @GET("1/auth/user")
    fun login(@Header("Authorization") auth: String, @Query("apiKey") apiKey: String): Completable
}