package com.xcaret.android_kotlin_module.playground/* ============================================================================
   1.17 DELEGACIÓN DE PROPIEDADES
   -----------------------------------------------------------------------------
   DEFINICIÓN:
   - Delegar el comportamiento get/set de una propiedad a otro objeto
   - Patrón de diseño que permite reutilizar lógica de propiedades

   USO:
   - 'by' para delegar
   - Delegados estándar: lazy, observable, vetoable, map
   - Crear delegados personalizados con getValue/setValue

   NOTAS IMPORTANTES:
   - lazy: inicialización perezosa (solo cuando se accede)
   - observable: notificar cambios
   - vetoable: validar antes de cambiar
   - Los delegados reducen boilerplate
   ============================================================================ */

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

// Lazy delegation (inicialización perezosa)
class RecursosPesados {
    val datos: String by lazy {
        println("Cargando datos pesados...")
        "Datos cargados"
    }

    val datosThreadSafe: String by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        "Datos thread-safe"
    }
}

// Observable delegation (observar cambios)
class UsuarioObservable {
    var nombre: String by Delegates.observable("Sin nombre") { prop, old, new ->
        println("${prop.name} cambió de '$old' a '$new'")
    }
}

// Vetoable delegation (validar cambios)
class Producto {
    var precio: Double by Delegates.vetoable(0.0) { prop, old, new ->
        new >= 0  // Solo permite valores no negativos
    }
}

// Map delegation (propiedades desde Map)
class UsuarioMap(map: Map<String, Any?>) {
    val nombre: String by map
    val edad: Int by map
    val email: String by map
}

// Delegado personalizado
class DelegadoMayusculas {
    private var valor: String = ""

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return valor.uppercase()
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        this.valor = value
    }
}

class Documento {
    var titulo: String by DelegadoMayusculas()
}

fun ejemplosDelegacion() {
    // Lazy
    val recursos = RecursosPesados()
    println("Antes de acceder")
    println(recursos.datos)  // Aquí se carga
    println(recursos.datos)  // Ya está cargado

    // Observable
    val usuario = UsuarioObservable()
    usuario.nombre = "Ana"  // Imprime: nombre cambió de 'Sin nombre' a 'Ana'
    usuario.nombre = "Juan"  // Imprime: nombre cambió de 'Ana' a 'Juan'

    // Vetoable
    val producto = Producto()
    producto.precio = 100.0  // OK
    producto.precio = -50.0  // Rechazado, precio sigue siendo 100.0
    println(producto.precio)  // 100.0

    // Map delegation
    val mapa = mapOf(
        "nombre" to "Pedro",
        "edad" to 30,
        "email" to "pedro@email.com"
    )
    val usuarioDesdeMap = UsuarioMap(mapa)
    println("${usuarioDesdeMap.nombre}, ${usuarioDesdeMap.edad}")

    // Delegado personalizado
    val doc = Documento()
    doc.titulo = "mi documento"
    println(doc.titulo)  // MI DOCUMENTO
}

println("\n17. Delegación de Propiedades")
ejemplosDelegacion()