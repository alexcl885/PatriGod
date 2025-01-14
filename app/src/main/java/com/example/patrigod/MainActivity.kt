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
    private lateinit var ficheroCompartido: SharedPreferences
    private lateinit var binding: ActivityMainBinding

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Diseño de borde a borde

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iniciarPreferenciasCompartidas()

        auth = FirebaseAuth.getInstance()

        // configuración del Toolbar
        val toolbar = binding.appBarConfiguration.menu
        setSupportActionBar(toolbar)

        // configuración de Navigation
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.fragmentoCardview, R.id.home, R.id.anuncios), // Destinos de nivel superior
            binding.main // DrawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.myNavView.setupWithNavController(navController)

        /**
         * Navegacion para el menu lateral :)
         * */
        binding.myNavView.setNavigationItemSelectedListener { menuItem ->
            val handled = when (menuItem.itemId) {
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
                R.id.anuncios -> {
                    navController.navigate(R.id.anuncios)
                    true
                }
                else -> false
            }

            if (handled) {
                binding.main.closeDrawer(GravityCompat.START)
            }
            handled
        }
        /*
            Hacemos que el componente de navegación, funcione correctamente con cada uno de los
            elementos del Bottom
            1.- Sin esto, no podrá navegar a ningún destino.
         */
        binding.appBarConfiguration.appBottomBar.myBottonNavigation.setupWithNavController( navController )
    }

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
            R.id.configuracion -> {
                navController.navigate(R.id.configuracion)
                true
            }
            R.id.login -> {
                logout()
                true
            }
            R.id.filtro -> {
                navController.navigate(R.id.filtro)
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
        ficheroCompartido = getSharedPreferences(nombreFicheroCompartido, MODE_PRIVATE)
    }

    /**
     * Método para cerrar sesión
     */
    private fun logout() {
        auth.signOut()
        val loginIntent = Intent(this, Login::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(loginIntent)
    }
}
