package com.example.app.ui.post.data


import com.example.app.ui.post.model.Post
import java.util.Date

fun fakeUsers(): Map<Long, Pair<String, String>> {
    return mapOf(
        1L to ("Mark Zuckerberg" to "https://i.pravatar.cc/150?img=1"),
        2L to ("Elon Musk" to "https://i.pravatar.cc/150?img=2")
    )
}


fun fakePosts(): List<Post> {
    val now = System.currentTimeMillis()
    return listOf(
        Post(
            id = 1,
            userId = 1,
            content = "Hello Facebook 👋",
            media = """["https://picsum.photos/400/300"]""",
            privacy = "public",
            likeCount = 120,
            commentCount = 30,
            shareCount = 5,
            status = "active",
            createdAt = Date(now - 5 * 60 * 1000),
            updatedAt = Date()
        ),
        Post(
            id = 2,
            userId = 2,
            content = "Mars is coming 🚀",
            media = null,
            privacy = "public",
            likeCount = 999,
            commentCount = 88,
            shareCount = 12,
            status = "active",
            createdAt = Date(),
            updatedAt = Date()
        ),
        Post(
            id = 3,
            userId = 2,
            content = "Mars is coming 🚀",
            media = null,
            privacy = "public",
            likeCount = 999,
            commentCount = 88,
            shareCount = 12,
            status = "active",
            createdAt = Date(now - 5 * 60 * 10000),
            updatedAt = Date()
        )
    )
}
