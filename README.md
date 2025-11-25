# Gestor de Contactos - CRUD MVC (Java Swing)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-GUI-blue?style=for-the-badge)
![Architecture](https://img.shields.io/badge/Pattern-MVC-green?style=for-the-badge)

Aplicación de escritorio robusta para la gestión de contactos telefónicos, desarrollada bajo el paradigma de **Programación Orientada a Objetos** y el patrón de arquitectura **Modelo-Vista-Controlador (MVC)**. Utiliza `RandomAccessFile` para la persistencia de datos eficiente sin bases de datos externas.

---

## 👥 Autores

Este proyecto ha sido diseñado y desarrollado por:

* **Thomas Molina Molina**
* **Mariana García Herrera**

**Universidad Nacional de Colombia** *Programación Orientada a Objetos - Actividad 5*

---

## 🚀 Características Principales

* **Arquitectura Limpia:** Separación estricta entre Lógica (Model), Interfaz (View) y Control (Controller).
* **CRUD Completo:** Funcionalidades de Crear, Leer, Actualizar y Borrar contactos.
* **Interfaz Gráfica (Swing):**
    * Uso de `JTable` para visualización estructurada.
    * Selección interactiva de filas para edición rápida.
* **Persistencia de Datos:** Manejo de archivos de texto (`friendsContact.txt`) con operaciones de bajo nivel optimizadas.
* **Modo Consola (Seed):** Comando oculto para generación masiva de datos (testing).

---

## 📂 Estructura del Proyecto

El código está organizado en el paquete `com.mycompany.tarea5`:

```text
com.mycompany.tarea5
│
├── Friend.java             # (Model) Entidad que representa un contacto (POJO).
├── FriendRepository.java   # (Model) Lógica de acceso a datos y manejo de archivos.
├── FriendView.java         # (View) Interfaz gráfica: Ventanas, Tabla, Botones.
├── FriendController.java   # (Controller) Coordina la vista y el modelo.
└── Tarea5.java             # (Main) Punto de entrada. Maneja modo GUI y Consola.
