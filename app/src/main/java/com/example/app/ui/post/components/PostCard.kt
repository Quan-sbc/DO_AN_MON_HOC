package com.example.app.ui.post.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.app.ui.post.model.Post
import com.example.app.ui.post.model.parseMediaJson
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.ThumbUpOffAlt
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.app.utils.formatTimeAgo

// Nội dung bài đăng
@Composable
fun PostRow(post: Post, user: Pair<String, String>?,onClickComment:()->Unit) {
    val mediaList = parseMediaJson(post.media ?: "[]")

    Column(modifier = Modifier.fillMaxWidth().background(Color.White).padding(vertical = 12.dp)) {
        // Header
        Row(modifier = Modifier.padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(model = user?.second, contentDescription = null, modifier = Modifier.size(40.dp).clip(CircleShape))
            Spacer(Modifier.width(8.dp))
            Column {
                Text(user?.first ?: "Unknown", fontWeight = FontWeight.Bold)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(formatTimeAgo(post.createdAt.time), style = MaterialTheme.typography.bodySmall) // Cần format date
                    Spacer(Modifier.width(4.dp))
                    Icon(Icons.Default.Circle, null, modifier = Modifier.size(3.dp), tint = Color.Gray)
                    Spacer(Modifier.width(4.dp))
                    Icon(Icons.Default.Public, null, modifier = Modifier.size(12.dp), tint = Color.Gray)
                }
            }
        }
        // Content
        Text(text = post.content, modifier = Modifier.padding(16.dp))

        // Media
        if (mediaList.isNotEmpty()) {
            AsyncImage(
                model = mediaList[0],
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().heightIn(min = 200.dp, max = 400.dp),
                contentScale = ContentScale.FillWidth
            )
        }

        // Reaction Stats (Like, Comment counts)
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Favorite, null, tint = Color(0xFF1877F2), modifier = Modifier.size(16.dp))
                Text(" ${post.likeCount}", color = Color.Gray, fontSize = 13.sp)
            }
            Text("${post.commentCount} bình luận • ${post.shareCount} chia sẻ", color = Color.Gray, fontSize = 13.sp,
                modifier = Modifier.clickable { onClickComment() }
            )
        }

        HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)

        // Action Buttons
        Row(modifier = Modifier.fillMaxWidth()) {
            ActionButton(Icons.Default.FavoriteBorder, "Thích", Modifier.weight(1f))
            ActionButton(Icons.Default.ChatBubbleOutline, "Bình luận", Modifier.weight(1f)
                .clip(CircleShape)
                , onClickComment = {onClickComment()}
                )
            ActionButton(Icons.Default.Share, "Chia sẻ", Modifier.weight(1f))
        }
    }
}