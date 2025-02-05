# **PATRIGOD**

<div style="text-align: center;">
   <img src="https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEjuojbWAX_oT6z65Wkl0w6ks9Ls5DIV7rsSh0_woEEqIorCN1mho55_hLIgmcxynmeoBgWoEVsBCOSMR-Iv9BZleW5MT263xu42W2Xtit-X3SaiewwQ8uD7a5-z3bl6VUAW9xRugvOyQkY/s1600/PAtrimonio+Mundial.png" alt="Patrigod" width="200" height="200" />
</div>

---

## **Descripción del Proyecto**

Este proyecto forma parte del módulo de **Desarrollo de Aplicaciones Multiplataforma**.

El objetivo de esta aplicacion es desarrollar una aplicación que gestione información sobre la cultura presente en las **Ciudades Patrimonio de la Humanidad**.

---

## **Audiencia Objetivo**

La aplicación está diseñada para ser útil a:

- 🧳 **Turistas** interesados en explorar ciudades patrimonio.
- 🏛️ **Amantes del patrimonio cultural de España**.
- 🌍 **Cualquier persona** que desee conocer más sobre la riqueza cultural de estas ciudades.

---

### 🌟 Tecnologías Utilizadas

- **Kotlin**: Lenguaje principal de desarrollo.
- **Android Studio**: IDE para el desarrollo de aplicaciones Android.
- **XML**: Diseño de interfaces y layouts.
- **SharedPreferences**: Almacenamiento y manejo de datos persistentes (incluyendo binding).

---
## **Credenciales para Iniciar Sesión**

- **Cuenta**: alexpruebapatrigod@gmail.com
- **Contraseña**: 123456

---

## CARACTERISTICAS AÑADIDAS ACTUALMENTE

#### I. Login

1. **Login Sencillo**
   - Implementación de una pantalla de inicio de sesión básica.
   - Autenticación mediante constantes predefinidas.
   - Uso de SharedPreferences para mantener la sesión del usuario.

2. **Navegación a Activity Principal**
   - Uso de Intent explícito para navegar desde el login a la actividad principal.

---



### II. Creación POJO

1. **Creación de las siguientes carpetas**
   - `adapter`
   - `controler`
   - `dao`
   - `dialogues`
   - `interfaces`
   - `models`
   - `objects_models`

2. **Agregación de dos nuevas vistas**
   - Vista en el `Main` para ver las clases POJO.
   - Vista `CardView` donde irá la creación de nuestras vistas.

3. **Creación de clases para repositorio, POJO e interfaces**
   - `Repositorio`: contendrá la información de cada uno de los `CardViews`.
   - `Monumento`: será el objeto de mi POJO.
   - `Interfaz`: implementará los métodos necesarios.

4. **Programación del Adapter y el ViewHolder**
   - `AdapterMonumento`: creará la vista reiterada veces para generar los `CardViews`.
   - `ViewMonumento`: donde se renderizará la información de los monumentos en cada `CardView`.

   **Nota sobre Glide:**
   Se necesitan permisos de internet en la aplicación para cargar las URL de las imágenes.

5. **Creación del DAO (con patrón Singleton)**
   - Utilización del `by lazy`.

6. **Programación del Adapter y el ViewHolder**
   - Lógica para eliminar un elemento del `CardView`.
   - Lógica para mostrar cada elemento en el `MainActivity`, referenciándolo desde el Adapter.

7. **Aportación del MainActivity**
   - Creación del `MainActivity` con todo lo realizado en este bloque.

---


#### III. Implementar los Listener

1. **Modificación del método `setAdapter` en el Controller:**
   - Se añadieron dos funciones Lambda (`borrar` y `actualizar`).

2. **Modificación del constructor de `AdapterMonumento`:**
   - Se agregó la recepción de dos funciones (`borrar` y `actualizar`).

3. **Paso de las funciones al ViewHolder:**
   - Uso de `ViewMonumento` para gestionar eventos.

