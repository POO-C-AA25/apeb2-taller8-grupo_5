/**
 * Problema 3 - Juego de fútbol "Estadísticas"
Se desea realizar una aplicación que permita a un periodista deportivo 
* llevar las estadísticas de los jugadores de un equipo de fútbol para poder
* valorar su actuación en el partido.

Cada jugador se identifica por su nombre, número de dorsal y Rut

Los jugadores se dividen en tres categorías:

Atacantes
Defensores
Porteros
Para todos los jugadores se desea contabilizar el número de goles marcados, 
* además en el caso de los jugadores de campo se contabilizan los pases 
* realizados con éxito y el número de balones recuperados. En el caso de los 
* porteros se contabilizan las atajadas realizadas.

Valoración del jugador
Cálculo base para todos los jugadores:

valor_goles = goles * 30
Valor adicional según tipo de jugador:

Atacantes

valor += recuperaciones * 3
Defensores

valor += recuperaciones * 4
Porteros

valor += atajadas * 5
Note

Se debe aplicar polimorfismo mediante la aplicación de herencia, encapsulamiento
* de atributos y comportamientos comunes, y especializar comportamiento según
* el tipo de jugador.
 * @author Erick Malla , Johan Wang
 */

public class Problema_3_EstadisticasFutbol {
    public static void main(String[] args) {
        Atacante atacante1 = new Atacante("Juan Perez", 10, 5, 2, 3);
        Defensor defensor1 = new Defensor("Carlos Lopez", 5, 3, 4, 7);
        Portero portero1 = new Portero("Luis Garcia", 1, 2, 6);
        
        System.out.println(atacante1);
        System.out.println(defensor1);
        System.out.println(portero1);
    }
}

abstract class Jugador {
    String nombre;
    int dorsal;
    int goles;

    public Jugador(String nombre, int dorsal, int goles) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.goles = goles;
    }

    public abstract double calcularValor();

    @Override
    public String toString() {
        return "Jugador: " + nombre + " (Dorsal: " + dorsal + ")";
    }
}

class Atacante extends Jugador {
    int recuperaciones;
    int pases;

    public Atacante(String nombre, int dorsal, int goles, int recuperaciones, int pases) {
        super(nombre, dorsal, goles);
        this.recuperaciones = recuperaciones;
        this.pases = pases;
    }

    @Override
    public double calcularValor() {
        double valor = goles * 30; 
        valor += recuperaciones * 3; 
        return valor;
    }

    @Override
    public String toString() {
        return super.toString() + ", Goles: " + goles + ", Recuperaciones: " + recuperaciones + ", Pases: " + pases + ", Valor: " + calcularValor();
    }
}

class Defensor extends Jugador {
    int recuperaciones;
    int balonesRecuperados;

    public Defensor(String nombre, int dorsal, int goles, int recuperaciones, int balonesRecuperados) {
        super(nombre, dorsal, goles);
        this.recuperaciones = recuperaciones;
        this.balonesRecuperados = balonesRecuperados;
    }

    @Override
    public double calcularValor() {
        double valor = goles * 30;
        valor += recuperaciones * 4;
        return valor;
    }

    @Override
    public String toString() {
        return super.toString() + ", Goles: " + goles + ", Recuperaciones: " + recuperaciones + ", Balones recuperados: " + balonesRecuperados + ", Valor: " + calcularValor();
    }
}

class Portero extends Jugador {
    int atajadas;

    public Portero(String nombre, int dorsal, int goles, int atajadas) {
        super(nombre, dorsal, goles);
        this.atajadas = atajadas;
    }

    @Override
    public double calcularValor() {
        double valor = goles * 30;
        valor += atajadas * 5; 
        return valor;
    }

    @Override
    public String toString() {
        return super.toString() + ", Goles: " + goles + ", Atajadas: " + atajadas + ", Valor: " + calcularValor();
    }
}
/**
 * Jugador: Juan Perez (Dorsal: 10), Goles: 5, Recuperaciones: 2, Pases: 3, Valor: 156.0
Jugador: Carlos Lopez (Dorsal: 5), Goles: 3, Recuperaciones: 4, Balones recuperados: 7, Valor: 106.0
Jugador: Luis Garcia (Dorsal: 1), Goles: 2, Atajadas: 6, Valor: 90.0
debug-single:
BUILD SUCCESSFUL (total time: 3 seconds)
 */