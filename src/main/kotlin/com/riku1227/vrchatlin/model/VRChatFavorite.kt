package com.riku1227.vrchatlin.model

data class VRChatFavorite(
    var id: String? = null,
    var type: String,
    var favoriteId: String,
    var tags: List<String>
)