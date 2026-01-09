package com.xcaret.android_kotlin_module.playground/* ============================================================================
   1.6 DATA CLASSES
   -----------------------------------------------------------------------------
   DEFINICIÓN:
   - Clases especializadas para almacenar datos
   - Generan automáticamente: equals(), hashCode(), toString(), copy(), componentN()

   USO:
   - Declarar con 'data class'
   - Requieren al menos un parámetro en el constructor primario
   - Útiles para DTOs, modelos de datos

   NOTAS IMPORTANTES:
   - Solo las propiedades del constructor primario se usan en equals/hashCode
   - copy() permite crear copias con modificaciones
   - Desestructuración automática con componentN()
   ============================================================================ */

data class Usuario(
    val id: Int,
    val nombre: String,
    val email: String,
    var activo: Boolean = true
)

fun ejemploDataClass() {
    val usuario1 = Usuario(1, "Diego", "d@email.com")
    val usuario2 = Usuario(1, "Diego", "d@email.com")

    // equals() automático
    println(usuario1 == usuario2)  // true

    // toString() automático
    println(usuario1)  // Usuario(id=1, nombre=Ana, email=ana@email.com, activo=true)

    // copy() para crear copias modificadas
    val usuario3 = usuario1.copy(nombre = "Ana María")

    // Desestructuración
    val (id, nombre, email) = usuario1
    println("ID: $id, Nombre: $nombre")
}

println("\n6. Data Classes")
ejemploDataClass()