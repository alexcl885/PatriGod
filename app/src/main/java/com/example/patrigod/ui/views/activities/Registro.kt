package com.example.patrigod.ui.views.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.patrigod.R
import com.example.patrigod.databinding.ActivityRegistroBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.auth

class Registro : AppCompatActivity() {

    private lateinit var registerBinding : ActivityRegistroBinding
    private lateinit var fichero_compartido : SharedPreferences
    private lateinit var intent : Intent
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        registerBinding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)
        iniciarPreferenciasCompartidas()
        auth = Firebase.auth
        start()
    }
    private fun iniciarPreferenciasCompartidas(){
        val nombreFicheroCompartido = getString(R.string.nombre_fichero_preferencia_compartida)
        this.fichero_compartido = getSharedPreferences(nombreFicheroCompartido, MODE_PRIVATE)
    }
    private fun start() {
        registerBinding.btnRegisterInRegister.setOnClickListener{
            val email = registerBinding.editUserRegister.text.toString()
            val pass = registerBinding.editPassRegister.text.toString()
            val repeatPass = registerBinding.editPassRegister.text.toString()
            if (pass != repeatPass
                || email.isEmpty()
                || pass.isEmpty()
                || repeatPass.isEmpty())
                Toast.makeText(this, "Campos vacíos y/o password diferentes", Toast.LENGTH_LONG).show()
            else{

                registrarUsuario (email, pass){
                        result, msg ->
                    Toast.makeText(this@Registro, msg, Toast.LENGTH_LONG).show()
                    if (result)
                        startActivityLogin()
                }
            }
        }

        registerBinding.btnLastRegister.setOnClickListener{
            // Como la lambda tiene un parámetro view, el this es el Activity
                view->
            val intent = Intent (this, Login::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun startActivityLogin() {
        //Tengo que lanzar un intent con el Activity a loguear.
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish() //No quiero que sigua el Activity del registro.

    }
    private fun registrarUsuario(email: String, pass: String, onResult: (Boolean, String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this){
                    taskAssin->
                if (taskAssin.isSuccessful){
                    //enviaremos un email de confirmación
                    val user = auth.currentUser
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener{
                                taskVerification ->
                            var msg = ""
                            if (taskVerification.isSuccessful)
                                msg = "Usuario Registrado Ok. Verifique su correo"
                            else
                                msg = "Usuario Registrado Ok. ${taskVerification.exception?.message}"
                            auth.signOut() //tiene que verificar antes el email
                            onResult(true, msg)
                        }
                        ?.addOnFailureListener{
                                exception->
                            Log.e("ActivityRegister", "Fallo al enviar correo de verificación: ${exception.message}")
                            onResult(false, "No se pudo enviar el correo de verificación: ${exception.message}")
                        }

                }else{
                    try{
                        throw taskAssin.exception ?:Exception ("Error desconocido")
                    } catch (e: FirebaseAuthUserCollisionException){
                        onResult (false, "Ese usuario ya existe")
                    }catch (e: FirebaseAuthWeakPasswordException){
                        onResult (false, "La contraseña es débil: ${e.reason}")
                    }
                    catch (e: FirebaseAuthInvalidCredentialsException){
                        onResult (false, "El email proporcionado, no es válido")
                    }
                    catch (e: Exception){
                        onResult (false, e.message.toString())
                    }

                }
            }


    }

}