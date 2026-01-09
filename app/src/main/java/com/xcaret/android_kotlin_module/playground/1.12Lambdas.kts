package com.xcaret.android_kotlin_module.playground/* ============================================================================
   1.12 LAMBDAS
   -----------------------------------------------------------------------------
   DEFINICIÓN:
   - Funciones anónimas que pueden ser tratadas como valores
   - Sintaxis: { parámetros -> cuerpo }

   USO:
   - Higher-order functions (funciones que reciben/retornan funciones)
   - Callbacks y listeners
   - Operaciones sobre colecciones (map, filter, etc.)

   NOTAS IMPORTANTES:
   - 'it' es el nombre por defecto para un solo parámetro
   - Si la lambda es el último argumento, puede ir fuera de paréntesis
   - Pueden capturar variables del contexto (closures)
   ============================================================================ */

fun ejemplosLambdas() {
    // Lambda básica
    val suma = { a: Int, b: Int -> a + b }
    println(suma(3, 5))  // 8

    // Lambda con un parámetro (usando 'it')
    val doble = { numero: Int -> numero * 2 }
    val triple: (Int) -> Int = { it * 3 }

    // Higher-order function
    fun operar(a: Int, b: Int, operacion: (Int, Int) -> Int): Int {
        return operacion(a, b)
    }

    val resultado1 = operar(5, 3) { x, y -> x + y }
    val resultado2 = operar(5, 3) { x, y -> x * y }

    // Lambdas con colecciones
    val numeros = listOf(1, 2, 3, 4, 5)

    val pares = numeros.filter { it % 2 == 0 }
    val cuadrados = numeros.map { it * it }
    val suma_total = numeros.reduce { acc, n -> acc + n }

    // Lambda de múltiples líneas
    val procesarLista = numeros.map { numero ->
        val cuadrado = numero * numero
        val cubo = cuadrado * numero
        cubo
    }

    // Trailing lambda (lambda fuera de paréntesis)
    numeros.forEach { numero ->
        println(numero)
    }

    // Closures (captura de variables)
    var contador = 0
    val incrementar = {
        contador++
        println("Contador: $contador")
    }
    incrementar()  // Contador: 1
    incrementar()  // Contador: 2
}

// Function types y referencias a funciones
fun ejemploFunctionTypes() {
    // Tipo de función
    val operacion: (Int, Int) -> Int = { a, b -> a + b }

    // Referencia a función
    fun multiplicar(a: Int, b: Int) = a * b
    val ref = ::multiplicar
    println(ref(4, 5))
}

println("\n12. Lambdas")
ejemplosLambdas()