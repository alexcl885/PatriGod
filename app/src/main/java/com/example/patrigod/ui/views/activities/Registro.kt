package com.example.patrigod.ui.views.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.patrigod.data.user.models.Request.RequestUsuarioEntero
import com.example.patrigod.data.user.network.service.ApiServiceUsuario
import com.example.patrigod.databinding.ActivityRegistroBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class Register : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegistroBinding

    @Inject
    lateinit var apiServiceUsuario: ApiServiceUsuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)
        setupRegisterListeners()
    }

    private fun setupRegisterListeners() {
        registerBinding.btnRegisterInRegister.setOnClickListener {
            val dni = registerBinding.editUserRegister.text.toString().trim()
            val password = registerBinding.editPassRegister.text.toString().trim()


            if (dni.isNotEmpty() && password.isNotEmpty()) {

                    registerUser(dni, password)

            } else {
                Toast.makeText(this, "Tienes algún campo vacío", Toast.LENGTH_SHORT).show()
            }
        }

        registerBinding.btnLastRegister.setOnClickListener {
            val loginIntent = Intent(this, Login::class.java)
            startActivity(loginIntent)
            finish()
        }
    }

    private fun registerUser(dni: String, password: String) {
        val numRandom = Random(12345).nextInt()
        val requestUsuario = RequestUsuarioEntero(dni, "alexc@gmail.com", password, "udff", "udfdf", "")

        lifecycleScope.launch {
            val result = apiServiceUsuario.registerService(requestUsuario)
            result.fold(
                onSuccess = { response ->
                    Toast.makeText(this@Register, "Registro exitoso", Toast.LENGTH_LONG).show()
                    val mainIntent = Intent(this@Register, MainActivity::class.java)
                    startActivity(mainIntent)
                    finish()
                },
                onFailure = { throwable ->
                    Log.e("RegisterError", "Error en el registro", throwable) // Ver el error completo
                    val errorMsg = throwable.message ?: "Error desconocido en el registro"
                    Toast.makeText(this@Register, "Errorrrr: $errorMsg", Toast.LENGTH_LONG).show()
                }

            )
        }
    }
}
