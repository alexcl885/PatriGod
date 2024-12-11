package com.example.patrigod

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.patrigod.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.auth

/**
 * Login de PatriGod
 * @author Alejandro Copado Lopez
 * */
class Login : AppCompatActivity() {
    private lateinit var loginBinding : ActivityLoginBinding
    private lateinit var fichero_compartido : SharedPreferences
    private lateinit var intent : Intent
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
        iniciarPreferenciasCompartidas()
        auth = Firebase.auth
        start()


        /*loginBinding.btEntrar.setOnClickListener{
            /**Constantes para entrar a la aplicacion*/
            val USER = "copado"
            val PASSWORD = "copado"
            /**Inputs de los campos de texto*/
            val usuario = loginBinding.email.text.toString()
            val contasena = loginBinding.comtrasena.text.toString()

            /**
             * Logica por el cual si esta en blanco o con espacios (Black) manda un mensaje de error.
             * Si los inputs son igual a las constantes entrara al activity principal.
             * Y si los datos no son ciertos pues se mostrara por pantalla que estan mal.
             * */
            if (usuario.isBlank() || contasena.isBlank()) {
                Toast.makeText(this, "Usuario o contraseña vacíos", Toast.LENGTH_LONG).show()
            } else if (usuario == USER && contasena == PASSWORD) {
                intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("usuario",usuario)
                    putExtra("contrasena", contasena)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
            }

        }*/
    }
    /**
     * Metodo que inicia las preferencias compartidas
     * */
    private fun iniciarPreferenciasCompartidas(){
        val nombreFicheroCompartido = getString(R.string.nombre_fichero_preferencia_compartida)

        this.fichero_compartido = getSharedPreferences(nombreFicheroCompartido, MODE_PRIVATE)
    }
    private fun start() {
        loginBinding.btEntrar.setOnClickListener {
            val usuario = loginBinding.email.text.toString()
            val contasena = loginBinding.comtrasena.text.toString()

            if (usuario.isNotEmpty() && contasena.isNotEmpty())
                startLogin(usuario, contasena){
                        result, msg ->
                    Toast.makeText(this@Login, msg, Toast.LENGTH_LONG).show()
                    if (result){
                        intent = Intent(this@Login, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            else
                Toast.makeText(this, "Tienes algún campo vacío", Toast.LENGTH_LONG).show()

        }
    }
    private fun startLogin(user: String, pass: String, onResult: (Boolean, String) -> Unit) {
        auth.signInWithEmailAndPassword(user, pass)
            .addOnCompleteListener {
                    taskAssin ->
                var msg = ""
                if (taskAssin.isSuccessful){
                    //debemos comprobar si el usuario ha verificado el email
                    val posibleUser = auth.currentUser
                    if (posibleUser?.isEmailVerified == true){
                        onResult ( true, "Usuario Logueado satisfactoriamente")
                    }else{
                        auth.signOut() //hay que desloguearse, porque no ha verificado.
                        onResult (false, "Debes verificar tu correo antes de loguearte")
                    }
                }else{

                    try {
                        throw taskAssin.exception?: Exception("Error desconocido")
                    }catch (e: FirebaseAuthInvalidUserException){
                        msg = "El usuario tiene problemas por haberse borrado o desabilitado"
                    }catch (e: FirebaseAuthInvalidCredentialsException){
                        msg = if (e.message?.contains("There is no user record corresponding to this identifier") == true){
                            "El usuario no existe"
                        }else "contraseña incorrecta"

                    }catch (e: Exception){
                        msg = e.message.toString()
                    }

                    onResult (false, msg)  //genérico.
                }

            }

    }


}