package com.xcaret.android_kotlin_module.playground/* ============================================================================
   1.8 ENUMS
   -----------------------------------------------------------------------------
   DEFINICIÓN:
   - Tipo especial de clase para representar un conjunto fijo de constantes
   - Cada constante es una instancia única del enum

   USO:
   - 'enum class' para declarar
   - Pueden tener propiedades, métodos y constructor
   - Útiles para estados, tipos, categorías

   NOTAS IMPORTANTES:
   - Los enums pueden implementar interfaces
   - Tienen métodos automáticos: values(), valueOf()
   - Cada constante puede tener comportamiento personalizado
   ============================================================================ */

enum class DiaSemana {
    LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO
}

// Enum con propiedades
enum class Color(val rgb: Int) {
    ROJO(0xFF0000),
    VERDE(0x00FF00),
    AZUL(0x0000FF),
    AMARILLO(0xFFFF00)
}

// Enum con métodos y comportamiento personalizado
enum class TipoOperacion(val simbolo: String) {
    SUMA("+") {
        override fun aplicar(a: Int, b: Int) = a + b
    },
    RESTA("-") {
        override fun aplicar(a: Int, b: Int) = a - b
    },
    MULTIPLICACION("*") {
        override fun aplicar(a: Int, b: Int) = a * b
    },
    DIVISION("/") {
        override fun aplicar(a: Int, b: Int) = a / b
    };

    abstract fun aplicar(a: Int, b: Int): Int
}

fun ejemploEnums() {
    val dia = DiaSemana.LUNES

    // Iterar sobre todos los valores
    for (d in DiaSemana.values()) {
        println(d)
    }

    // Obtener enum desde string
    val martes = DiaSemana.valueOf("MARTES")
    println(martes)

    // Usar enum con propiedades
    val color = Color.ROJO
    println("RGB: ${color.rgb}")

    // Enum con comportamiento
    val resultado = TipoOperacion.SUMA.aplicar(5, 3)
    println(resultado)

}

println("\n8. Enums")
ejemploEnums()