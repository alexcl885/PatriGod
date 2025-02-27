package com.example.patrigod.ui.views.activities

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.patrigod.R
import com.example.patrigod.databinding.ActivityLoginBinding
import com.example.patrigod.data.user.models.Request.RequestUsuario
import com.example.patrigod.data.user.network.service.ApiServiceUsuario
import com.example.patrigod.domain.usuarios.models.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class Login : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding

    @Inject
    lateinit var apiServiceUsuario: ApiServiceUsuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
        setupLoginListeners()
    }

    private fun setupLoginListeners() {
        loginBinding.btEntrar.setOnClickListener {
            val email = loginBinding.email.text.toString().trim()
            val password = loginBinding.comtrasena.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Tienes algún campo vacío", Toast.LENGTH_SHORT).show()
            }
        }

        loginBinding.btRegistrarse.setOnClickListener {
            val registerIntent = Intent(this, Register::class.java)
            startActivity(registerIntent)
        }

        loginBinding.btRecuperarContrasena.setOnClickListener {
            Toast.makeText(this, "Funcionalidad de recuperación no implementada", Toast.LENGTH_SHORT).show()
        }

        loginBinding.pin.setOnClickListener {
            togglePasswordVisibility()
        }
    }

    private fun loginUser(email: String, password: String) {
        val requestUsuario = RequestUsuario(email, password)

        lifecycleScope.launch {
            val result = apiServiceUsuario.loginService(requestUsuario)
            result.fold(
                onSuccess = { token ->
                    // guardo el token en SharedPreferences
                    val prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE).edit()
                    prefs.putString("token", token.token)
                    prefs.apply()
                    //++IMPORTANTE
                    //seteo el token que me manda la api para guardarla en un objetct para almacenarla para manndar peticiones a la api con ese token
                    User.token = token.token

                    Toast.makeText(this@Login, "Login exitoso", Toast.LENGTH_LONG).show()
                    //me aseguro de que se pasa bien el token
                    Log.d("Login", "Token recibido: ${token.token}")

                    // Navegar a la pantalla principal
                    val mainIntent = Intent(this@Login, MainActivity::class.java)
                    startActivity(mainIntent)
                    finish()
                },
                onFailure = { throwable ->
                    Toast.makeText(this@Login, "Error: ${throwable.message}", Toast.LENGTH_LONG).show()
                }
            )
        }
    }

    private fun togglePasswordVisibility() {
        val inputType = loginBinding.comtrasena.inputType
        if (inputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD) {
            loginBinding.comtrasena.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            loginBinding.pin.setImageResource(R.drawable.eye_open_icon)
        } else {
            loginBinding.comtrasena.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            loginBinding.pin.setImageResource(R.drawable.eye_close_icon)
        }
        loginBinding.comtrasena.setSelection(loginBinding.comtrasena.text.length)
    }
}
