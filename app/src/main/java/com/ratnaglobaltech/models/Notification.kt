package com.ratnaglobaltech.models

data class Notification(
    val email: String,
    val id: Int,
    val message: String,
    val notificationSentOn: String,
    val status: String,
    val username: Any
)