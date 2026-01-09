package com.xcaret.android_kotlin_module.playground/* ============================================================================
   SISTEMA DE GESTIÓN DE BIBLIOTECA
   ============================================================================*/

// Enum para estados del libro
enum class EstadoLibro {
    DISPONIBLE,
    PRESTADO
}

// Enum para categorías con días de préstamo
enum class Categoria(val diasPrestamo: Int) {
    FICCION(14),
    PROGRAMACION(30),
    CIENCIA(21)
}

// Data class para Libro
data class Libro(
    val id: Int,
    val titulo: String,
    val autor: String,
    val categoria: Categoria,
    var estado: EstadoLibro = EstadoLibro.DISPONIBLE
)

// Data class para Usuario
data class Usuario(
    val id: Int,
    val nombre: String,
    val email: String,
    val librosPrestados: MutableList<Int> = mutableListOf()
) {
    fun puedePrestar(): Boolean = librosPrestados.size < 3
}

// Data class para Préstamo
data class Prestamo(
    val libroId: Int,
    val usuarioId: Int,
    var activo: Boolean = true
)

// ============================================================================
// CLASE BIBLIOTECA
// ============================================================================

class Biblioteca {
    private val libros = mutableListOf<Libro>()
    private val usuarios = mutableListOf<Usuario>()
    private val prestamos = mutableListOf<Prestamo>()

    // Agregar libro
    fun agregarLibro(libro: Libro) {
        libros.add(libro)
        println("Libro agregado: ${libro.titulo}")
    }

    // Agregar usuario
    fun agregarUsuario(usuario: Usuario) {
        usuarios.add(usuario)
        println("Usuario agregado: ${usuario.nombre}")
    }

    // Prestar libro
    fun prestarLibro(libroId: Int, usuarioId: Int): String {
        val libro = libros.find { it.id == libroId }
            ?: return "Libro no encontrado"

        val usuario = usuarios.find { it.id == usuarioId }
            ?: return "Usuario no encontrado"

        return when {
            libro.estado != EstadoLibro.DISPONIBLE ->
                "El libro no está disponible"
            !usuario.puedePrestar() ->
                "Usuario alcanzó el límite de préstamos"
            else -> {
                libro.estado = EstadoLibro.PRESTADO
                usuario.librosPrestados.add(libroId)
                prestamos.add(Prestamo(libroId, usuarioId))
                "Préstamo exitoso: '${libro.titulo}' para ${usuario.nombre}"
            }
        }
    }

    // Devolver libro
    fun devolverLibro(libroId: Int, usuarioId: Int): String {
        val libro = libros.find { it.id == libroId }
            ?: return "Libro no encontrado"

        val usuario = usuarios.find { it.id == usuarioId }
            ?: return "Usuario no encontrado"

        val prestamo = prestamos.find {
            it.libroId == libroId && it.usuarioId == usuarioId && it.activo
        } ?: return "Préstamo no encontrado"

        libro.estado = EstadoLibro.DISPONIBLE
        usuario.librosPrestados.remove(libroId)
        prestamo.activo = false

        return "Libro devuelto: '${libro.titulo}'"
    }

    // Buscar libros por categoría
    fun buscarPorCategoria(categoria: Categoria): List<Libro> {
        return libros.filter { it.categoria == categoria }
    }

    // Mostrar estadísticas
    fun mostrarEstadisticas() {
        println("\n=== ESTADÍSTICAS ===")
        println("Total de libros: ${libros.size}")
        println("Libros disponibles: ${libros.count { it.estado == EstadoLibro.DISPONIBLE }}")
        println("Libros prestados: ${libros.count { it.estado == EstadoLibro.PRESTADO }}")
        println("Total de usuarios: ${usuarios.size}")
        println("Préstamos activos: ${prestamos.count { it.activo }}")
    }
}

// ============================================================================
// EXTENSION FUNCTIONS
// ============================================================================

// Extension para validar email
fun String.esEmailValido(): Boolean {
    return this.contains("@") && this.contains(".")
}

// Extension para filtrar libros disponibles
fun List<Libro>.disponibles(): List<Libro> {
    return this.filter { it.estado == EstadoLibro.DISPONIBLE }
}

// ============================================================================
// MAIN - CASOS DE USO
// ============================================================================

fun main() {
    println("=== SISTEMA DE GESTIÓN DE BIBLIOTECA ===\n")

    val biblioteca = Biblioteca()

    // Agregar libros
    println("--- Agregando Libros ---")
    biblioteca.agregarLibro(Libro(1, "Clean Code", "Robert Martin", Categoria.PROGRAMACION))
    biblioteca.agregarLibro(Libro(2, "Kotlin in Action", "Dmitry Jemerov", Categoria.PROGRAMACION))
    biblioteca.agregarLibro(Libro(3, "El Principito", "Saint-Exupéry", Categoria.FICCION))
    biblioteca.agregarLibro(Libro(4, "Cosmos", "Carl Sagan", Categoria.CIENCIA))
    biblioteca.agregarLibro(Libro(5, "1984", "George Orwell", Categoria.FICCION))

    // Agregar usuarios
    println("\n--- Agregando Usuarios ---")
    biblioteca.agregarUsuario(Usuario(1, "Ana García", "ana@email.com"))
    biblioteca.agregarUsuario(Usuario(2, "Juan Pérez", "juan@email.com"))
    biblioteca.agregarUsuario(Usuario(3, "María López", "maria@email.com"))

    // Realizar préstamos
    println("\n--- Realizando Préstamos ---")
    println(biblioteca.prestarLibro(1, 1))  // Clean Code para Ana
    println(biblioteca.prestarLibro(2, 1))  // Kotlin para Ana
    println(biblioteca.prestarLibro(3, 2))  // Principito para Juan

    // Intentar préstamo inválido
    println("\n--- Préstamos Inválidos ---")
    println(biblioteca.prestarLibro(1, 2))  // Clean Code ya prestado

    // Devolver libro
    println("\n--- Devolviendo Libros ---")
    println(biblioteca.devolverLibro(1, 1))  // Ana devuelve Clean Code

    // Buscar por categoría
    println("\n--- Libros de Programación ---")
    val librosProg = biblioteca.buscarPorCategoria(Categoria.PROGRAMACION)
    librosProg.forEach { libro ->
        println("  • ${libro.titulo} [${libro.estado}]")
    }

    // Probar extension function
    println("\n--- Validando Emails ---")
    val emails = listOf("diego@gmail.com", "invalido", "test@gmail.com")
    emails.forEach { email ->
        val valido = if (email.esEmailValido()) "Válido" else "Inválido"
        println("  $email -> $valido")
    }

    // Mostrar estadísticas
    biblioteca.mostrarEstadisticas()
}

main ()