4. **Implementación de lógica de eventos en `ViewMonumento`:**
   - Eventos de clic para botones de actualizar y eliminar.

5. **Lógica en el Controller:**
   - Se definió el comportamiento al pulsar los botones de eliminar o actualizar.

---

#### III. Creación Fragmentos para Añadir, Borrar y Editar

1. **Fragmento para Borrar**
   - Verificación para confirmar la eliminación de un elemento de la clase POJO.

2. **Fragmento para Editar**
   - Permite modificar un elemento existente en la clase POJO.

3. **Fragmento para Crear**
   - Habilita la creación de un nuevo elemento en la clase POJO.

---


#### VI. Implementacion Firebase para el LOGIN.
En esta parte lo que he introducido es a partir de mi sistema de logueo, he introducido Firebase para tener un sistema para registrar y tener una base de datos de las personas que pueden utilizar mi aplicacion. Además he introducido un registro para que el usuario tenga una cuenta en mi aplicación y un método para cambiar la contraseña por si el usuario no se acordase de ella.




#### VI.I Introducir Firebase en el Proyecto
---
#### 1. Crear cuenta en Firebase
- Me he registrado en [Firebase](https://firebase.google.com).

#### 2. Crear proyecto en Firebase
- He ido a la consola de Firebase y me he creado un nuevo proyecto.

#### 3. Registrar tu App Android con el proyecto de Firebase
- Agrego una nueva aplicación Android.
- Introduzco el **nombre del paquete** de mi aplicación.
- Clickeo en **Registrar app**.

#### 4. Agregar la configuración necesaria en tu App
- Descargo el archivo `google-services.json` desde la consola de Firebase.
- Coloco el archivo en el directorio `app/` de mi proyecto Android.

#### 5. Configurar el archivo `build.gradle`
##### a. Configuración en el Gradle del proyecto:
```gradle
id("com.google.gms.google-services") version "4.4.2" apply false
```

##### b. Configuración en el Gradle del módulo:
```gradle
id("com.google.gms.google-services")
```

##### c. Añadir las dependencias de Firebase al Gradle del módulo:
```gradle
// Import the Firebase BoM
implementation(platform("com.google.firebase:firebase-bom:33.6.0"))

// Add the dependency for the Firebase SDK for Google Analytics
implementation("com.google.firebase:firebase-analytics")
```

#### 6. Sincronizar todo
1. Sincronizo todos los cambios en Gradle para asegurarte de que todo esté configurado correctamente.



#### 7. Configurar una cuenta de Gmail
1. He necesitado una cuenta de Gmail para:
   - Confirmar la creación de usuarios en tu aplicación.
   - Enviar correos para restablecer contraseñas.
2. Ejemplo de cuenta creada: **alexpruebapatrigod@gmail.com**.

---

#### VI.II. Gestión de Usuarios con Firebase
---
#### **1. Registro de Usuario**
1. **Configuración Inicial**:
   - Creo un objeto de autenticación con:
     ```kotlin
     val auth = Firebase.auth
     ```

2. **Método `registerUser`**:
   - Recibo el email y contraseña del usuario.
   - Llamo a `createUserWithEmailAndPassword(email, pass)` para registrar al usuario:
     ```kotlin
     auth.createUserWithEmailAndPassword(email, pass)
     ```
   - Verifico el éxito del registro con `addOnCompleteListener`:
     ```kotlin
     task.isSuccessful
     ```
   - Si el registro es exitoso:
      - Obtengo el usuario registrado con:
        ```kotlin
        val user = auth.currentUser
        ```
      - Envío un correo de verificación:
        ```kotlin
        user?.sendEmailVerification()
        ```
      - Manejo el resultado del envío con:
        ```kotlin
        .addOnCompleteListener { taskVerification -> 
            taskVerification.isSuccessful 
        }
        ```
      - Capturo errores críticos con:
        ```kotlin
        .addOnFailureListener { exception -> }
        ```
     Metodo que realizo en el proyecto:
       ```kotlin
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

                }
                else{
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
   ```

#### **2. Inicio de Sesión**
1. **Método `startLogin`**:
   - Recibo email y contraseña.
   - Llamo a `signInWithEmailAndPassword(user, pass)` para iniciar sesión:
     ```kotlin
     auth.signInWithEmailAndPassword(email, pass)
     ```
   - Verifico el estado del login con `addOnCompleteListener`:
     ```kotlin
     task.isSuccessful
     ```
   - Si el login es exitoso:
      - Compruebo si la cuenta está verificada:
        ```kotlin
        auth.currentUser?.isEmailVerified
        ```
      - Devuelve el resultado según la verificación del correo.

     Metodo que utilizo en el proyecto :
     ```kotlin
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
     ```

#### **3. Recuperación de Contraseña**
1. **Método `recoverPassword`**:
   - Recibo el email del usuario.
   - Llamo a `sendPasswordResetEmail(email)` para enviar un correo de recuperación:
     ```kotlin
     auth.sendPasswordResetEmail(email)
     ```
   - Confirmo el envío exitoso con `addOnCompleteListener`:
     ```kotlin
     task.isSuccessful
     ```
   - Manejo errores de envío con un listener adicional:
     ```kotlin
     .addOnFailureListener { exception -> }
     ```
     Metodo que utilizo en el proyecto :
     ```kotlin
         private fun recoverPassword(email : String, onResult: (Boolean, String)->Unit) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener{
                    taskResetEmail ->
                if (taskResetEmail.isSuccessful){
                    onResult (true, "Acabamos de enviarte un email con la nueva password")
                }else{
                    var msg = ""
                    try{
                        throw taskResetEmail.exception?:Exception("Error de reseteo inesperado")
                    }catch (e : FirebaseAuthInvalidCredentialsException){
                        msg = "El formato del email es incorrecto"
                    }catch (e: Exception){
                        msg = e.message.toString()
                    }
                    onResult(false, msg)


                }
            }     
     ```
#### **4. Cerrar Sesion para volver al login**
En esta aplicación, se ha implementado un mecanismo para gestionar la sesión del usuario mediante Firebase Authentication. Esto incluye tanto la verificación de usuarios activos como el cierre de sesión.

## **Verificación de Usuario Activo**

Al iniciar la aplicación, se comprueba si hay un usuario ya autenticado. Si el usuario está autenticado y su correo electrónico está verificado, es redirigido automáticamente a la pantalla principal (`MainActivity`). De lo contrario, será llevado al login.

### **Código para Verificación de Usuario Activo**

```kotlin
val currentUser = FirebaseAuth.getInstance().currentUser
if (currentUser != null && currentUser.isEmailVerified) {
   // Usuario autenticado y correo verificado
   val mainIntent = Intent(this, MainActivity::class.java)
   startActivity(mainIntent)
} else {
   // Usuario no autenticado o sin verificar
   val loginIntent = Intent(this, Login::class.java)
   startActivity(loginIntent)
}
```

## **Cierre de Sesión y permanencia en la aplicación**
El usuario puede cerrar sesión desde la actividad principal (`MainActivity`). Al hacerlo:

1. Se cierra la sesión en Firebase Authentication usando `auth.signOut()`.
2. Se redirige al usuario a la pantalla de inicio de sesión (`LoginActivity`).
3. Se asegura que el usuario no pueda volver a la actividad principal utilizando las banderas `FLAG_ACTIVITY_NEW_TASK` y `FLAG_ACTIVITY_CLEAR_TASK`.

### **Código para el Cierre de Sesión**

```kotlin
private fun logout() {
   // Cerrar sesión en Firebase
   auth.signOut()

   // Redirigir al LoginActivity
   val loginIntent = Intent(this, Login::class.java)
   loginIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
   startActivity(loginIntent)
   finish()
}
```
---

#### VII. Navegation Drawer
En este apartado he aportado la navegacion en mi aplicacion por el cual he incluido:
- Menu toolbar arriba del todo
- Menu lateral
- Menu Botton

Lo primero que hago es crear el menu, creandome asi la carpeta menu automaticamente en
res y luego creare el menu lateral con los diferentes items que ponga.

Luego he creado la navegacion en la cual me crea la carpeta sola y creado asi el grafico de navegacion
por la cual he llamado nav_graph que va a contener toda la navegacion de PatriGod(muy importante).

A partir de esto voy creando poco a poco los menu y los voy integrando al main activity y segun lo trabajado en clase
lo hago mediante `include` para realizarlo por partes porque como dice el gran dicho: `Divide y venceras`. Es una forma de hacerlo mas ordenado y verlo mejor visualmente.

###### activity_main.xml
```xml
<?xml version ="1.0" encoding="utf-8"?>
<androidx.drawerlayout.widget.DrawerLayout
    android:id="@+id/main"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width ="match_parent"
    android:layout_height ="match_parent"
    android:background="@color/white"
    tools:context=".MainActivity" >

    <include
        android:id="@+id/appBarConfiguration"
        layout="@layout/app_bar_layout_drawer"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <com.google.android.material.navigation.NavigationView
        android:id="@+id/my_nav_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        android:layout_marginTop="30dp"
        app:headerLayout="@layout/nav_header"
        app:menu="@menu/nav_menu_extend" />
</androidx.drawerlayout.widget.DrawerLayout>
```
En este apartado, además  incluyo un **Bottom Navigation** que proporciona la navegación desde la parte inferior de la aplicación. Para ello, se han realizado las siguientes tareas:

1. Creación de un Nuevo Menú
   Se ha creado un nuevo archivo de menú para definir los elementos del **Navigation Bottom**.

2. Archivo en el Layout para el Navigation Bottom
   Se ha añadido un archivo de diseño en el layout que contendrá el **Navigation Bottom**.

3. Uso de `include` para Navegación
   Se ha utilizado un `include` en el archivo principal para incorporar la navegación y poder enlazarla con otras vistas o fragmentos.

---

#### VIII.  FragmetoDetalle
Voy a realizar el tema del fragmentoDetalle:
El objetivo de esto es crear un fragmento que reciba los detalles de un ítem específico basado en su **ID** para que el usuario pueda leer bien los detalles de ese usuario.

### Pasos Realizados
1. **Creación del Fragmento**: He creado un nuevo fragmento llamado `DetallesFragment`.
2. **Navegación Segura**: utilizo la navegación segura para pasar el **ID** del ítem seleccionado al fragmento.
3. **Extracción de Datos**: Obtengo el objeto `Monumento` utilizando el ID y muestro sus atributos en el fragmento.
4. **Incrustación de Imagen con Glide**: Utilizo la biblioteca **Glide** para cargar imágenes desde una URL.
   DetallesFragment:
```java
class DetallesFragment : Fragment() {
        private lateinit var binding: FragmentDetallesBinding
        private val args: DetallesFragmentArgs by navArgs()


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            binding = FragmentDetallesBinding.inflate(inflater, container, false)
            return binding.root
        }
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val idItem = args.idItem

            val monumento = MonumentoDAO.myDao.getDataMonuments()[idItem]

            binding.nameMonument.text = monumento.nombre
            binding.descriptionMonument.text = monumento.descripcionPlus
            Glide.with(this)//con glide incrusto la imagen mediante una url
                .load(monumento.imagen)
                .centerCrop()
                .into(binding.imageMonument)

        }


    }

```


---

#### VIII. Lista genérica para cualquier usuario (ANUNCIOS)

En este apartado, se me ha solicitado crear una lista de objetos en la cual cualquier usuario que vea mi aplicación pueda visualizar, pero sin la posibilidad de modificarla. Este requerimiento se logra mediante la implementación de un **adapter**, similar al que utilicé anteriormente para la lista de monumentos. A continuación, explico los pasos y detalles de la implementación:

1. **Definición del modelo de datos:**
   Primero, creamos una clase `Anuncio` que representará los anuncios que se mostrarán en la lista. Cada objeto de esta clase contiene información relevante como el título, la ciudad, la fecha, la información adicional y la imagen asociada.

   ```kotlin
   data class Anuncio(
       val id: Int,
       val nombre: String,
       val ciudad: String,
       val fecha: String,
       val informacion: String,
       val imagenUrl: String
   )
    ```
2. **Clase para los datos de los anuncios:**

   Creamos una clase `DatosAnuncios` que contiene una lista inmutable de objetos `Anuncio`, la cual será utilizada para mostrar los anuncios en la aplicación.

   ```kotlin
   class DatosAnuncios {
       var listAnuncios: List<Anuncio> = listOf(
           Anuncio(0, "¿Los ochios de dónde son?", "Úbeda", "19/12/2023", "Los ochios originalmente son de Úbeda", "https://upload.wikimedia.org/wikipedia/commons/8/86/Ochios_Andaluces.jpg"),
           Anuncio(1, "¿Qué monumento visitar en Baeza?", "Baeza", "20/12/2023", "La Catedral de Baeza es uno de los lugares más emblemáticos.", "https://upload.wikimedia.org/wikipedia/commons/9/91/Baeza-Catedral-2007-08.jpg"),
           Anuncio(2, "Fiestas de Úbeda", "Úbeda", "21/12/2023", "Las fiestas de San Miguel en Úbeda son imperdibles.", "https://upload.wikimedia.org/wikipedia/commons/4/47/Ubeda_Paseo.JPG"),
           Anuncio(3, "Mejor época para visitar Granada", "Granada", "22/12/2023", "La primavera es ideal para disfrutar de la Alhambra y los alrededores.", "https://upload.wikimedia.org/wikipedia/commons/e/e8/Alhambra_-_Granada%2C_Spain_-_panoramio.jpg"),
           Anuncio(4, "¿Qué hace especial a la Alhambra?", "Granada", "23/12/2023", "La Alhambra es famosa por su arquitectura nazarí y vistas espectaculares.", "https://upload.wikimedia.org/wikipedia/commons/8/87/Alhambra_-_Generalife.jpg")
       )
   }
    ```

3. **Creación del Adapter**

Se implementa un `RecyclerView.Adapter` para vincular los datos de la lista `listAnuncios` con la vista de la interfaz. Este adapter se encarga de inflar los elementos de la lista en un `CardView` para que cada anuncio se muestre de forma estilizada en la interfaz de usuario.


```kotlin
class AnunciosAdapter(private val anuncios: List<Anuncio>) : RecyclerView.Adapter<AnunciosAdapter.AnuncioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnuncioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_anuncio, parent, false)
        return AnuncioViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnuncioViewHolder, position: Int) {
        val anuncio = anuncios[position]
        holder.bind(anuncio)
    }

    override fun getItemCount(): Int {
        return anuncios.size
    }

    inner class AnuncioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNombre: TextView = itemView.findViewById(R.id.tv_nombre)
        private val tvCiudad: TextView = itemView.findViewById(R.id.tv_ciudad)
        private val tvFecha: TextView = itemView.findViewById(R.id.tv_fecha)
        private val tvInformacion: TextView = itemView.findViewById(R.id.tv_informacion)
        private val ivFoto: ImageView = itemView.findViewById(R.id.ivFoto)

        fun bind(anuncio: Anuncio) {
            tvNombre.text = anuncio.nombre
            tvCiudad.text = anuncio.ciudad
            tvFecha.text = anuncio.fecha
            tvInformacion.text = anuncio.informacion
            Picasso.get().load(anuncio.imagenUrl).into(ivFoto)  // Usando Picasso para cargar la imagen desde la URL
        }
    }
}
```
4. **Implementación del RecyclerView en la actividad**

Finalmente, en la actividad donde se mostrará la lista de anuncios, se inicializa un `RecyclerView` y se asigna el `Adapter` que creamos para vincular los anuncios a la interfaz de usuario.

```kotlin
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AnunciosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar el RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Crear y asignar el Adapter con la lista de anuncios
        val datosAnuncios = DatosAnuncios()  // Obtén la lista de anuncios
        adapter = AnunciosAdapter(datosAnuncios.listAnuncios)  // Crea el Adapter
        recyclerView.adapter = adapter  // Asigna el Adapter al RecyclerView
    }
}
```

---

# Version 2.1 MVVM y HILT

## Introducción
El proyecto **PatriGod** utiliza la arquitectura **Clean Architecture** para garantizar un diseño limpio, mantenible y escalable. Esta arquitectura organiza el código en capas bien definidas, asegurando que cada componente tenga una responsabilidad específica y reduciendo el acoplamiento entre ellos.

## ¿Qué es Clean Architecture?
**Clean Architecture** es un enfoque de diseño de software propuesto por Robert C. Martin (**Uncle Bob**) que enfatiza la separación de preocupaciones. Este modelo organiza el código en capas que giran en torno a la lógica de negocio, protegiéndola de detalles externos como frameworks, bases de datos o interfaces de usuario.

### Principios básicos de Clean Architecture:
1. **Independencia de Frameworks**: El sistema no depende de ningún framework específico.
2. **Testabilidad**: Es fácil realizar pruebas unitarias en la lógica de negocio.
3. **Independencia de UI**: La lógica de negocio no depende de la implementación de la interfaz de usuario.
4. **Independencia de Bases de Datos**: La lógica de negocio no depende de ninguna tecnología de almacenamiento.
5. **Separación de responsabilidades**: Cada capa tiene una función específica y bien definida.

---

## Estructura del Proyecto
El proyecto está dividido en tres capas principales: `data`, `domain` y `ui`. A continuación, se explica cada una de estas capas y sus submódulos.

### 1. **Capa Data**
Esta capa es responsable de manejar los datos. Aquí se encuentran los modelos de datos, las conexiones con las fuentes de datos (APIs, bases de datos, etc.) y los repositorios que sirven como mediadores entre la capa de dominio y las fuentes de datos.

#### Submódulos:
- **monumentos**
    - `objects_models`: Define los objetos de datos utilizados en esta capa.
    - `repository`: Contiene las implementaciones de los repositorios encargados de gestionar los datos de los monumentos.
- **users**: Gestiona los datos relacionados con los usuarios.

---

### 2. **Capa Domain**
Es la capa central de la arquitectura, donde reside la lógica de negocio de la aplicación. Es independiente de cualquier detalle de implementación.

#### Submódulos:
- **monumentos**
    - `interfaces`: Define contratos o interfaces que deben implementarse en la capa `data`.
    - `models`: Contiene los modelos de dominio, representaciones puras de los datos relevantes.
    - `usecases`: Implementa los casos de uso, la lógica de negocio que conecta las interfaces con los repositorios.
- **usuarios**: Define los modelos y casos de uso relacionados con los usuarios.

---

### 3. **Capa UI**
Esta capa es responsable de la interfaz de usuario y de interactuar con el usuario. Se comunica con la capa de dominio mediante `ViewModels`.

#### Submódulos:
- **viewModel**
    - `monumentos`: Define los `ViewModels` que gestionan la lógica entre la UI y la capa de dominio para los monumentos.
    - `usuarios`: Define los `ViewModels` para los usuarios.
- **views**
    - `activities`: Contiene las actividades principales de la aplicación.
    - `fragments`: Contiene los fragmentos que conforman la interfaz modular.

---

## Ventajas de Clean Architecture en PatriGod
1. **Escalabilidad**: Permite agregar nuevas funcionalidades sin alterar la lógica de negocio o la estructura de datos.
2. **Mantenibilidad**: Las capas bien definidas facilitan la comprensión y el mantenimiento del código.
3. **Testabilidad**: La separación de responsabilidades permite realizar pruebas unitarias de manera sencilla.
4. **Reutilización**: Las capas independientes pueden ser reutilizadas en otros proyectos o partes del sistema.
5. **Flexibilidad tecnológica**: Cambiar detalles de implementación como frameworks o bases de datos es más sencillo porque no están acoplados directamente con la lógica de negocio.

---


# 📌 FragmentoMonumento y MonumentoViewModel

## 🏛 FragmentoMonumento

### 📄 Descripción
Este fragmento gestiona la visualización, adición, edición y eliminación de monumentos en un RecyclerView. Utiliza un `ViewModel` para manejar los datos de forma reactiva.

### 📌 Dependencias
- **Dagger Hilt** para la inyección de dependencias.
- **ViewModel y LiveData** para la gestión de datos.
- **RecyclerView** para la presentación de la lista de monumentos.
- **Coroutines** para llamadas asíncronas.

### 📌 Propiedades
```kotlin
lateinit var binding: FragmentoCardviewBinding
val monumentoViewModel: MonumentoViewModel by viewModels()
private lateinit var layoutManager: LinearLayoutManager
lateinit var adapterMonumento: AdapterMonumento
```

### 📌 Métodos Principales

#### `onViewCreated(view: View, savedInstanceState: Bundle?)`
- Configura el `RecyclerView` con un `LinearLayoutManager`.
- Inicializa el adaptador y los observadores.
- Llama a `showMonumentos()` en el ViewModel.

#### `setObserver()`
- Observa los cambios en los `LiveData` del `ViewModel` y actualiza el adaptador según sea necesario.

#### `btnAddOnClickListener()`
- Muestra un `DialogAddMonumento` para agregar un nuevo monumento.
- Al aceptar, se envía el nuevo monumento al `ViewModel`.

#### `deleteMonumento(pos: Int)`
- Muestra un `DialogDeleteMonumento` para confirmar la eliminación.
- Si el usuario acepta, se envía la acción al `ViewModel`.

#### `updateMonumento(pos: Int)`
- Muestra un `DialogEditMonumento` para editar un monumento existente.
- Al aceptar, se envía la actualización al `ViewModel`.

#### `navigateToDetails(pos: Int)`
- Navega al fragmento de detalles con el ID del monumento seleccionado.

---

## 🏛 MonumentoViewModel

### 📄 Descripción
Gestiona la lógica de negocio relacionada con los monumentos, proporcionando datos al `FragmentoMonumento` mediante `LiveData`.

### 📌 Dependencias
- **Casos de uso** para gestionar los monumentos.
- **LiveData y ViewModel** para la reactividad.
- **Coroutines** para operaciones en segundo plano.

### 📌 Propiedades
```kotlin
val newMonumentoLiveData = MutableLiveData<Monumento>()
val posDeleteHotelLiveDate = MutableLiveData<Int>()
val posUpdateMonumentoLiveData = MutableLiveData<Int>()
val monumentosLiveData = MutableLiveData<List<Monumento>>()
val detailMonumentoLiveData = MutableLiveData<Monumento>()
```

### 📌 Métodos Principales

#### `showMonumentos()`
- Obtiene la lista de monumentos de la base de datos y la actualiza en `monumentosLiveData`.

#### `addMonumento(monumento: Monumento)`
- Agrega un nuevo monumento y actualiza la lista en `monumentosLiveData`.

#### `deleteMonumento(pos: Int)`
- Elimina un monumento y notifica la posición eliminada a `posDeleteHotelLiveDate`.

#### `updateMonumento(id: Int, monumento: Monumento)`
- Actualiza un monumento existente y actualiza la lista en `monumentosLiveData`.

#### `getMonumentosForPosition(pos: Int)`
- Obtiene los datos de un monumento en una posición específica y los almacena en `detailMonumentoLiveData`.

---
