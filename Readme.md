# **PATRIGOD**

<div style="text-align: center;">
   <img src="https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEjuojbWAX_oT6z65Wkl0w6ks9Ls5DIV7rsSh0_woEEqIorCN1mho55_hLIgmcxynmeoBgWoEVsBCOSMR-Iv9BZleW5MT263xu42W2Xtit-X3SaiewwQ8uD7a5-z3bl6VUAW9xRugvOyQkY/s1600/PAtrimonio+Mundial.png" alt="Patrigod" width="200" height="200" />
</div>

---

## **Descripción del Proyecto**

Este proyecto forma parte del módulo de **Desarrollo de Aplicaciones Multiplataforma**.

El objetivo de esta aplicacion es desarrollar una aplicación que gestione información sobre **monumentos**, **iglesias** y otros elementos culturales presentes en las **Ciudades Patrimonio de la Humanidad**.

---

## **Audiencia Objetivo**

La aplicación está diseñada para ser útil a:

- 🧳 **Turistas** interesados en explorar ciudades patrimonio.
- 🏛️ **Amantes del patrimonio cultural de España**.
- 🌍 **Cualquier persona** que desee conocer más sobre la riqueza cultural de estas ciudades.


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

### III. Creación Fragmentos para Añadir, Borrar y Editar

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
         }
     ```


---

### 🌟 Tecnologías Utilizadas

- **Kotlin**: Lenguaje principal de desarrollo.  
- **Android Studio**: IDE para el desarrollo de aplicaciones Android.  
- **XML**: Diseño de interfaces y layouts.  
- **SharedPreferences**: Almacenamiento y manejo de datos persistentes (incluyendo binding).  






