package com.riku1227.vrchatlin.model

data class VRChatWorld(
    var id: String,
    var name: String,
    var description: String?,
    var featured: Boolean?,
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
    var assetUrl: String?,
    //assetUrlObject TODO
    var pluginUrl: String?,
    //pluginUrlObject TODO
    var unityPackageUrl: String?,
    //unityPackageUrlObject TODO
    var namespace: String?,
    var unityPackageUpdated: Boolean?,
    var unityPackages: List<VRChatUnityPackage>,
    var version: Int?,
    var previewYoutubeId: String?,
    var popularity: Int,
    var heat: Int,
    var publicOccupants: Int?,
    var privateOccupants: Int?,
    var occupants: Int?,
    var favoriteId: String?,
    var instances: List<Any>?
)