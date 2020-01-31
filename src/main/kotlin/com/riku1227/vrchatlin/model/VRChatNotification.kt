package com.riku1227.vrchatlin.model

data class VRChatNotification(
    var id: String,
    var senderUserId: String,
    var senderUsername: String,
    var receiverUserId: String?,
    var type: String,
    var message: String?,
    var details: String?,
    var seen: Boolean?,
    var created_at: String,
    var jobName: String?,
    var jobColor: String?
)