package com.xcaret.android_kotlin_module.playground/* ============================================================================
   1.9 RANGOS
   -----------------------------------------------------------------------------
   DEFINICIÓN:
   - Representan un intervalo de valores comparable
   - Útiles para iteraciones y verificaciones de pertenencia

   USO:
   - '..' para rangos inclusivos
   - 'until' para rangos exclusivos
   - 'downTo' para rangos descendentes
   - 'step' para definir el incremento

   NOTAS IMPORTANTES:
   - 'in' verifica si un valor está en el rango
   - Los rangos son lazy (no crean colecciones)
   - Funcionan con cualquier tipo comparable
   ============================================================================ */

fun ejemplosRangos() {
    // Rango inclusivo (1 al 10)
    val rango1 = 1..10
    println(rango1)

    // Rango exclusivo (1 al 9)
    val rango2 = 1 until 10
    println(rango2)

    // Rango descendente
    val rango3 = 10 downTo 1
    println(rango3)


    // Rango con step
    val rango4 = 1..10 step 2  // 1, 3, 5, 7, 9
    println(rango4)

    // Verificar pertenencia
    val numero = 5
    if (numero in 1..10) {
        println("$numero está en el rango")
    }

    // Iterar sobre rangos
    for (i in 1..5) {
        print("$i ")  // 1 2 3 4 5
    }

    println()

    for (i in 10 downTo 1 step 2) {
        print("$i ")  // 10 8 6 4 2
    }

    println()

    // Rangos de caracteres
    for (c in 'a'..'e') {
        print("$c ")  // a b c d e
    }
}

println("\n9. Rangos")
ejemplosRangos()