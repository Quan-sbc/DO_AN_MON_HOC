package com.example.app.ui.login
import android.content.Intent
import android.os.Bundle

import androidx.activity.compose.setContent

import androidx.compose.runtime.*

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import com.example.app.R
import com.example.app.ui.post.PostActivity
import com.example.app.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

       setContent {
           var showLoginScreen by remember { mutableStateOf(false) }

           LoginScreen(
               onLoginClick = {
                   startActivity(Intent(this, PostActivity::class.java))
               },
               onBackClick = { finish() },
               onRegisterClick = { startActivity(Intent(this, RegisterActivity::class.java))}
           )
       }
       }
    }

