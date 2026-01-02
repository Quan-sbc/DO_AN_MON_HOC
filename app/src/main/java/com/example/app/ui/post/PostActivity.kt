package com.example.app.ui.post

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import com.example.app.ui.comment.CommentActivity
import com.example.app.ui.comment.CommentScreen
import com.example.app.ui.post.data.fakePosts
import com.example.app.ui.post.data.fakeUsers

class PostActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                PostScreen(
                    posts = fakePosts(),
                    usersMap = fakeUsers(),
                    onClickComment = { startActivity(Intent(this, CommentActivity::class.java)) }
                )
            }
        }
    }
}
