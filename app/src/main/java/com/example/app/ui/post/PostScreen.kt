package com.example.app.ui.post


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.example.app.ui.post.components.PostRow
import com.example.app.ui.post.components.PostTopBar
import com.example.app.ui.post.components.QuickStatusInput
import com.example.app.ui.post.model.Post


@Composable
fun PostScreen(
    posts: List<Post>,
    usersMap: Map<Long, Pair<String, String>>,
    onClickComment:()->Unit
) {
    // 1. Tạo biến để lưu trữ Tab đang được chọn (mặc định là 0 - Trang chủ)
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            PostTopBar(
                onAddClick = { /* Xử lý add */ },
                onSearchClick = { /* Xử lý search */ },
                onMenuClick = { /* Xử lý menu */ },
                // 2. TRUYỀN THÊM 2 THAM SỐ CÒN THIẾU VÀO ĐÂY:
                selectedTabIndex = selectedTab,
                onTabClick = { index ->
                    selectedTab = index // Khi nhấn tab thì cập nhật lại vị trí
                }
            )
        },
        containerColor = Color(0xFFE4E6EB)
    ) { padding ->
        // 3. Hiển thị nội dung dựa trên Tab được chọn
        if (selectedTab == 0) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                item {
                    val currentUser = usersMap[1L]
                    QuickStatusInput(avatarUrl = currentUser?.second)
                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(posts) { post ->
                    PostRow(
                        onClickComment = { onClickComment() },
                        post = post,
                        user = usersMap[post.userId]
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        } else {
            // Các màn hình khác khi nhấn Tab 1, 2, 3
            Box(
                modifier = Modifier.padding(padding).fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = when(selectedTab) {
                    1 -> "Màn hình Trang cá nhân"
                    2 -> "Màn hình Yêu thích"
                    else -> "Màn hình Thông báo"
                })
            }
        }
    }
}