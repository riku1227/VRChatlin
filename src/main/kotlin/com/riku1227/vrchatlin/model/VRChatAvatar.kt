package com.riku1227.vrchatlin.model

data class VRChatAvatar(
    var id: String,
    var name: String,
    var description: String,
    var authorId: String,
    var authorName: String,
    var tags: List<String>,
    var assetUrl: String,
    //var assetUrlObject TODO
    var imageUrl: String,
    var thumbnailImageUrl: String,
    var releaseStatus: String,
    var version: Int,
    var featured: Boolean,
    var unityPackages: List<VRChatUnityPackage>,
    var unityPackageUpdated: Boolean,
    var unityPackageUrl: String,
    //var unityPackageUrlObject TODO
    var created_at: String,
    var updated_at: String
)