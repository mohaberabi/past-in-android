package com.mohaberabi.pastinandroid.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class InterestDto(
    val id: String,
    val image: String,
    val title: String,
    val description: String,
) {

    companion object {
        fun fromMap(map: Map<String, Any?>): InterestDto {

            return InterestDto(
                id = map["id"] as String? ?: "",
                image = map["image"] as String? ?: "",
                title = map["title"] as String? ?: "",
                description = map["description"] as String? ?: "",
            )
        }
    }
}