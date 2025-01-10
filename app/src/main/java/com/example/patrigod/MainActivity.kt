package com.example.patrigod

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.patrigod.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var fichero_compartido: SharedPreferences
    private lateinit var binding: ActivityMainBinding


    private lateinit var appBarConfiguration: AppBarConfiguration


    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Activamos diseño de borde a borde

        // Inflamos el layout con ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializamos las preferencias compartidas
        iniciarPreferenciasCompartidas()

        // aqui esta la sesion de la persona
        auth = FirebaseAuth.getInstance()

        // configuro el Toolbar
        val toolbar = binding.appBarConfiguration.menu
        setSupportActionBar(toolbar)

        // configuro el NavController y AppBarConfiguration
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHost.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.fragmentoCardview, R.id.home), //los top-level son el crud y el homme
            binding.main
        )


        setupActionBarWithNavController(navController, appBarConfiguration)


        binding.myNavView.setupWithNavController(navController)
        /*Para el memu lateral
        *
        * Con este binding manejo el menu de opciones que tengo en la barra lateral
        * y realiza la accion que desee navegando entre fragmentos
        * */
        binding.myNavView.setNavigationItemSelectedListener { menuItem ->
            val accion = when (menuItem.itemId) {
                R.id.fragmentoCardview -> {
                    navController.navigate(R.id.fragmentoCardview)
                    true
                }
                R.id.login -> {
                    logout()
                    true
                }
                R.id.home -> {
                    navController.navigate(R.id.home)
                    true
                }
                else -> false
            }

            // esto lo que hace es que cuando se pulse una opcion del menu se quitara el menu y dejara toda la vision al fragmento
            if (accion) {
                binding.main.closeDrawer(GravityCompat.START)
            }

            accion
        }
    }

    /**
     * Método para inflar el menú de opciones
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    /**
     * Método para gestionar la navegación hacia arriba
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    /**
     * Método para manejar las opciones seleccionadas en el menú
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.home2 -> {
                navController.navigate(R.id.home) // Navegamos al fragmento correspondiente
                true
            }
            R.id.configuracion ->{
                navController.navigate(R.id.configuracion)
                true
            }
            R.id.login -> {

                logout() // Cerramos sesión cuando se selecciona "logout"
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Inicializamos las preferencias compartidas con el nombre del fichero correspondiente
     */
    private fun iniciarPreferenciasCompartidas() {
        val nombreFicheroCompartido = getString(R.string.nombre_fichero_preferencia_compartida)
        this.fichero_compartido = getSharedPreferences(nombreFicheroCompartido, MODE_PRIVATE)
    }

    /**
     * Método para cerrar sesión
     */
    private fun logout() {
        auth.signOut() // Cerrar la sesión en Firebase
        /*
         * Configuramos las banderas para limpiar la pila de actividades y evitar que el usuario
         * pueda regresar a la pantalla principal o a otras actividades anteriores después de cerrar sesión.
         */
        // Redirigir al LoginActivity
        val loginIntent = Intent(this, Login::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(loginIntent)
    }
}
