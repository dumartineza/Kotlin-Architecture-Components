package com.example.android_kotlin_module

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/*
====================================
PRUEBAS INSTRUMENTADAS - ANDROID REAL
====================================
EJECUCIÓN:
- Se ejecutan en emulador o dispositivo físico
- Requieren Android OS real
USO PRINCIPAL:
- UI tests
- Navegación
- Integración entre capas
- Room real
- Flujos completos de usuario
CAPACIDADES:
- Context real
- Activities / Fragments reales
- Views, Resources, Themes
- Base de datos real
DESVENTAJAS:
- Más lentas
- Requieren setup de emulador
HERRAMIENTAS:
- AndroidJUnit4
- Espresso
- UI Automator
*/

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.android_kotlin_module", appContext.packageName)
    }
}