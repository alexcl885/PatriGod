package com.example.patrigod

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.patrigod.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
/**
 * Login de PatriGod
 * @author Alejandro Copado López
 * */
class Login : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        auth = FirebaseAuth.getInstance()


        setupLoginListeners()
    }

    private fun setupLoginListeners() {
        loginBinding.btEntrar.setOnClickListener {
            val email = loginBinding.email.text.toString()
            val password = loginBinding.comtrasena.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                signIn(email, password)
            } else {
                Toast.makeText(this, "Tienes algún campo vacío", Toast.LENGTH_SHORT).show()
            }
        }

        loginBinding.btRegistrarse.setOnClickListener {
            val registerIntent = Intent(this, Registro::class.java)
            startActivity(registerIntent)
        }

        loginBinding.btRecuperarContrasena.setOnClickListener {
            val email = loginBinding.email.text.toString()
            if (email.isNotEmpty()) {
                recoverPassword(email)
            } else {
                Toast.makeText(this, "Debes rellenar el campo email", Toast.LENGTH_SHORT).show()
            }
        }

        loginBinding.pin.setOnClickListener {
            togglePasswordVisibility()
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                if (user?.isEmailVerified == true) {
                    val mainIntent = Intent(this, MainActivity::class.java)
                    startActivity(mainIntent)
                    finish()
                } else {
                    auth.signOut()
                    Toast.makeText(this, "Debes verificar tu correo antes de loguearte", Toast.LENGTH_LONG).show()
                }
            } else {
                handleLoginError(task.exception)
            }
        }
    }

    private fun recoverPassword(email: String) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Se ha enviado un correo para recuperar la contraseña", Toast.LENGTH_LONG).show()
            } else {
                handleRecoveryError(task.exception)
            }
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

    private fun handleLoginError(exception: Exception?) {
        val message = when (exception) {
            is FirebaseAuthInvalidUserException -> "El usuario no existe o ha sido deshabilitado."
            is FirebaseAuthInvalidCredentialsException -> "Usuario o contraseña incorrectos."
            else -> "Error desconocido: ${exception?.message}"
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun handleRecoveryError(exception: Exception?) {
        val message = when (exception) {
            is FirebaseAuthInvalidCredentialsException -> "El formato del email es incorrecto."
            else -> "Error al enviar el correo: ${exception?.message}"
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
