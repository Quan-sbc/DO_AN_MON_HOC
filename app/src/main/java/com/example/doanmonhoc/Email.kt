@file:OptIn(ExperimentalMaterial3Api::class)

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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.util.PatternsCompat

@Composable
fun EmailScreen(
    onBack: () -> Unit = {},
    onNext: (String) -> Unit = {},
) {
    val bg = Color(0xFF0F1B21)
    val card = Color(0xFF14232B)
    val outline = Color(0xFF2C3E48)
    val blue = Color(0xFF1877F2)
    val textSub = Color(0xFFB8C7D1)
    val hint = Color(0xFF8EA3B0)
    val sheet = Color(0xFF14232B)

    var email by remember { mutableStateOf("") }

    val emailClean = remember(email) { email.trim().replace(" ", "") }
    val isValidEmail = remember(emailClean) {
        emailClean.isNotBlank() && PatternsCompat.EMAIL_ADDRESS.matcher(emailClean).matches()
    }
Surface(
    modifier = Modifier.fillMaxSize(),
    color = bg
){
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
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Email của bạn là gì?",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 36.sp
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = "Nhập email có thể dùng để liên hệ với bạn. Sẽ không ai nhìn thấy thông tin này trên trang cá nhân của bạn.",
            color = textSub,
            fontSize = 16.sp,
            lineHeight = 22.sp
        )

        Spacer(Modifier.height(18.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { input ->
                email = input.replace(" ", "")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            placeholder = { Text("Email", color = hint, fontSize = 18.sp) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = card,
                unfocusedContainerColor = card,
                focusedBorderColor = outline,
                unfocusedBorderColor = outline,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White
            )
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { onNext(emailClean) },
            enabled = isValidEmail,
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
    }
}
}

