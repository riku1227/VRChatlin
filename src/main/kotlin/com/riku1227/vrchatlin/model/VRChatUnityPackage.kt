package com.riku1227.vrchatlin.model

data class VRChatUnityPackage(
    var id: String?,
    var assetUrl: String?,
    var platform: String,
    var unityVersion: String,
    var unitySortNumber: Long?,
    var assetVersion: Int?,
    var created_at: String?
)