@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.doanmonhoc

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CreatePasswordScreen(
    onBack: () -> Unit = {},
    onNext: (password: String, rememberLogin: Boolean) -> Unit = { _, _ -> }
) {
    var password by remember { mutableStateOf("") }
    var isHidden by remember { mutableStateOf(true) }
    var rememberLogin by remember { mutableStateOf(true) }
    var confirmPassword by remember { mutableStateOf("") }

    val isPasswordValid  = password.length >= 6
    val isConfirmValid = confirmPassword.isNotEmpty() && confirmPassword == password
    val confirmMismatch = confirmPassword.isNotEmpty() && confirmPassword != password
    val isValid = isPasswordValid && isConfirmValid


    val bg = Color(0xFF0F1B21)
    val sheet = Color(0xFF14232B)
    val field = Color(0xFF0E1A20)
    val outline = Color(0xFF2C3E47)
    val textPrimary = Color(0xFFEAF2F6)
    val textSecondary = Color(0xFFB7C7D1)
    val blue = Color(0xFF1B74E4)
    val linkBlue = Color(0xFF4DA3FF)
    val outlineFocus = Color(0xFF6CB6FF)

    Surface(modifier = Modifier.fillMaxSize(), color = bg) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 18.dp)
                .clip(RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp))
                .background(sheet)
                .padding(horizontal = 18.dp, vertical = 14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = textPrimary
                    )
                }
                Spacer(Modifier.weight(1f))
            }

            Spacer(Modifier.height(6.dp))

            Text(
                text = "Tạo mật khẩu",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                color = textPrimary
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = "Tạo mật khẩu gồm ít nhất 6 chữ cái hoặc chữ số.\nBạn nên chọn mật khẩu thật khó đoán.",
                style = MaterialTheme.typography.bodyMedium,
                color = textSecondary
            )

            Spacer(Modifier.height(18.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Mật khẩu") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = if (isHidden) PasswordVisualTransformation() else VisualTransformation.None,
                trailingIcon = {
                    IconButton(onClick = { isHidden = !isHidden }) {
                        Icon(
                            imageVector = if (isHidden) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = "Toggle password",
                            tint = textSecondary
                        )
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = field,
                    unfocusedContainerColor = field,
                    focusedBorderColor = outline,
                    unfocusedBorderColor = outline,
                    focusedTextColor = textPrimary,
                    unfocusedTextColor = textPrimary,
                    focusedLabelColor = textSecondary,
                    unfocusedLabelColor = textSecondary,
                    cursorColor = textPrimary
                ),
                shape = RoundedCornerShape(18.dp),
                supportingText = {
                    if (password.isNotEmpty() && !isValid) {
                        Text("Mật khẩu phải từ 6 ký tự trở lên", color = textSecondary)
                    }
                }
            )

            Spacer(Modifier.height(12.dp))

            val confirmMismatch = confirmPassword.isNotEmpty() && confirmPassword != password

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Xác minh mật khẩu") },
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = textPrimary), // ⭐ luôn trắng
                isError = confirmMismatch,
                visualTransformation = if (isHidden) PasswordVisualTransformation() else VisualTransformation.None,
                trailingIcon = {
                    IconButton(onClick = { isHidden = !isHidden }) {
                        Icon(
                            imageVector = if (isHidden) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = "Toggle",
                            tint = textSecondary
                        )
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = field,
                    unfocusedContainerColor = field,

                    focusedTextColor = textPrimary,
                    unfocusedTextColor = textPrimary,

                    // ⭐ quan trọng: khi lỗi vẫn cho chữ trắng
                    errorTextColor = textPrimary,
                    errorContainerColor = field,

                    focusedBorderColor = outlineFocus,
                    unfocusedBorderColor = outline,
                    errorBorderColor = Color(0xFFFF6B6B),

                    focusedLabelColor = textSecondary,
                    unfocusedLabelColor = textSecondary.copy(alpha = 0.9f), // label sáng hơn chút
                    errorLabelColor = Color(0xFFFF6B6B),

                    cursorColor = textPrimary,
                    errorCursorColor = textPrimary,
                    errorTrailingIconColor = textSecondary
                ),
                supportingText = {
                    if (confirmMismatch) {
                        Text("Mật khẩu xác minh không khớp", color = Color(0xFFFF6B6B))
                    }
                },
                shape = RoundedCornerShape(18.dp),
            )


            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = rememberLogin,
                    onCheckedChange = { rememberLogin = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = blue,
                        uncheckedColor = textSecondary,
                        checkmarkColor = Color.Black
                    )
                )

                Text(
                    text = "Ghi nhớ thông tin đăng nhập.",
                    color = textPrimary,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(Modifier.width(6.dp))

                Text(
                    text = "Tìm hiểu thêm",
                    color = linkBlue,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.clickable {
                        // TODO: mở dialog/ màn giải thích nếu muốn
                    }
                )
            }

            Spacer(Modifier.height(14.dp))

            Button(
                onClick = { onNext(password, rememberLogin) },
                enabled = isValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(26.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = blue,
                    disabledContainerColor = blue.copy(alpha = 0.35f),
                    contentColor = Color.White,
                    disabledContentColor = Color.White.copy(alpha = 0.7f)
                )
            ) {
                Text("Tiếp")
            }

            Spacer(Modifier.weight(1f))
        }
    }
}
