package com.example.app.ui.post.model


import android.util.Log
import androidx.room.*
import androidx.room.Entity
import java.util.Date
import org.json.JSONArray

@Entity(tableName = "posts")
data class Post(
    @PrimaryKey
    val id: Long,

    @ColumnInfo(name = "user_id", index = true)
    val userId: Long,

    val content: String,

    val media: String?,  // JSON list URL media

    val privacy: String,  // "public", "friends", "private"

    @ColumnInfo(name = "comment_count")
    val commentCount: Int = 0,

    @ColumnInfo(name = "like_count")
    val likeCount: Int = 0,

    @ColumnInfo(name = "share_count")
    val shareCount: Int = 0,

    val status: String,  // "active", "deleted"

    @ColumnInfo(name = "created_at")
    val createdAt: Date,

    @ColumnInfo(name = "updated_at")
    val updatedAt: Date,

    @ColumnInfo(name = "original_post_id")
    val originalPostId: Long? = null
)

// helper parse media JSON
fun parseMediaJson(mediaJson: String): List<String> {
    return try {
        val array = JSONArray(mediaJson)
        List(array.length()) { i -> array.getString(i) }
    } catch (e: Exception) {
        Log.d("Erorr: ", e.toString())
        emptyList()
    }
}
