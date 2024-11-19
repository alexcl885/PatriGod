# Proyecto de Desarrollo de Aplicaciones Multiplataforma y Dispositivos Móviles

## Aplicación Android con Kotlin

Este proyecto es parte del módulo de Desarrollo de Aplicaciones Multiplataforma. Estamos desarrollando una aplicación Android utilizando Kotlin en Android Studio.

### Características actuales

1. **Login Sencillo**
   - Implementación de una pantalla de inicio de sesión básica.
   - Autenticación mediante constantes predefinidas.
   - Uso de SharedPreferences para mantener la sesión del usuario.

2. **Navegación a Activity Principal**
   - Uso de Intent explícito para navegar desde el login a la actividad principal.

3. **CardView en Activity Principal**
   - Implementación inicial de un CardView en la actividad principal.
   - Este CardView será la base para un futuro CRUD.

## POJO


PASO 1 He creado las siguientes carpetas:
- adapter
- controler
- dao
- dialogues
- interfaces
- models
- objects_models

PASO 2 He agregado dos nuevas vistas:
1. Vista en el main para ver las clases pojo
2. Vista cardview donde va ir la vista y la creacion de nuestras views.

PASO 3 Creo las clases necesarias para el repostitorio, pojo e interfaces.

- Repositorio -> va a estar la informacion de cada una de los cardviews
- Monumento -> va a ser el objeto de mi pojo
- Interfaz -> voy a implementar los metodos que tenga que hacer.

PASO 4 Programo el Adapter y el ViewHolder
Creo dos clases:
1. AdapterMonumento -> creara la vista reiterada veces para crear los cardviews
2. ViewMonumento -> donde se renderizaran la informacion de los monumentos en cada cardview

PASO 5 Creamos el DAO  (con patron singleton)
### Próximos pasos

- Implementar un sistema de autenticación más robusto.
- Desarrollar la funcionalidad CRUD completa utilizando el CardView.
- Mejorar el diseño y la experiencia de usuario.

### Tecnologías utilizadas

- Kotlin
- Android Studio
- XML para layouts
- SharedPreferences para gestión de sesiones

### Cómo ejecutar el proyecto

1. Clona este repositorio.
2. Abre el proyecto en Android Studio.
3. Ejecuta la aplicación en un emulador o dispositivo Android.




