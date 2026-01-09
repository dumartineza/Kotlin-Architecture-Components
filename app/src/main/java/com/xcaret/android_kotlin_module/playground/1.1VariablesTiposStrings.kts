package com.xcaret.android_kotlin_module.playground/* ============================================================================
    1.1 VARIABLES, TIPOS Y STRING
    -----------------------------------------------------------------------------
     DEFINICIÓN:
     - Variables inmutables (val): No pueden reasignarse después de la inicialización
     - Variables mutables (var): Pueden cambiar su valor
     - Kotlin tiene inferencia de tipos, pero también tipado explícito

     TIPOS BÁSICOS:
     - Int, Long, Double, Float, Boolean, Char, String
     - Todo en Kotlin es un objeto (no hay tipos primitivos)

     STRINGS:
     - Inmutables, soportan templates ($variable, ${expresión})
     - Raw strings con triple comillas (""")

    USO:
    - Preferir 'val' sobre 'var' para inmutabilidad
    - String templates evitan concatenación manual

    NOTAS IMPORTANTES:
    - val no significa que el objeto sea inmutable, solo la referencia
    - Los tipos se infieren automáticamente si se inicializan
    ============================================================================ */

fun variables_tipos_strings() {
    // Variables inmutables y mutables
    val nombre: String = "Juan"  // Inmutable
    var edad = 25  // Mutable, tipo inferido
    
    // Tipos básicos
    val entero: Int = 42
    val largo: Long = 100L
    val decimal: Double = 3.14
    val flotante: Float = 2.5f
    val booleano: Boolean = true
    val caracter: Char = 'A'
    
    // String templates
    println("Mi nombre es $nombre y tengo $edad años")
    println("El próximo año tendré ${edad + 1} años")
    
    // Raw strings (multilinea)
    val texto = """
        Primera línea
        Segunda línea
        Tercera línea
    """.trimIndent()
    
    // Operaciones con strings
    val saludo = "Hola" + " " + "Mundo"
    val longitud = nombre.length
    val mayusculas = nombre.uppercase()
}

println("1. Variables, Tipos y Strings")
variables_tipos_strings()