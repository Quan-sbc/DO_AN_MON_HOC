package com.example.app.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.app.R

val bg = Color(0xFF0F1B21)
val sheet = Color(0xFF14232B)
val field = Color(0xFF0E1A20)
val outline = Color(0xFF2C3E47)
val textPrimary = Color(0xFFEAF2F6)
val textSecondary = Color(0xFFB7C7D1)
val blue = Color(0xFF1B74E4)

@Composable
fun LoginScreen(onLoginClick:()->Unit,onBackClick: () -> Unit,
                onRegisterClick:()->Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(bg)) {
        // Pattern ở góc phải
        Image(
            painter = painterResource(R.drawable.bgw1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopEnd)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .align(Alignment.Center),
            shape = RoundedCornerShape(40.dp),
            colors = CardDefaults.cardColors(containerColor = sheet),
            elevation = CardDefaults.cardElevation(20.dp)
        ) {
            Column(
                modifier = Modifier.padding(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = textPrimary
                        )
                    }
                }

                Text("Đăng nhập", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = textPrimary)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Vui lòng nhập thông tin để đăng nhập.",
                    fontSize = 15.sp,
                    color = textSecondary,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(32.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email", color = textSecondary) },
                    placeholder = { Text("Nhập email của bạn", color = textSecondary) },
                    leadingIcon = { Icon(Icons.Default.Email, null, tint = textPrimary) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = field,
                        unfocusedContainerColor = field,
                        focusedBorderColor = outline,
                        unfocusedBorderColor = outline,
                        cursorColor = blue,
                        focusedTextColor = textPrimary,
                        unfocusedTextColor = textPrimary
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Mật khẩu", color = textSecondary) },
                    placeholder = { Text("Nhập mật khẩu", color = textSecondary) },
                    leadingIcon = { Icon(Icons.Default.Lock, null, tint = textPrimary) },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = null,
                                tint = textPrimary
                            )
                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = field,
                        unfocusedContainerColor = field,
                        focusedBorderColor = outline,
                        unfocusedBorderColor = outline,
                        cursorColor = blue,
                        focusedTextColor = textPrimary,
                        unfocusedTextColor = textPrimary
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text("Quên mật khẩu?", color = blue, modifier = Modifier.align(Alignment.End))

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { onLoginClick() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = blue)
                ) {
                    Text("Đăng nhập", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                }

                Spacer(modifier = Modifier.height(24.dp))
                Text("Chưa có tài khoản? Đăng ký", color = blue,
                    modifier = Modifier.clickable {
                        onRegisterClick()
                    })
            }
        }
    }
}
