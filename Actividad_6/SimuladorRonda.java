package Actividad_6; // Declaramos el paquete para organizar nuestro código

import java.util.Random; // Importamos Random para generar los puntos aleatorios

// 1. CLASE JUGADOR: Representa los datos de la persona que juega
class Jugador {
    String nombre; // Guarda el nombre del jugador
    int puntos; // Guarda los puntos acumulados en el juego

    // Constructor que se llama al crear un nuevo jugador
    public Jugador(String nombre) {
        this.nombre = nombre; // Asignamos el nombre que llega por parámetro
        this.puntos = 0; // Todos empiezan el juego con cero puntos
    }
}

// 2. CLASE NODO: Es la "caja" que guarda al jugador y lo enlaza con el siguiente
class NodoCircular {
    Jugador jugador; // El objeto jugador que está dentro del nodo
    NodoCircular siguiente; // El "cable" que apunta al próximo jugador en la ronda

    // Constructor del nodo
    public NodoCircular(Jugador jugador) {
        this.jugador = jugador; // Metemos al jugador en el nodo
        this.siguiente = this; // Al principio, como está solo, se apunta a sí mismo
    }
}

// 3. CLASE LISTA CIRCULAR: La estructura que maneja la ronda completa
class ListaCircular {
    NodoCircular actual; // Es nuestro puntero principal, nos dice de quién es el turno
    int cantidadJugadores; // Contador para saber cuántos quedan vivos en el juego

    // Constructor de la lista
    public ListaCircular() {
        this.actual = null; // Al iniciar la mesa, no hay nadie (lista vacía)
        this.cantidadJugadores = 0; // Por lo tanto, hay 0 jugadores
    }

    // Método para agregar jugadores al final del anillo
    public void insertarJugador(Jugador jugador) {
        NodoCircular nuevo = new NodoCircular(jugador); // Creamos el nodo con el jugador

        if (actual == null) {
            // Caso base: si la lista está vacía, el nuevo nodo es el actual
            actual = nuevo;
        } else {
            // Si ya hay gente, buscamos al último de la fila para enlazarlo
            NodoCircular temporal = actual; // Usamos un temporal para no perder el 'actual'

            // Recorremos hasta llegar al nodo que apunta al 'actual' (ese es el último)
            while (temporal.siguiente != actual) {
                temporal = temporal.siguiente; // Avanzamos al siguiente nodo
            }

            // Conectamos el último nodo con el nuevo que llegó
            temporal.siguiente = nuevo;
            // Y el nuevo apunta al 'actual' para cerrar el círculo perfectamente
            nuevo.siguiente = actual;
        }
        cantidadJugadores++; // Sumamos uno al contador de jugadores
    }

    // Método para pasar el turno al siguiente jugador sin eliminar a nadie
    public void avanzarTurno() {
        if (actual != null) {
            actual = actual.siguiente; // Movemos el puntero al próximo nodo del anillo
        }
    }

    // Método clave: Saca al jugador que tiene el turno actual
    public void eliminarJugadorActual() {
        // Caso base: Si solo queda 1 jugador, vaciamos la lista
        if (cantidadJugadores == 1) {
            actual = null; // Destruimos el anillo porque el juego terminó
        } else {
            // Para eliminar el 'actual', necesito saber quién está justo detrás de él
            NodoCircular anterior = actual;

            // Damos la vuelta entera hasta llegar al nodo que está detrás del 'actual'
            while (anterior.siguiente != actual) {
                anterior = anterior.siguiente; // Vamos avanzando
            }

            // Puentear: El de atrás se salta al 'actual' y apunta directo al siguiente
            anterior.siguiente = actual.siguiente;

            // Como el 'actual' ya no está, el turno pasa automáticamente al siguiente
            actual = actual.siguiente;
        }
        cantidadJugadores--; // Restamos un jugador del contador
    }

    // Método extra para poder ver en la consola cómo está la mesa de juego
    public void mostrarMesa() {
        if (actual == null) return; // Si no hay nadie, no imprime nada

        NodoCircular temp = actual; // Empezamos a imprimir desde el turno actual
        System.out.print("Ronda: ");

        // Usamos un do-while para dar la vuelta imprimiendo hasta volver al inicio
        do {
            System.out.print("[" + temp.jugador.nombre + ": " + temp.jugador.puntos + " pts]-> ");
            temp = temp.siguiente; // Pasamos al siguiente para imprimirlo
        } while (temp != actual); // Se detiene cuando da la vuelta completa

        System.out.println("(vuelve a " + actual.jugador.nombre + ")"); // Muestra que es circular
    }
}

// 4. CLASE SIMULADOR: El programa principal que corre el juego
public class SimuladorRonda {
    public static void main(String[] args) {
        ListaCircular juego = new ListaCircular(); // Creamos la lista (la mesa)
        Random random = new Random(); // Objeto para generar números al azar
        int LIMITE_PUNTOS = 21; // Puntos máximos antes de perder

        // Agregamos a los jugadores conectando la lista circular
        juego.insertarJugador(new Jugador("Andres"));
        juego.insertarJugador(new Jugador("Camila"));
        juego.insertarJugador(new Jugador("Felipe"));
        juego.insertarJugador(new Jugador("Laura"));

        System.out.println("=== INICIA EL JUEGO DE CARTAS LA RONDA ===");

        // El bucle sigue dando vueltas infinito hasta que solo quede 1 jugador
        while (juego.cantidadJugadores > 1) {
            juego.mostrarMesa(); // Imprimimos cómo están los jugadores

            // Generamos puntos al azar entre 1 y 10 para este turno
            int puntosTurno = random.nextInt(10) + 1;

            // Le sumamos los puntos al jugador que tiene el turno (el 'actual')
            juego.actual.jugador.puntos += puntosTurno;

            System.out.println("-> Turno de " + juego.actual.jugador.nombre + ". Saca carta y gana " + puntosTurno + " puntos.");

            // Evaluamos la condición aleatoria: ¿Se pasó de los puntos?
            if (juego.actual.jugador.puntos >= LIMITE_PUNTOS) {
                System.out.println("   ¡" + juego.actual.jugador.nombre + " supero el limite (" + juego.actual.jugador.puntos + ") y es ELIMINADO!");
                // Llamamos a la lista para reajustar los nodos y borrarlo
                juego.eliminarJugadorActual();
            } else {
                // Si no se pasó, simplemente avanzamos el puntero al próximo turno
                juego.avanzarTurno();
            }

            System.out.println("--------------------------------------------------");
        }

        // Cuando el bucle termina, solo queda 1 jugador en el nodo 'actual'
        if (juego.actual != null) {
            System.out.println("\n=== FIN DEL JUEGO ===");
            System.out.println("¡El gran ganador es " + juego.actual.jugador.nombre + " con " + juego.actual.jugador.puntos + " puntos!");
        }
    }
}