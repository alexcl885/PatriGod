# PATRIGOD 
<div style="text-align: center;">
   <img src="https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEjuojbWAX_oT6z65Wkl0w6ks9Ls5DIV7rsSh0_woEEqIorCN1mho55_hLIgmcxynmeoBgWoEVsBCOSMR-Iv9BZleW5MT263xu42W2Xtit-X3SaiewwQ8uD7a5-z3bl6VUAW9xRugvOyQkY/s1600/PAtrimonio+Mundial.png" alt="Patrigod" width="200px" height="200px" />
</div>
Este proyecto parte del módulo de Desarrollo de Aplicaciones Multiplataforma.

Estoy desarrollando una aplicación para gestionar información sobre monumentos, iglesias y todo lo relacionado con la cultura que se puede encontrar en las Ciudades Patrimonio de la Humanidad.

El público para esta aplicación sería para :

- Turistas.
- Personas que le interesan el patrimonio de España.
- Cualquier persona que le interese.

## CARACTERISTICAS AÑADIDAS ACTUALMENTE

#### I. Login

1. **Login Sencillo**
   - Implementación de una pantalla de inicio de sesión básica.
   - Autenticación mediante constantes predefinidas.
   - Uso de SharedPreferences para mantener la sesión del usuario.

2. **Navegación a Activity Principal**
   - Uso de Intent explícito para navegar desde el login a la actividad principal.

---


#### II. Creacion POJO

1. **Creacion de las siguientes carpetas**
   - adapter
   - controler
   - dao
   - dialogues
   - interfaces
   - models
   - objects_models

2. **Agregacion de dos nuevas vistas**
   - Vista en el main para ver las clases pojo
   - Vista cardview donde va ir la vista y la creacion de nuestras views.

3. **Creación de clases para repositorio, pojo e interfaces**
   - Repositorio -> va a estar la informacion de cada una de los cardviews
   - Monumento -> va a ser el objeto de mi pojo
   - Interfaz -> voy a implementar los metodos que tenga que hacer.
4. **Programación del Adapter y el ViewHolder**
   - AdapterMonumento -> creara la vista reiterada veces para crear los cardviews
   -  ViewMonumento -> donde se renderizaran la informacion de los monumentos en cada cardview

   **Para el glide**
   Necesitaremos los permisos de internet en la aplicación para poder cargar las url de las imágenes.

5. **Creación del Dao(con patron singleton)**
   - Utilizacion del by lazy.

6. **Programación del Adapter y el ViewHolder**
   - Creacion de toda la logica para poder eliminar un item del cardview.
   - Creación de la logica para mostrar cada item al main activity referenciando del Adapter(donde he creado cada item e insertado datos).

7. **Aportacion del Main Activity**
   - Creacion del main activity con todo lo realizado de este bloque.

---

### Tecnologías utilizadas

- Kotlin
- Android Studio
- XML para layouts
- SharedPreferences para todo (binding)





