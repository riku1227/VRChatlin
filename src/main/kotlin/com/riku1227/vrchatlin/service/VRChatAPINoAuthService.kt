package com.riku1227.vrchatlin.service

import com.riku1227.vrchatlin.model.VRChatRemoteConfig
import io.reactivex.Single
import retrofit2.http.GET

interface VRChatAPINoAuthService {
    //Remote Config
    @GET("1/config")
    fun getRemoteConfig(): Single<VRChatRemoteConfig>
}