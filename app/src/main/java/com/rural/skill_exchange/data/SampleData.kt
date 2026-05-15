package com.rural.skill_exchange.data

import com.rural.skill_exchange.model.ChatMessage
import com.rural.skill_exchange.model.NeedPost
import com.rural.skill_exchange.model.SkillProfile
import com.rural.skill_exchange.model.SwapOffer

object SampleData {
    val profile = SkillProfile()

    val needs = listOf(
        NeedPost(
            id = "need_1",
            title = "Fix solar inverter at milk society",
            requiredSkill = "Solar repair",
            location = "Channapatna",
            hours = 3,
            description = "Battery is charging slowly and the lights cut out by evening.",
            postedBy = "Lakshmi"
        ),
        NeedPost(
            id = "need_2",
            title = "Service borewell pump before sowing",
            requiredSkill = "Pump service",
            location = "Maddur",
            hours = 4,
            description = "Pump starts but water flow is weak. Need inspection and basic repair.",
            postedBy = "Manjunath"
        ),
        NeedPost(
            id = "need_3",
            title = "Weld frame for mobile vegetable cart",
            requiredSkill = "Welding",
            location = "Srirangapatna",
            hours = 5,
            description = "Cart frame needs reinforcement and two new support bars.",
            postedBy = "Asha"
        ),
        NeedPost(
            id = "need_4",
            title = "Repair drip irrigation controller",
            requiredSkill = "Electrical",
            location = "Pandavapura",
            hours = 2,
            description = "Timer display is working, but the valves are not opening.",
            postedBy = "Kiran"
        )
    )

    val offers = listOf(
        SwapOffer(
            id = "offer_1",
            needTitle = "Fix solar inverter at milk society",
            fromUser = "Lakshmi",
            offeredSkill = "Tailoring work",
            requestedSkill = "Solar repair",
            hours = 3,
            status = "Pending"
        ),
        SwapOffer(
            id = "offer_2",
            needTitle = "Service borewell pump before sowing",
            fromUser = "Manjunath",
            offeredSkill = "Tractor tilling",
            requestedSkill = "Pump service",
            hours = 4,
            status = "Accepted",
            requesterConfirmed = true
        )
    )

    val messages = listOf(
        ChatMessage(id = "m1", senderName = "Lakshmi", body = "Can you come tomorrow morning? The inverter stops by evening.", mine = false),
        ChatMessage(id = "m2", senderName = "Ravi", body = "Yes. I can check it at 9 AM. It may take around 3 hours.", mine = true),
        ChatMessage(id = "m3", senderName = "Lakshmi", body = "I can offer 3 hours of tailoring work as points.", mine = false)
    )
}
