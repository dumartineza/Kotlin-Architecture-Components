package com.xcaret.android_kotlin_module.playground/* ============================================================================
   1.14 EXTENSION FUNCTIONS
   -----------------------------------------------------------------------------
   DEFINICIÓN:
   - Funciones que extienden una clase existente sin heredar de ella
   - Añaden funcionalidad a clases sin modificar su código fuente

   USO:
   - fun TipoReceptor.nombreFuncion() { }
   - Útiles para añadir utilidades a clases de librerías
   - No pueden acceder a miembros privados de la clase

   NOTAS IMPORTANTES:
   - Se resuelven estáticamente (no son polimórficas)
   - No modifican la clase real, solo añaden sintaxis conveniente
   - 'this' refiere a la instancia receptora
   ============================================================================ */

// Extension function para String
fun String.esPalindromo(): Boolean {
    val limpio = this.lowercase().replace(" ", "")
    return limpio == limpio.reversed()
}

// Extension function con parámetros
fun String.repetir(veces: Int): String {
    return this.repeat(veces)
}

// Extension function para List
fun <T> List<T>.segundoElemento(): T? {
    return if (this.size >= 2) this[1] else null
}

// Extension property
val String.primerCaracter: Char?
    get() = this.firstOrNull()

// Extension function para tipos nullable
fun String?.orVacio(): String {
    return this ?: ""
}

fun ejemplosExtensionFunctions() {
    // Usar extension functions
    println("anita lava la tina".esPalindromo())  // true
    println("Hola".repetir(3))  // HolaHolaHola

    val lista = listOf(1, 2, 3, 4)
    println(lista.segundoElemento())  // 2

    val texto = "Kotlin"
    println(texto.primerCaracter)  // K

    val nulo: String? = null
    println(nulo.orVacio())  // ""
}

// Extension functions útiles personalizadas
fun Int.esPar(): Boolean = this % 2 == 0
fun Int.esImpar(): Boolean = this % 2 != 0

fun Double.formatearDinero(): String = "$${String.format("%.2f", this)}"

fun <T> List<T>.intercambiar(index1: Int, index2: Int): List<T> {
    val resultado = this.toMutableList()
    val temp = resultado[index1]
    resultado[index1] = resultado[index2]
    resultado[index2] = temp
    return resultado
}

println("\n14. Extension Functions")
ejemplosExtensionFunctions()