package com.example.kakaoauthtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var getId = intent.getStringExtra("id")
        id.text = getId

        var getEmail = intent.getStringExtra("email")
        email.text = getEmail
    }
}