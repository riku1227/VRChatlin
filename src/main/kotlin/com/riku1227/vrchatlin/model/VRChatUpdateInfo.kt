package com.riku1227.vrchatlin.model

data class VRChatUpdateInfo(
    var email: String? = null,
    var birthday: String? = null,
    var acceptedTOSVersion: String? = null,
    var tags: List<String>? = null,
    var networkSessionId: String? = null,
    var status: String? = null,
    var statusDescription: String? = null,
    var bio: String? = null,
    var bioLinks: List<String>? = null
)