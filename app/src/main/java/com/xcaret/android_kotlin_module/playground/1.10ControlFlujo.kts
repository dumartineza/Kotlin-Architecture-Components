package com.xcaret.android_kotlin_module.playground/* ============================================================================
   1.10 CONTROLES DE FLUJO
   -----------------------------------------------------------------------------
   DEFINICIÓN:
   - Estructuras que controlan el flujo de ejecución del programa
   - if, when, for, while son expresiones (pueden retornar valores)

   USO:
   - if/else: condiciones simples
   - when: alternativa poderosa a switch, soporta múltiples condiciones
   - for: iteración sobre rangos y colecciones
   - while/do-while: bucles con condiciones

   NOTAS IMPORTANTES:
   - 'when' es más expresivo que switch de Java
   - No hay operador ternario (?:), usar if como expresión
   - 'break' y 'continue' disponibles en bucles
   ============================================================================ */

fun ejemplosControlesFlujo() {
    val numero = 5

    // if como expresión
    val resultado = if (numero > 0) "positivo" else "no positivo"

    // when (similar a switch pero más poderoso)
    val descripcion = when (numero) {
        0 -> "cero"
        1, 2, 3 -> "pequeño"
        in 4..10 -> "mediano"
        !in 0..100 -> "fuera de rango"
        else -> "grande"
    }

    // when sin argumento (como if-else-if)
    val tipo = when {
        numero < 0 -> "negativo"
        numero == 0 -> "cero"
        numero > 0 -> "positivo"
        else -> "desconocido"
    }

    // for con rangos
    for (i in 1..5) {
        println(i)
    }

    // for con colecciones
    val lista = listOf("a", "b", "c")
    for (elemento in lista) {
        println(elemento)
    }

    // for con índice
    for ((indice, valor) in lista.withIndex()) {
        println("$indice: $valor")
    }

    // while
    var contador = 0
    while (contador < 5) {
        println(contador)
        contador++
    }

    // do-while
    do {
        println(contador)
        contador--
    } while (contador > 0)

    // Labels y break/continue
    bucleExterno@ for (i in 1..3) {
        for (j in 1..3) {
            if (i == 2 && j == 2) break@bucleExterno
            println("$i, $j")
        }
    }
}

println("\n10. Controles de Flujo")
ejemplosControlesFlujo()