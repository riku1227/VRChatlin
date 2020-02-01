package com.riku1227.vrchatlin.model

data class VRChatWorldInstance(
    var id: String,
    var instanceId: String,
    var name: String,
    var worldId: String,
    var type: String,
    var ownerId: String,
    var tags: List<String>,
    var active: Boolean,
    var full: Boolean,
    var n_users: Int,
    var capacity: Int,
    var platforms: VRChatPlatforms,
    var shortName: String,
    var world: VRChatWorld?,
    var users: List<VRChatUser>?,
    var buildVersion: String,
    var photonServer: String,
    var canRequestInvite: Boolean,
    var permanent: Boolean,
    var hidden: String?
)