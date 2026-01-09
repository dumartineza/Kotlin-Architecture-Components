package com.xcaret.android_kotlin_module.playground/* ============================================================================
   1.4 PROPIEDADES (PROPERTIES)
   -----------------------------------------------------------------------------
   DEFINICIÓN:
   - Campos de clase con getters y setters automáticos
   - Pueden tener lógica personalizada en get() y set()

   USO:
   - val: propiedad de solo lectura (solo getter)
   - var: propiedad mutable (getter y setter)
   - Backing fields con 'field' variable interna en el get/set en getters/setters personalizados

   NOTAS IMPORTANTES:
   - El acceso siempre usa sintaxis de propiedad, no métodos get/set
   - 'field' es la referencia al valor almacenado
   - Propiedades lateinit para inicialización tardía
   ============================================================================ */

class Rectangulo(var ancho: Double, var alto: Double) {
    // Propiedad calculada (solo getter)
    val area: Double
        get() = ancho * alto

    // Propiedad con getter y setter personalizado
    var perimetro: Double
        get() = 2 * (ancho + alto)
        set(value) {
            // Ajustar proporcionalmente
            val factor = value / perimetro
            ancho *= factor
            alto *= factor
        }

    // Propiedad con backing field
    var nombre: String = ""
        get() = field.uppercase()
        set(value) {
            field = value.trim()
        }
}

// Lateinit para inicialización tardía
class Configuracion {
    lateinit var baseDatos: String

    fun inicializar() {
        baseDatos = "PostgreSQL"
    }

    fun estaInicializado(): Boolean {
        return ::baseDatos.isInitialized
    }
}

println("\n4. Propiedades")
val rect = Rectangulo(5.0, 3.0)
println("Área: ${rect.area}")