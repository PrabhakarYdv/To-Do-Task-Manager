package com.prabhakar.todotaskmanager.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prabhakar.todotaskmanager.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {
    private val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // -------- LOGIN User -----------------

        loginBtn.setOnClickListener {
            if (validEmail()) {
                startActivity(Intent(this, HomeActivity::class.java))
            }

        }

        // Navigate to Register
        not_account.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }

    private fun validEmail(): Boolean {
        if (loginEmail.text != null && loginEmail.text.isNotEmpty()
            && loginEmail.text.toString().matches(emailPattern)
        ) {
            return true
        } else {
            loginEmail.error = "Invalid Email !"
            return false
        }
    }
}