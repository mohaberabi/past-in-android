package com.mohaberabi.pastinandroid.core.data.dto


data class NewsModelDto(
    val id: String,
    val title: String,
    val body: String,
    val url: String,
    val imageUrl: String,
    val related: List<String>,
    val date: String
) {
    companion object {
        fun fromMap(map: Map<String, Any?>): NewsModelDto {
            val id = if (map["id"] == null) "" else map["id"] as String
            val title = if (map["title"] == null) "" else map["title"] as String
            val body = if (map["body"] == null) "" else map["body"] as String
            val url = if (map["url"] == null) "" else map["url"] as String
            val imageUrl = if (map["imageUrl"] == null) "" else map["imageUrl"] as String

            val related =
                if (map["related"] == null) listOf() else (map["related"] as List<*>).map { it.toString() }
            val date = if (map["date"] == null) "" else map["date"] as String

            return NewsModelDto(
                id = id,
                title = title,
                body = body,
                url = url,
                imageUrl = imageUrl,
                related = related,
                date = date,
            )
        }
    }


}

