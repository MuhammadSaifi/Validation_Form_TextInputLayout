package com.example.validation_form_textinputlayout

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast

import com.google.android.material.textfield.TextInputLayout

import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {


    private var textInputEmail: TextInputLayout? = null
    private var textInputUsername: TextInputLayout? = null
    private var textInputPassword: TextInputLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        textInputEmail = findViewById(R.id.text_input_email)
        textInputUsername = findViewById(R.id.text_input_username)
        textInputPassword = findViewById(R.id.text_input_password)

    }

    private fun validateEmail(): Boolean {
        val emailInput = textInputEmail!!.editText!!.text.toString().trim { it <= ' ' }

        if (emailInput.isEmpty()) {
            textInputEmail!!.error = "Field can't be empty"
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            textInputEmail!!.error = "Please enter a valid email address"
            return false
        } else {
            textInputEmail!!.error = null
            return true
        }
    }

    private fun validateUsername(): Boolean {
        val usernameInput = textInputUsername!!.editText!!.text.toString().trim { it <= ' ' }

        if (usernameInput.isEmpty()) {
            textInputUsername!!.error = "Field can't be empty"
            return false
        } else if (usernameInput.length > 15) {
            textInputUsername!!.error = "Username too long"
            return false
        } else {
            textInputUsername!!.error = null
            return true
        }
    }

    private fun validatePassword(): Boolean {
        val passwordInput = textInputPassword!!.editText!!.text.toString().trim { it <= ' ' }

        if (passwordInput.isEmpty()) {
            textInputPassword!!.error = "Field can't be empty"
            return false
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            textInputPassword!!.error = "Password too weak"
            return false
        } else {
            textInputPassword!!.error = null
            return true
        }
    }

    fun confirmInput(v: View) {
        if (!validateEmail() or !validateUsername() or !validatePassword()) {
            return
        }

        var input = "Email: " + textInputEmail!!.editText!!.text.toString()
        input += "\n"
        input += "Username: " + textInputUsername!!.editText!!.text.toString()
        input += "\n"
        input += "Password: " + textInputPassword!!.editText!!.text.toString()

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show()
    }
// this is our password format
    companion object {
        private val PASSWORD_PATTERN = Pattern.compile("^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter

                "(?=.*[@#$%^&+=])" +    //at least 1 special character

                "(?=\\S+$)" +           //no white spaces

                ".{4,}" +               //at least 4 characters

                "$")
    }
}
