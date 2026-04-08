# Simulador de Ronda - Actividad 6

## Descripción

Este proyecto implementa un simulador del juego de cartas "La Ronda" utilizando estructuras de datos en Java. El programa utiliza una **lista circular enlazada** para gestionar los turnos de los jugadores de manera eficiente.

### Reglas del Juego
- Los jugadores comienzan con 0 puntos
- En cada turno, un jugador recibe puntos aleatorios (1-10)
- Si un jugador alcanza 21 puntos o más, queda eliminado
- El último jugador en pie gana

## Estructura del Código

### Clases Principales

#### `Jugador`
- Representa a un participante del juego
- Atributos: `nombre` (String), `puntos` (int)

#### `NodoCircular`
- Nodo de la lista circular
- Contiene un `Jugador` y referencia al siguiente nodo

#### `ListaCircular`
- Gestiona la ronda completa
- Métodos principales:
  - `insertarJugador(Jugador)`: Agrega un nuevo jugador
  - `avanzarTurno()`: Pasa al siguiente jugador
  - `eliminarJugadorActual()`: Elimina al jugador actual
  - `mostrarMesa()`: Muestra el estado actual de la ronda

#### `SimuladorRonda`
- Clase principal con el método `main`
- Ejecuta la simulación completa del juego

## Requisitos

- Java Development Kit (JDK) 8 o superior
- Entorno de desarrollo como VS Code con extensión de Java

## Compilación y Ejecución

### Desde la línea de comandos:

1. **Compilar:**
   ```bash
   javac Actividad_6/SimuladorRonda.java
   ```

2. **Ejecutar:**
   ```bash
   java Actividad_6.SimuladorRonda
   ```

### Desde VS Code:

1. Abrir el proyecto en VS Code
2. Ejecutar el archivo `Actividad_6/SimuladorRonda.java` usando el comando "Run Java"

## Ejemplo de Salida

```
=== INICIA EL JUEGO DE CARTAS LA RONDA ===
Ronda: [Andres: 0 pts]-> [Camila: 0 pts]-> [Felipe: 0 pts]-> [Laura: 0 pts]-> (vuelve a Andres)
-> Turno de Andres. Saca carta y gana 6 puntos.
--------------------------------------------------
...
=== FIN DEL JUEGO ===
¡El gran ganador es Camila con 16 puntos!
```

## Estructuras de Datos Utilizadas

- **Lista Circular Enlazada**: Permite navegación eficiente en rondas
- **Nodos**: Cada nodo contiene un jugador y referencia al siguiente
- **Puntero Actual**: Indica de quién es el turno actual

## Características Técnicas

- **Lenguaje**: Java
- **Paradigma**: Programación Orientada a Objetos
- **Estructuras**: Lista circular, nodos enlazados
- **Aleatoriedad**: Generación de puntos usando `Random`

## Autor

Desarrollado como parte del curso de Estructuras de Datos.

## Licencia

Este proyecto es para fines educativos.