package com.xcaret.android_kotlin_module.playground/* ============================================================================
   1.7 COLECCIONES
   -----------------------------------------------------------------------------
   DEFINICIÓN:
   - Estructuras para almacenar grupos de elementos
   - Inmutables (List, Set, Map) y mutables (MutableList, MutableSet, MutableMap)

   USO:
   - List: colección ordenada (permite duplicados)
   - Set: colección sin duplicados
   - Map: pares clave-valor

   NOTAS IMPORTANTES:
   - Las colecciones inmutables son de solo lectura.
   - Operaciones funcionales: map, filter, reduce, etc.
   - Usen colecciones inmutables cuando sea posible
   ============================================================================ */

fun ejemplosColecciones() {
    // Listas inmutables
    val numeros = listOf(1, 2, 3, 4, 5)
    val primero = numeros.first()
    val ultimo = numeros.last()

    // Listas mutables
    val nombresMutable = mutableListOf("Ana", "Juan", "Pedro")
    nombresMutable.add("María")
    nombresMutable.remove("Juan")

    // Sets (sin duplicados)
    val conjunto = setOf(1, 2, 3, 2, 1)  // [1, 2, 3]
    val conjuntoMutable = mutableSetOf<String>()

    // Maps (clave-valor)
    val edades = mapOf(
        "Ana" to 25,
        "Juan" to 30,
        "Pedro" to 28
    )
    val edadAna = edades["Ana"]

    val edadesMutable = mutableMapOf<String, Int>()
    edadesMutable["María"] = 22

    // Operaciones funcionales
    val pares = numeros.filter { it % 2 == 0 }
    val duplicados = numeros.map { it * 2 }
    val suma = numeros.reduce { acc, n -> acc + n }
    val cualquieraPar = numeros.any { it % 2 == 0 }
    val todosPares = numeros.all { it % 2 == 0 }
}

println("\n7. Colecciones")
ejemplosColecciones()