package com.riku1227.vrchatlin.model

data class VRChatWorld(
    var id: String,
    var name: String,
    var authorId: String,
    var authorName: String,
    var capacity: Int,
    var imageUrl: String,
    var thumbnailImageUrl: String,
    var releaseStatus: String,
    var organization: String,
    var tags: List<String>,
    var favorites: Int,
    var created_at: String,
    var updated_at: String,
    var publicationDate: String,
    var labsPublicationDate: String,
    var visits: Int,
    var unityPackages: List<VRChatUnityPackage>,
    var popularity: Int,
    var heat: Int,
    var occupants: Int,
    var favoriteId: String
)