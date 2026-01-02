@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.app.ui.register.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app.ui.register.RegisterViewModel
import androidx.compose.runtime.getValue                  // Quan trọng cho "by"
import androidx.compose.runtime.collectAsState            // Cho collectAsState() // Cho viewModel()
@Composable
fun PhoneNumberScreen(
    viewModel: RegisterViewModel = viewModel(),
    onBack: () -> Unit = {},
    onNext: () -> Unit = {},
    onSignupWithEmail: () -> Unit = {}
) {
    val bg = Color(0xFF0F1B21)
    val card = Color(0xFF14232B)
    val outline = Color(0xFF2C3E48)
    val blue = Color(0xFF1877F2)
    val textSub = Color(0xFFB8C7D1)
    val hint = Color(0xFF8EA3B0)
    val sheet = Color(0xFF14232B)

    // Lấy dữ liệu từ ViewModel
    val user by viewModel.user.collectAsState()
    val phone = user.phone

    val cleaned = remember(phone) { phone.filter { it.isDigit() } }
    val canNext = cleaned.length >= 9

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 18.dp)
            .clip(RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp))
            .background(sheet)
            .padding(18.dp)
    ) {
        // Top bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        // Title
        Text(
            text = "Số di động của bạn là gì?",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 36.sp
        )

        Spacer(Modifier.height(12.dp))

        // Description
        Text(
            text = "Nhập số di động có thể dùng để liên hệ với bạn. Sẽ không ai nhìn thấy thông tin này trên trang cá nhân của bạn.",
            color = textSub,
            fontSize = 16.sp,
            lineHeight = 22.sp
        )

        Spacer(Modifier.height(18.dp))
        OutlinedTextField(
            value = phone,
            onValueChange = { input->
                val filtered = buildString{
                    input.forEachIndexed {
                        index, ch -> if(ch.isDigit()) append(ch)
                        else if (index==0) append(ch)
                    }
                }
                viewModel.updatePhone(filtered)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            placeholder = {
                Text("Số di động", color = hint, fontSize = 18.sp)
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = card,
                unfocusedContainerColor = card,
                disabledContainerColor = card,

                focusedBorderColor = outline,
                unfocusedBorderColor = outline,
                disabledBorderColor = outline,

                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White
            )
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = "Chúng tôi có thể gửi cho bạn thông báo qua SMS và WhatsApp nhằm mục đích bảo mật và đăng nhập.",
            color = hint,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { onNext },
            enabled = canNext,
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = blue,
                disabledContainerColor = blue.copy(alpha = 0.45f)
            )
        ) {
            Text("Tiếp", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Spacer(Modifier.height(12.dp))

        OutlinedButton(
            onClick = onSignupWithEmail,
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            shape = RoundedCornerShape(30.dp),
            border = BorderStroke(1.dp, outline),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Transparent,
                contentColor = Color.White
            )
        ) {
            Text("Đăng ký bằng email", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}
