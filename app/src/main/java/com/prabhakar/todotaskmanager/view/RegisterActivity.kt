package com.prabhakar.todotaskmanager.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prabhakar.todotaskmanager.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        registerBtn.setOnClickListener {
            if (validationEmail() && validPassword()) {
                startActivity(Intent(this, HomeActivity::class.java))
            }

        }
    }

    private fun validationEmail(): Boolean {
        if (registerUserName.text != null && registerUserName.text.isNotEmpty()
            && registerEmail.text != null && registerEmail.text.isNotEmpty()
            && registerEmail.text.toString().matches(emailPattern)

        ) {
            return true
        } else {
            registerEmail.error = "Invalid Email !"
            return false
        }
    }

    private fun validPassword(): Boolean {
        if (registerPassword.text.toString().length < 8 && registerPassword.text != null
            && registerPassword.text.isEmpty()
        ) {
            registerPassword.error = "Password should be minimum 8 character !"
            return false
        }
        else if (registerPassword.text.toString() != re_typePassword.text.toString()) {
            registerPassword.error = "Password and re-type Password should be same !"
            re_typePassword.error = "Password and re-type Password should be same !"
            return false
        } else {
            return true
        }


    }
}
