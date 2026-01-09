package com.xcaret.android_kotlin_module.playground/* ============================================================================
   1.13 NULIDAD
   -----------------------------------------------------------------------------
   DEFINICIÓN:
   - Sistema de tipos que distingue referencias nullable de non-nullable
   - Previene NullPointerException en tiempo de compilación
   
   USO:
   - Type? indica que puede ser null
   - ?. (safe call): ejecuta solo si no es null
   - ?: (Elvis operator): valor por defecto si es null
   - !! (not-null assertion): fuerza unwrap (lanza excepción si es null)
   
   NOTAS IMPORTANTES:
   - Por defecto, las variables NO pueden ser null
   - Evitar !! a menos que estés seguro
   - let con safe call es un patrón común
   ============================================================================ */

data class Usuario(
    val id: Int,
    val nombre: String,
    val email: String,
    var activo: Boolean = true
)

fun ejemplosNulidad() {
    // Variables non-nullable
    var nombre: String = "Ana"
    // nombre = null  // Error de compilación

    // Variables nullable
    var apellido: String? = "García"
    apellido = null  // OK

    // Safe call (?.)
    val longitud = apellido?.length  // Int? (puede ser null)

    // Elvis operator (?:)
    val longitudSegura = apellido?.length ?: 0  // Int (nunca null)

    // Not-null assertion (!!)
    //val longitudForzada = apellido!!.length  // Lanza excepción si es null

    // let con safe call
    apellido?.let {
        println("El apellido es: $it")
        //println("Longitud: ${it.length}")
    }

    // Verificación explícita
    if (apellido != null) {
        // Smart cast: dentro del bloque, apellido es String (no String?)
        println(apellido.length)
    }

    // Safe cast (as?)
    val obj: Any = "Hola"
    val str: String? = obj as? String  // null si no se puede castear

    // Colecciones con elementos nullable
    val lista: List<String?> = listOf("a", null, "b")
    val listaNoNulls = lista.filterNotNull()  // List<String>
}

// Función que retorna nullable
fun buscarUsuario(id: Int): Usuario? {
    return if (id > 0) Usuario(id, "Usuario $id", "user$id@email.com") else null
}

fun ejemploFuncionNullable() {
    val usuario = buscarUsuario(1)

    // Encadenar operaciones seguras
    val email = buscarUsuario(1)?.email?.uppercase() ?: "Sin email"

    // takeIf/takeUnless
    val numeroPositivo = 5.takeIf { it > 0 }  // 5
    val numeroNegativo = (-5).takeIf { it > 0 }  // null
}

println("\n13. Nulidad")
//ejemplosNulidad()
buscarUsuario(0)
ejemploFuncionNullable()