package com.xcaret.android_kotlin_module.playground/* ============================================================================
   1.3 CLASES
   -----------------------------------------------------------------------------
   DEFINICIÓN:
   - Plantillas para crear objetos con propiedades y comportamientos
   - Constructor primario en la declaración de la clase
   - Pueden tener constructores secundarios

   USO:
   - 'class' define una clase
   - 'init' para bloques de inicialización
   - Modificadores: open, abstract, final

   NOTAS IMPORTANTES:
   - Las clases son 'final' por defecto (no heredables)
   - Usar 'open' para permitir herencia
   - No necesitan la palabra 'new' para instanciar
   ============================================================================ */


// Clase simple
class Persona {
    // Bloque de inicialización
    init {
        println("Inicializando cuenta")
    }

    var nombre: String = ""
    var edad: Int = 0

    // Método
    fun presentarse() {
        println("Hola, soy $nombre y tengo $edad años")
    }
}

// Uso
val persona = Persona()
persona.nombre = "Mau"
persona.edad = 25
persona.presentarse()

// Clase abierta (open) puede ser heredada
open class Animal(val nombre: String) {
    open fun hacerSonido() {
        println("El animal hace un sonido")
    }
}

class Perro(nombre: String, val raza: String) : Animal(nombre) {
    override fun hacerSonido() {
        println("$nombre dice: ¡Guau guau!")
    }

    fun jugar() {
        println("$nombre está jugando")
    }
}

class Gato(nombre: String) : Animal(nombre) {
    override fun hacerSonido() {
        println("$nombre dice: ¡Miau!")
    }
}

// Uso
val animal = Perro("Coraje","Beagle")
println(animal.hacerSonido())
println(animal.jugar())