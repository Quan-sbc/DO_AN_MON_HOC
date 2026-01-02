package com.example.app.ui.register.components



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app.ui.register.RegisterViewModel

@Composable
fun CreateUsernameScreen(
    viewModel: RegisterViewModel = viewModel(),
    onClose: () -> Unit = {},
    onNext: () -> Unit = {},
) {
    val user by viewModel.user.collectAsState()
    val username = user.userName

    val isValid by remember(username) {
        derivedStateOf {
            username.length in 3..20 &&
                    username.all { it.isLetterOrDigit() || it == '.' || it == '_' }
        }
    }

    val bg = Color(0xFF0F1B21)
    val sheet = Color(0xFF14232B)
    val field = Color(0xFF0E1A20)
    val outline = Color(0xFF2C3E47)
    val textPrimary = Color(0xFFEAF2F6)
    val textSecondary = Color(0xFFB7C7D1)
    val blue = Color(0xFF1B74E4)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = bg
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 18.dp)
                .clip(RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp))
                .background(sheet)
                .padding(18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onClose) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = textPrimary
                    )
                }
                Spacer(Modifier.weight(1f))
            }

            Spacer(Modifier.height(6.dp))

            Text(
                text = "Tạo tên người dùng",
                style = MaterialTheme.typography.headlineSmall,
                color = textPrimary
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = "Để bắt đầu tạo tài khoản, bạn cần thêm tên người dùng hoặc dùng gợi ý của chúng tôi. Bạn có thể thay đổi tên bất cứ lúc nào.",
                style = MaterialTheme.typography.bodyMedium,
                color = textSecondary
            )

            Spacer(Modifier.height(18.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { viewModel.updateUserName(it) },

                modifier = Modifier
                    .fillMaxWidth(),
                label = { Text("Tên người dùng") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Ascii,
                    imeAction = ImeAction.Done
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = field,
                    unfocusedContainerColor = field,
                    focusedBorderColor = outline,
                    unfocusedBorderColor = outline,
                    focusedLabelColor = textSecondary,
                    unfocusedLabelColor = textSecondary,
                    focusedTextColor = textPrimary,
                    unfocusedTextColor = textPrimary,
                    cursorColor = textPrimary
                ),
                shape = RoundedCornerShape(14.dp)
            )

            Spacer(Modifier.weight(1f))

            Button(
                onClick = { onNext() },
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

            Spacer(Modifier.height(10.dp))
        }
    }
}
