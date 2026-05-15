package com.rural.skill_exchange.model

data class ChatMessage(
    val id: String = "",
    val senderName: String = "",
    val body: String = "",
    val mine: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
