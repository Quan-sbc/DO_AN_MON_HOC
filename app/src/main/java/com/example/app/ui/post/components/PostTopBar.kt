package com.example.app.ui.post.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PostTopBar(
    onAddClick: () -> Unit,
    onSearchClick: () -> Unit,
    onMenuClick: () -> Unit,
    selectedTabIndex: Int,
    onTabClick: (Int) -> Unit,
) {
    // Thêm statusBarsPadding ở đây để toàn bộ TopBar luôn nằm dưới tai thỏ/pin
    Column(modifier = Modifier.background(Color.White).statusBarsPadding()) {

        // 1. Chỉ hiển thị phần này khi selectedTabIndex == 0 (Home)
        AnimatedVisibility(
            visible = selectedTabIndex == 0,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            // Phần logo + 3 nút bấm
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 14.dp, end = 6.dp, top = 12.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val gradientColors = listOf(Color(0xFF1877F2), Color(0xFF0052D4), Color(0xFF4361EE))

                Text(
                    text = "UNDISC",
                    style = TextStyle(
                        brush = Brush.linearGradient(colors = gradientColors),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = (-0.5).sp
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    HeaderIconButton(Icons.Filled.AddCircleOutline, onClick = onAddClick)
                    HeaderIconButton(Icons.Default.Search, onClick = onSearchClick)
                    HeaderIconButton(Icons.Default.Menu, onClick = onMenuClick)
                }
            }
        }

        // Phần Tabs Home, Friends, Dating, Notification
        Row(modifier = Modifier.fillMaxWidth().height(48.dp)) {
            TabIcon(
                icon = Icons.Default.Home,
                isSelected = selectedTabIndex == 0,
                modifier = Modifier.weight(1f),
                onClick = { onTabClick(0) }
            )
            TabIcon(
                icon = Icons.Default.Person,
                isSelected = selectedTabIndex == 1,
                modifier = Modifier.weight(1f),
                onClick = { onTabClick(1) }
            )
            TabIcon(
                icon = Icons.Default.Favorite,
                isSelected = selectedTabIndex == 2,
                modifier = Modifier.weight(1f),
                onClick = { onTabClick(2) }
            )
            TabIcon(
                icon = Icons.Default.Notifications,
                isSelected = selectedTabIndex == 3,
                modifier = Modifier.weight(1f),
                onClick = { onTabClick(3) }
            )
        }

        // Đường kẻ mảnh dưới khi chọn 1 tag
        HorizontalDivider(
            thickness = 0.5.dp,
            color = Color.LightGray.copy(alpha = 0.5f)
        )
    }
}
// Hàm tạo kích thước 3 icon ở bên phải logo
@Composable
fun HeaderIconButton(icon: ImageVector,
                     onClick: ()-> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(38.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(26.dp),
            tint = Color.Black
        )
    }
}
