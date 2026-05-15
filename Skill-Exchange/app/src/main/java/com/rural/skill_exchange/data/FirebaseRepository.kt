package com.rural.skill_exchange.data

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.rural.skill_exchange.model.ChatMessage
import com.rural.skill_exchange.model.NeedPost
import com.rural.skill_exchange.model.SkillProfile
import com.rural.skill_exchange.model.SwapOffer

class FirebaseRepository {
    private val auth = Firebase.auth
    private val db = Firebase.firestore

    fun ensureSignedIn(onReady: () -> Unit = {}) {
        if (auth.currentUser != null) {
            onReady()
            return
        }
        auth.signInAnonymously().addOnCompleteListener { onReady() }
    }

    fun observeProfile(onResult: (SkillProfile) -> Unit) {
        db.collection("profiles")
            .document(SampleData.profile.id)
            .addSnapshotListener { snapshot, _ ->
                val profile = snapshot?.toObject(SkillProfile::class.java) ?: SampleData.profile
                onResult(profile)
            }
    }

    fun observeNeeds(onResult: (List<NeedPost>) -> Unit) {
        db.collection("needs")
            .addSnapshotListener { snapshot, _ ->
                val remote = snapshot?.documents
                    ?.mapNotNull { it.toObject(NeedPost::class.java)?.copy(id = it.id) }
                    .orEmpty()
                onResult(remote.ifEmpty { SampleData.needs })
            }
    }

    fun observeOffers(onResult: (List<SwapOffer>) -> Unit) {
        db.collection("offers")
            .addSnapshotListener { snapshot, _ ->
                val remote = snapshot?.documents
                    ?.mapNotNull { it.toObject(SwapOffer::class.java)?.copy(id = it.id) }
                    .orEmpty()
                onResult(remote.ifEmpty { SampleData.offers })
            }
    }

    fun observeMessages(offerId: String, onResult: (List<ChatMessage>) -> Unit) {
        db.collection("negotiations")
            .document(offerId)
            .collection("messages")
            .orderBy("createdAt")
            .addSnapshotListener { snapshot, _ ->
                val remote = snapshot?.documents
                    ?.mapNotNull { it.toObject(ChatMessage::class.java)?.copy(id = it.id) }
                    .orEmpty()
                onResult(remote.ifEmpty { SampleData.messages })
            }
    }

    fun sendMessage(offerId: String, body: String) {
        val message = ChatMessage(
            senderName = "Ravi",
            body = body,
            mine = true,
            createdAt = System.currentTimeMillis()
        )
        db.collection("negotiations")
            .document(offerId)
            .collection("messages")
            .add(message)
    }

    fun confirmSwap(offer: SwapOffer, onLocalResult: (SwapOffer, Int) -> Unit) {
        val updated = offer.copy(
            status = "Completed",
            requesterConfirmed = true,
            helperConfirmed = true
        )
        val newTrustScore = (SampleData.profile.trustScore + 3).coerceAtMost(100)
        if (offer.id.isNotBlank()) {
            db.collection("offers").document(offer.id).set(updated)
            db.collection("profiles").document(SampleData.profile.id).update("trustScore", newTrustScore)
        }
        onLocalResult(updated, newTrustScore)
    }
}
