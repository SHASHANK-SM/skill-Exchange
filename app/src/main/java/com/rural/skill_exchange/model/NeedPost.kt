package com.rural.skill_exchange.model

data class NeedPost(
    val id: String = "",
    val title: String = "",
    val requiredSkill: String = "",
    val location: String = "",
    val hours: Int = 1,
    val description: String = "",
    val postedBy: String = ""
) {
    val skillPoints: Int
        get() = hours
}
