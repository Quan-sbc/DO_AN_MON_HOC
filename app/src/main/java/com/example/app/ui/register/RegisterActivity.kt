package com.example.app.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app.ui.post.PostActivity
import com.example.app.ui.post.model.Post
import com.example.app.ui.register.components.BirthDateScreen
import com.example.app.ui.register.components.CreatePasswordScreen
import com.example.app.ui.register.components.CreateUsernameScreen
import com.example.app.ui.register.components.EmailScreen
import com.example.app.ui.register.components.OtpVerifyScreen

enum class RegisterStep { USERNAME, PASSWORD, DATE, EMAIL, OTP }
class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: RegisterViewModel = viewModel()

            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    var step by remember { mutableStateOf(RegisterStep.USERNAME) }

                    val user by viewModel.user.collectAsState()
                    val currentEmail = user.email

                    // ← BỎ key(step) ĐI HOÀN TOÀN
                    when (step) {
                        RegisterStep.USERNAME -> CreateUsernameScreen(
                            viewModel = viewModel,
                            onClose = { finish() },
                            onNext = { step = RegisterStep.DATE }
                        )

                        RegisterStep.DATE -> BirthDateScreen(
                            viewModel = viewModel,
                            onBack = { step = RegisterStep.USERNAME },
                            onNext = { step = RegisterStep.EMAIL }
                        )

                        RegisterStep.EMAIL -> EmailScreen(
                            viewModel = viewModel,
                            onBack = { step = RegisterStep.DATE },
                            onNext = { step = RegisterStep.PASSWORD }
                        )

                        RegisterStep.PASSWORD -> CreatePasswordScreen(
                            viewModel = viewModel,
                            onBack = { step = RegisterStep.EMAIL },
                            onNext = { step = RegisterStep.OTP }
                        )

                        RegisterStep.OTP -> OtpVerifyScreen(
                            email = currentEmail,
                            onBack = { step = RegisterStep.PASSWORD },
                            onNext = {
                                startActivity(Intent(this, PostActivity::class.java))
                            },
                            onNoCode = {
                                // TODO: Gửi lại mã
                            }
                        )
                    }
                }
            }
        }
    }
}