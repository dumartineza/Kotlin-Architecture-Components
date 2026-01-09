package com.xcaret.android_kotlin_module.playground/* ============================================================================
   1.2 FUNCIONES
   -----------------------------------------------------------------------------
   DEFINICIÓN:
   - Bloques de código reutilizables que pueden recibir parámetros y retornar valores
   - Se declaran con la palabra clave 'fun'

   USO:
   - Funciones de una sola expresión pueden usar sintaxis simplificada (=)
   - Pueden tener parámetros con valores por defecto
   - Unit es el equivalente a void (retorno implícito)

   NOTAS IMPORTANTES:
   - Los parámetros son inmutables (val implícito)
   - Soportan sobrecarga de funciones
   - Pueden ser de nivel superior (fuera de clases)
   ============================================================================ */

// Función simple
fun saludar() {
    println("¡Hola!")
}
saludar()

// Función con parámetros
fun saludar(nombre: String) {
    println("¡Hola, $nombre!")
}
saludar("diego")

// Función con retorno
fun sumar(a: Int, b: Int): Int {
    return a + b
}
sumar(5,2)

// Función sin retorno Unit (explícito o implícito)
fun logTransaction(msg: String): Unit {
    println("[LOG]: $msg")
}
val a = logTransaction("prueba")


// Expresión de función (una línea)
fun multiplicar(a: Int, b: Int): Int = a * b

// Función con tipo inferido
fun dividir(a: Double, b: Double) = a / b

// Función con Valores por Defecto
fun crearUsuario(
    nombre: String,
    edad: Int = 18,
    ciudad: String = "CDMX"
): String {
    return "Usuario: $nombre, $edad años, vive en $ciudad"
}

// Uso
val usuario1 = crearUsuario("Carlos")
val usuario2 = crearUsuario("María", 25)
val usuario3 = crearUsuario("Pedro", 30, "Guadalajara")


// Funciones de Orden Superior recibe funcion como parametro
fun operar(a: Int, b: Int, operacion: (Int, Int) -> Int): Int {
    return operacion(a, b)
}

// Funciones de Orden Superior retorna funcion como parametro
fun obtenerOperacion(tipo: String): (Int, Int) -> Int {
    return when (tipo) {
        "suma" -> { a, b ->
            a + b
        }
        "multiplica" -> { a, b ->
            a * b
        }
        else -> { _, _
            -> 0
        }
    }
}

// Uso
val suma = operar(10, 5) { x, y ->
    x + y
}
println(suma)

val resta = operar(10, 4) { x, y ->
    x - y
}
println(resta)

val operacion = obtenerOperacion("suma")
println(operacion(2, 4)) // 6