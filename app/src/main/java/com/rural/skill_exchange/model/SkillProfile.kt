package com.rural.skill_exchange.model

data class SkillProfile(
    val id: String = "profile_ravi",
    val name: String = "Ravi Kumar",
    val village: String = "Mandya, Karnataka",
    val skills: List<String> = listOf("Solar repair", "Pump service", "Welding"),
    val skillPoints: Int = 18,
    val completedSwaps: Int = 9,
    val trustScore: Int = 82
)
