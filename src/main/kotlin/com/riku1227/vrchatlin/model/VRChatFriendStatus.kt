package com.riku1227.vrchatlin.model

data class VRChatFriendStatus(
    var isFriend: Boolean,
    var outgoingRequest: Boolean,
    var incomingRequest: Boolean
)