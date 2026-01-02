package com.example.app.ui.post.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@Composable
fun TabIcon(
    icon: ImageVector,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit // Tham số này sẽ báo về cho PostTopBar biết để reset các icon khác
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // 1. SỬA TẠI ĐÂY: Nổi lên nếu đang được CHỌN hoặc đang được NHẤN
    val animatedOffsetY by animateDpAsState(
        targetValue = if (isSelected || isPressed) (-6).dp else 0.dp,
        label = "TabOffset"
    )

    // 2. Màu sắc: Xanh nếu được chọn/nhấn, Xám nếu không
    val activeColor = if (isSelected || isPressed) Color(0xFF1877F2) else Color.Gray

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .height(48.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick // 3. QUAN TRỌNG: Phải gọi onClick ở đây để báo về PostTopBar
            )
    ) {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = activeColor,
                modifier = Modifier
                    .size(28.dp)
                    .offset(y = animatedOffsetY) // Icon sẽ đứng yên ở trên cao nếu isSelected = true
            )
        }

        // Thanh gạch chân dưới chân icon
        if (isSelected) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(3.dp)
                    .background(
                        color = activeColor,
                        shape = RoundedCornerShape(topStart = 3.dp, topEnd = 3.dp)
                    )
            )
        } else {
            Spacer(modifier = Modifier.height(3.dp))
        }
    }
}