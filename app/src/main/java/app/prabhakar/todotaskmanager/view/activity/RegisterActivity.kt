package app.prabhakar.todotaskmanager.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.prabhakar.todotaskmanager.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // ---------- REGISTER User --------------------

        registerBtn.setOnClickListener {
            if (validName() && validationEmail() && validPassword()) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("userName", registerUserName.text.toString())
                startActivity(intent)
            }

        }

        // Navigate to Login

        already_account.setOnClickListener {

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }


    private fun validName(): Boolean {
        if (registerUserName.text != null && registerUserName.text.isNotEmpty() && registerUserName.text.toString().length >= 3) {
            return true
        } else {
            registerUserName.error = "User Name should be minimum 3 Character !"
            return false
        }
    }

    private fun validationEmail(): Boolean {
        if (registerEmail.text != null && registerEmail.text.isNotEmpty()
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
        } else if (registerPassword.text.toString() != re_typePassword.text.toString()) {
            registerPassword.error = "Password and re-type Password should be same !"
            re_typePassword.error = "Password and re-type Password should be same !"
            return false
        } else {
            return true
        }
    }
}