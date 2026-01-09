package com.xcaret.android_kotlin_module.playground/* ============================================================================
   1.16 OBJECTS
   -----------------------------------------------------------------------------
   DEFINICIÓN:
   - Object declarations: singletons (instancia única)
   - Object expressions: objetos anónimos (reemplazan clases anónimas de Java)
   - Companion objects: miembros estáticos de una clase

   USO:
   - 'object' para singleton
   - 'object : Interface' para objetos anónimos
   - 'companion object' para métodos/propiedades de clase

   NOTAS IMPORTANTES:
   - Object declarations son thread-safe por defecto
   - Companion objects pueden implementar interfaces
   - Los objects se inicializan lazy (cuando se acceden)
   ============================================================================ */

// Object declaration (Singleton)
object DatabaseConnection {
    var url: String = "jdbc:postgresql://localhost/db"
    var connected: Boolean = false

    fun connect() {
        connected = true
        println("Conectado a $url")
    }

    fun disconnect() {
        connected = false
        println("Desconectado")
    }
}

// Clase con companion object (miembros estáticos)
class Calculadora {
    companion object {
        const val PI = 3.14159

        fun areaCirculo(radio: Double): Double {
            return PI * radio * radio
        }

        // Factory method
        fun crear(): Calculadora {
            return Calculadora()
        }
    }

    fun sumar(a: Int, b: Int) = a + b
}

// Companion object con nombre e interface
interface Factory<T> {
    fun create(): T
}

class MiClase {
    companion object MiFactory : Factory<MiClase> {
        override fun create(): MiClase {
            return MiClase()
        }
    }
}

// Object expression (objeto anónimo)
fun ejemploObjectExpression() {
    // Implementar interfaz on-the-fly
    val clickListener = object : Runnable {
        override fun run() {
            println("Click detectado")
        }
    }

    // Objeto anónimo sin interfaz
    val empleadoTemporal = object {
        val nombre = "Temporal"
        val id = 999

        fun trabajar() {
            println("$nombre trabajando")
        }
    }

    empleadoTemporal.trabajar()
}

fun ejemplosObjects() {
    // Usar singleton
    DatabaseConnection.connect()
    println(DatabaseConnection.connected)

    // Usar companion object
    val area = Calculadora.areaCirculo(5.0)
    val calc = Calculadora.crear()

    // Acceder a PI
    println(Calculadora.PI)
}

println("\n16. Objects")
ejemplosObjects()