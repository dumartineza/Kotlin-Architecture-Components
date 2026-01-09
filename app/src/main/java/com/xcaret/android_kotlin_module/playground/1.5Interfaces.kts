package com.xcaret.android_kotlin_module.playground/* ============================================================================
   1.5 INTERFACES
   -----------------------------------------------------------------------------
   DEFINICIÓN:
   - Contratos que definen métodos y propiedades que las clases deben implementar
   - Pueden tener implementaciones por defecto

   USO:
   - 'interface' para declarar
   - Una clase puede implementar múltiples interfaces
   - Soportan propiedades abstractas

   NOTAS IMPORTANTES:
   - Los métodos son abstractos por defecto
   - Pueden tener métodos con implementación
   ============================================================================ */

interface Volador {
    // Propiedad abstracta
    val alturaMaxima: Int

    // Método abstracto
    fun volar()

    // Método con implementación por defecto
    fun aterrizar() {
        println("Aterrizando suavemente...")
    }
}

interface Nadador {
    fun nadar()
}

class Pato(override val alturaMaxima: Int = 100) : Volador, Nadador {
    override fun volar() {
        println("El pato vuela hasta $alturaMaxima metros")
    }

    override fun nadar() {
        println("El pato está nadando")
    }
}

class Avion(override val alturaMaxima: Int = 10000) : Volador {
    override fun volar() {
        println("El avión vuela a $alturaMaxima metros")
    }
}

// Interfaces con Propiedades
interface Vehiculo {
    val marca: String
    val modelo: String
    val año: Int

    // Propiedad calculada
    val antiguedad: Int
        get() = 2025 - año

    fun arrancar()
    fun detener()
}

class Automovil(
    override val marca: String,
    override val modelo: String,
    override val año: Int
) : Vehiculo {
    override fun arrancar() {
        println("$marca $modelo arrancando el motor...")
    }

    override fun detener() {
        println("$marca $modelo deteniendo...")
    }
}

//Ejecución
val pato = Pato()
val avion = Avion()
val automovil = Automovil("Subaru", "WRX", 2023)

pato.volar()
pato.nadar()
avion.volar()
avion.aterrizar()
automovil.antiguedad
automovil.arrancar()



