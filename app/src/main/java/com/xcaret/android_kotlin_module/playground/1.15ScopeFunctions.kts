package com.xcaret.android_kotlin_module.playground/* ============================================================================
   1.15 SCOPE FUNCTIONS
   -----------------------------------------------------------------------------
   DEFINICIÓN:
   - Funciones que ejecutan un bloque de código en el contexto de un objeto
   - Cinco funciones: let, run, with, apply, also
   
   USO:
   - let: transformar objeto, null safety
   - run: ejecutar lógica en contexto, calcular valor
   - with: agrupar llamadas a un objeto
   - apply: configurar objeto, retorna el objeto
   - also: efectos secundarios, retorna el objeto
   
   NOTAS IMPORTANTES:
   - let y also usan 'it' (parámetro lambda)
   - run, with, apply usan 'this' (receptor lambda)
   - apply y also retornan el objeto, let y run retornan resultado del bloque
   ============================================================================ */
// Clase básica con constructor primario
class Persona(val nombre: String, var edad: Int) {
    // Bloque de inicialización
    init {
        require(edad >= 0) { "La edad no puede ser negativa" }
    }

    // Método
    fun presentarse() {
        println("Soy $nombre y tengo $edad años")
    }
}

class Empleado(val nombre: String) {
    var salario: Double = 0.0
    var departamento: String = ""

    constructor(nombre: String, salario: Double) : this(nombre) {
        this.salario = salario
    }

    constructor(nombre: String, salario: Double, departamento: String) : this(nombre, salario) {
        this.departamento = departamento
    }
}

fun ejemplosScopeFunctions() {
    val persona = Persona("Ana", 25)

    // LET - transformación y null safety
    val nombreMayusculas = persona.let {
        it.nombre.uppercase()
    }

    val longitudNombre: Int? = persona.let { p ->
        p.nombre.length
    }

    // Null safety con let
    val nullable: String? = "Hola"
    nullable?.let {
        println("Longitud: ${it.length}")
    }

    // RUN - ejecutar bloque en contexto
    val mensaje = persona.run {
        "Me llamo $nombre y tengo $edad años"
    }

    val resultado = "Hola".run {
        uppercase().reversed()
    }

    // WITH - agrupar operaciones en objeto
    val descripcion = with(persona) {
        println("Nombre: $nombre")
        println("Edad: $edad")
        "Descripción completa"
    }

    // APPLY - configurar objeto (retorna el objeto)
    val empleado = Empleado("Juan").apply {
        salario = 50000.0
        departamento = "IT"
    }

    // ALSO - efectos secundarios (retorna el objeto)
    val numeros = mutableListOf(1, 2, 3).also {
        println("Lista inicial: $it")
    }.also {
        it.add(4)
    }
}

// Comparación práctica
fun ejemploComparacionScopes() {
    data class Config(var host: String = "", var puerto: Int = 0)

    // apply: configurar y retornar objeto
    val config1 = Config().apply {
        host = "localhost"
        puerto = 8080
    }

    // also: logging mientras se configura
    val config2 = Config().also {
        println("Creando configuración...")
    }.apply {
        host = "localhost"
        puerto = 8080
    }

    // let: transformar
    val descripcionConfig = config1.let {
        "${it.host}:${it.puerto}"
    }

    // run: calcular algo con el objeto
    val esLocal = config1.run {
        host == "localhost"
    }
}

println("\n15. Scope Functions")
ejemplosScopeFunctions()