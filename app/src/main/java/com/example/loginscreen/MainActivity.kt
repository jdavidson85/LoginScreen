package com.example.loginscreen

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginscreen.MainActivity.LoginSuccess.*

class MainActivity : AppCompatActivity() {

    enum class LoginSuccess
    constructor(val intValue: Int) {

        login (1),
        password (2),
        success (0)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        val txtLogin = findViewById<TextView>(R.id.idLoginText)
        val txtPassword = findViewById<TextView>(R.id.idPassword)
        val btnLogin = findViewById<TextView>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            when (CheckLogin(txtLogin.text.toString(), txtPassword.text.toString())) {

                LoginSuccess.login -> {
                    Toast.makeText(applicationContext, getString(R.string.errMessageLogin), Toast.LENGTH_LONG).show()
                    txtLogin.requestFocus()
                }

                LoginSuccess.password -> {
                    Toast.makeText(applicationContext, getString(R.string.errMessagePassword), Toast.LENGTH_LONG).show()
                    txtPassword.requestFocus()
                }

                else ->
                    Toast.makeText(applicationContext, "Success", Toast.LENGTH_LONG).show()

            }
        }
    }
    fun CheckLogin(txtLogin: String, txtPassword: String): LoginSuccess{
        val holdLogin = "Joe"
        val holdPass = "password"

        if (holdLogin != txtLogin) {
            return login
        }
        return if (holdPass != txtPassword){
            password
        } else success

    }
}