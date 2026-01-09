package com.xcaret.android_kotlin_module.playground/* ============================================================================
   SISTEMA DE GESTIÓN DE BIBLIOTECA
   ============================================================================ */

// Crear enum EstadoLibro
// Debe tener dos valores: DISPONIBLE, PRESTADO


// Crear enum Categoria con propiedades
// Debe tener: FICCION(14), PROGRAMACION(30), CIENCIA(21)
// La propiedad es: diasPrestamo: Int


// Crear data class Libro
// Propiedades: id: Int, titulo: String, autor: String,
//              categoria: Categoria, estado: EstadoLibro = EstadoLibro.DISPONIBLE


// Crear data class Usuario
// Propiedades: id: Int, nombre: String, email: String,
//              librosPrestados: MutableList<Int> = mutableListOf()
// Agregar método: fun puedePrestar(): Boolean (retorna true si tiene menos de 3 libros)


// Crear data class Prestamo
// Propiedades: libroId: Int, usuarioId: Int, activo: Boolean = true


// Clase Biblioteca
class Biblioteca {
    private val libros = mutableListOf<Libro>()
    private val usuarios = mutableListOf<Usuario>()
    private val prestamos = mutableListOf<Prestamo>()

    // Implementar agregarLibro(libro: Libro)
    fun agregarLibro(libro: Libro) {
        // Agregar libro a la lista
        // Imprimir: "Libro agregado: ${libro.titulo}"
    }

    // Implementar agregarUsuario(usuario: Usuario)
    fun agregarUsuario(usuario: Usuario) {
        // Agregar usuario a la lista
        // Imprimir: "Usuario agregado: ${usuario.nombre}"
    }

    // Implementar prestarLibro(libroId: Int, usuarioId: Int): String
    fun prestarLibro(libroId: Int, usuarioId: Int): String {
        // 1. Buscar el libro con find
        // 2. Buscar el usuario con find
        // 3. Validar que el libro esté DISPONIBLE
        // 4. Validar que el usuario puedePrestar()
        // 5. Cambiar estado del libro a PRESTADO
        // 6. Agregar libroId a usuario.librosPrestados
        // 7. Crear y agregar un Prestamo
        // 8. Retornar mensaje de éxito o error
    }

    // Implementar devolverLibro(libroId: Int, usuarioId: Int): String
    fun devolverLibro(libroId: Int, usuarioId: Int): String {
        // 1. Buscar el libro
        // 2. Buscar el usuario
        // 3. Buscar el préstamo activo
        // 4. Cambiar estado del libro a DISPONIBLE
        // 5. Remover libroId de usuario.librosPrestados
        // 6. Marcar prestamo.activo = false
        // 7. Retornar mensaje de éxito
    }

    // Implementar buscarPorCategoria(categoria: Categoria): List<Libro>
    fun buscarPorCategoria(categoria: Categoria): List<Libro> {
        // Usar filter para retornar libros de la categoría
        return emptyList()
    }

    // Implementar mostrarEstadisticas()
    fun mostrarEstadisticas() {
        // Imprimir:
        // - Total de libros
        // - Libros disponibles (usar count)
        // - Libros prestados (usar count)
        // - Total de usuarios
        // - Préstamos activos (usar count)
    }
}

// Extension function para String
fun String.esEmailValido(): Boolean {
    // Retornar true si contiene @ y .
    return false
}

// Extension function para List<Libro>
fun List<Libro>.disponibles(): List<Libro> {
    // Usar filter para retornar solo libros DISPONIBLES
    return emptyList()
}

// Implementar main()
fun main() {
    println("=== SISTEMA DE GESTIÓN DE BIBLIOTECA ===\n")

    val biblioteca = Biblioteca()

    // Agregar 5 libros

    // Agregar 3 usuarios

    // Realizar 3 préstamos

    // Intentar un préstamo inválido

    // Devolver 1 libro

    // Buscar libros por categoría

    // Probar extension function de email

    // Mostrar estadísticas
}