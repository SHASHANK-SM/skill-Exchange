package com.rural.skill_exchange.model

data class SwapOffer(
    val id: String = "",
    val needTitle: String = "",
    val fromUser: String = "",
    val offeredSkill: String = "",
    val requestedSkill: String = "",
    val hours: Int = 1,
    val status: String = "Pending",
    val requesterConfirmed: Boolean = false,
    val helperConfirmed: Boolean = false
) {
    val skillPoints: Int
        get() = hours

    val isMutuallyConfirmed: Boolean
        get() = requesterConfirmed && helperConfirmed
}
