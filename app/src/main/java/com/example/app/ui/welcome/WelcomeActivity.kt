package com.example.app.ui.welcome

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app.R
import com.example.app.ui.login.LoginActivity

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            WelcomeScreen(
                onLoginClick = {
                    startActivity(Intent(this, LoginActivity::class.java))
                },
                onRegisterClick = {
                    // TODO: Sau này thêm màn Đăng ký ở đây

                }
            )
        }

    }
}