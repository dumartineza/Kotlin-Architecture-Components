package com.xcaret.android_kotlin_module.playground/* ============================================================================
   1.11 NAMED ARGUMENTS Y VALORES POR DEFECTO
   -----------------------------------------------------------------------------
   DEFINICIÓN:
   - Named arguments: llamar funciones especificando nombres de parámetros
   - Valores por defecto: parámetros con valores predefinidos opcionales

   USO:
   - Mejorar legibilidad al llamar funciones con muchos parámetros
   - Evitar múltiples sobrecargas de métodos
   - Cambiar el orden de argumentos

   NOTAS IMPORTANTES:
   - Los argumentos con valores por defecto deben ir al final (o usar named args)
   - Permiten API más flexibles y claras
   - Reducen la necesidad de builder patterns
   ============================================================================ */

// Función con valores por defecto
fun crearUsuario(
    nombre: String,
    edad: Int = 18,
    ciudad: String = "Desconocida",
    activo: Boolean = true
): String {
    return "Usuario: $nombre, $edad años, $ciudad, activo: $activo"
}

fun ejemplosNamedArguments() {
    // Llamada tradicional
    val usuario1 = crearUsuario("Ana", 25, "Madrid", true)

    // Con valores por defecto
    val usuario2 = crearUsuario("Juan")

    // Named arguments (orden diferente)
    val usuario3 = crearUsuario(
        nombre = "Pedro",
        ciudad = "Barcelona",
        edad = 30
    )

    // Mezclar posicionales y named
    val usuario4 = crearUsuario("María", ciudad = "Sevilla")
}

// Caso de uso real: configuración
data class ConfiguracionServidor(
    val host: String = "localhost",
    val puerto: Int = 8080,
    val ssl: Boolean = false,
    val timeout: Long = 30000,
    val maxConexiones: Int = 100
)

fun ejemploConfiguracion() {
    // Solo especificar lo que cambia
    val config = ConfiguracionServidor(
        host = "produccion.com",
        ssl = true
    )
}

println("\n11. Named Arguments")
ejemplosNamedArguments()