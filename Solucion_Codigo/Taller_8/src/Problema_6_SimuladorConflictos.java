/**
 * Problema 6 - Simulador de conflictos bélicos mundiales 2025
La ONU le solicita desarrollar un simulador de conflictos bélicos mundiales en 
* el lenguaje de alto nivel orientado a objetos Java, considerando sus cuatro 
* pilares fundamentales: abstracción, encapsulamiento, herencia y polimorfismo, 
* cumpliendo con los siguientes lineamientos:

Requisitos Funcionales
De manera general, cada nación debe ser representada con la siguiente 
* información: Nombre de la nación, Número de habitantes de la nación, Cantidad 
* de recursos económicos disponibles, Nivel de poder militar (valor entre 1 y
* 100),su estado de conflicto que indica si la nación está actualmente en 
* conflicto o no, y cualquier otra información que usted considere necesaria.
* No olvide implementar los métodos y/o constructores básicos para procesar 
* esta información dados todos los requerimientos.
A su vez se requiere la información de las naciones desarrolladas con alta 
* tecnología militar, como: Si la nación dispone de tecnología avanzada. Para 
* estas naciones avanzadas, implementar el cálculo del impacto, el cual 
* considera un bono de tecnología para el incremento de su poder militar 
* (no olvide que para este último la restricción es de 1-100, y en el caso de 
* sobre pasar, asigne directamente 100).
De igual forma se necesita conocer de las naciones en vías de desarrollo su 
* nivel de recursos limitados (recursos económicos y poder militar por cada 
* N habitantes), así como la implementación del cálculo del impacto, el cual 
* reduce el impacto en el conflicto debido a sus recursos limitados. Queda a 
* su criterio matemático y/o estadístico el planteamiento del modelo matemático 
* (con las variables/parámetros que tenga a bien) para calcular este factor de 
* impacto.
Para las naciones desarrolladas o en vías de desarrollo, considere sus naciones 
* aliadas, lo cual es decisivo para incrementar o decrementar su nivel de 
* impacto directamente a su poder militar, pero solo si tiene aliados
* disponibles.
El programa debe permitir declarar conflictos entre dos naciones seleccionadas 
* con un proceso aleatorio/randomico.
Calcular las consecuencias del conflicto utilizando polimorfismo y la 
* implementación de cálculo de impacto.
Consecuencias del conflicto:
Reducción del 5% de población por cada diferencia en los niveles de poder 
* militar.
Reducción del 10% de recursos de la nación derrotada.
Si las naciones tienen el mismo nivel de poder militar, ambas pierden el 
* 5% de recursos.
Al finalizar el programa, debe mostrar un reporte con el estado actual de 
* cada nación (población, recursos y estado de conflicto, etc), así como el 
* total de conflictos que se simularon entre N naciones.
Note

Reporte Final

Al finalizar la simulación, debe generarse un reporte general que contenga:
Estado actual de cada nación (población, recursos, estado de conflicto, etc.)
Total de conflictos simulados entre N naciones.
* @author Erick Malla , Johan Wang
 */


import java.util.ArrayList;
import java.util.Random;

public class Problema_6_SimuladorConflictos {
    public static void main(String[] args) {
        Nación desarrollada1 = new NaciónDesarrollada("USA", 330000000, 5000000000.0, 90, false, true, 20);
        Nación desarrollada2 = new NaciónDesarrollada("Rusia", 145000000, 4000000000.0, 85, true, true, 30);
        Nación enDesarrollo1 = new NaciónEnDesarrollo("Venezuela", 30000000, 1000000000.0, 40, false, 10);
        Nación enDesarrollo2 = new NaciónEnDesarrollo("Honduras", 10000000, 500000000.0, 35, false, 5);

        desarrollada1.agregarAliado(desarrollada2);
        enDesarrollo1.agregarAliado(enDesarrollo2);

        ArrayList<Nación> naciones = new ArrayList<>();
        naciones.add(desarrollada1);
        naciones.add(desarrollada2);
        naciones.add(enDesarrollo1);
        naciones.add(enDesarrollo2);

        Random rand = new Random();
        int conflictosSimulados = 0;
        for (int i = 0; i < 5; i++) {
            Nación nacion1 = naciones.get(rand.nextInt(naciones.size()));
            Nación nacion2 = naciones.get(rand.nextInt(naciones.size()));

            if (!nacion1.equals(nacion2)) {
                conflictosSimulados++;
                System.out.println("\nSimulando conflicto entre " + nacion1.getNombre() + " y " + nacion2.getNombre());
                nacion1.simularConflicto(nacion2);
            }
        }

        System.out.println("\nReporte Final:");
        for (Nación nacion : naciones) {
            System.out.println(nacion);
        }
        System.out.println("\nTotal de conflictos simulados: " + conflictosSimulados);
    }
}

abstract class Nación {
    private String nombre;
    private int poblacion;
    private double recursos;
    private int poderMilitar;
    private boolean enConflicto;
    private ArrayList<Nación> aliados;

    public Nación(String nombre, int poblacion, double recursos, int poderMilitar, boolean enConflicto) {
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.recursos = recursos;
        this.poderMilitar = poderMilitar;
        this.enConflicto = enConflicto;
        this.aliados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public double getRecursos() {
        return recursos;
    }

    public int getPoderMilitar() {
        return poderMilitar;
    }

    public boolean isEnConflicto() {
        return enConflicto;
    }

    public void setEnConflicto(boolean enConflicto) {
        this.enConflicto = enConflicto;
    }

    public void agregarAliado(Nación aliado) {
        this.aliados.add(aliado);
    }

    public ArrayList<Nación> getAliados() {
        return aliados;
    }

    public abstract void calcularImpacto(Nación enemigo);

    public void simularConflicto(Nación enemigo) {
        calcularImpacto(enemigo);

        int diferenciaPoder = Math.abs(this.poderMilitar - enemigo.poderMilitar);
        double porcentajePoblacion = diferenciaPoder * 0.05;
        this.poblacion -= (int) (this.poblacion * porcentajePoblacion);
        enemigo.poblacion -= (int) (enemigo.poblacion * porcentajePoblacion);

        // Si la población queda por debajo de 0, la asignamos a 0
        this.poblacion = Math.max(this.poblacion, 0);
        enemigo.poblacion = Math.max(enemigo.poblacion, 0);

        double porcentajeRecursos = 0;
        if (this.poderMilitar > enemigo.poderMilitar) {
            porcentajeRecursos = 0.1;
            this.recursos -= (this.recursos * porcentajeRecursos);
            enemigo.recursos -= (enemigo.recursos * 0.1);
        } else if (this.poderMilitar < enemigo.poderMilitar) {
            porcentajeRecursos = 0.1;
            this.recursos -= (this.recursos * porcentajeRecursos);
            enemigo.recursos -= (enemigo.recursos * 0.1);
        } else {
            this.recursos -= (this.recursos * 0.05);
            enemigo.recursos -= (enemigo.recursos * 0.05);
        }

        this.enConflicto = true;
        enemigo.enConflicto = true;

        System.out.println("Consecuencias del conflicto:");
        System.out.println(this.nombre + " pierde " + (int) (this.poblacion * porcentajePoblacion) + " habitantes y " + (int) (this.recursos * porcentajeRecursos) + " en recursos.");
        System.out.println(enemigo.nombre + " pierde " + (int) (enemigo.poblacion * porcentajePoblacion) + " habitantes y " + (int) (enemigo.recursos * porcentajeRecursos) + " en recursos.");
    }

    @Override
    public String toString() {
        return "Nacion: " + nombre + "\nPoblacion: " + poblacion + "\nRecursos: " + recursos + "\nPoder Militar: " + poderMilitar + "\nEn Conflicto: " + enConflicto;
    }
}

class NaciónDesarrollada extends Nación {
    private boolean tieneTecnologiaAvanzada;
    private int bonoTecnologia;

    public NaciónDesarrollada(String nombre, int poblacion, double recursos, int poderMilitar, boolean enConflicto, boolean tieneTecnologiaAvanzada, int bonoTecnologia) {
        super(nombre, poblacion, recursos, poderMilitar, enConflicto);
        this.tieneTecnologiaAvanzada = tieneTecnologiaAvanzada;
        this.bonoTecnologia = bonoTecnologia;
    }

    @Override
    public void calcularImpacto(Nación enemigo) {
        if (this.tieneTecnologiaAvanzada) {
            int nuevoPoderMilitar = this.getPoderMilitar() + bonoTecnologia;
            if (nuevoPoderMilitar > 100) nuevoPoderMilitar = 100;
            System.out.println(this.getNombre() + " tiene tecnologia avanzada, incrementando su poder militar a " + nuevoPoderMilitar);
        }
    }
}

class NaciónEnDesarrollo extends Nación {
    private int recursosLimitados;

    public NaciónEnDesarrollo(String nombre, int poblacion, double recursos, int poderMilitar, boolean enConflicto, int recursosLimitados) {
        super(nombre, poblacion, recursos, poderMilitar, enConflicto);
        this.recursosLimitados = recursosLimitados;
    }

    @Override
    public void calcularImpacto(Nación enemigo) {
        double impactoReducido = 1 - (0.01 * recursosLimitados);
        int nuevoPoderMilitar = (int) (this.getPoderMilitar() * impactoReducido);
        System.out.println(this.getNombre() + " tiene recursos limitados, reduciendo su poder militar a " + nuevoPoderMilitar);
    }
}
/*

Simulando conflicto entre Venezuela y USA
Venezuela tiene recursos limitados, reduciendo su poder militar a 36
Consecuencias del conflicto:
Venezuela pierde 0 habitantes y 90000000 en recursos.
USA pierde 0 habitantes y 450000000 en recursos.

Simulando conflicto entre Venezuela y Rusia
Venezuela tiene recursos limitados, reduciendo su poder militar a 36
Consecuencias del conflicto:
Venezuela pierde 0 habitantes y 81000000 en recursos.
Rusia pierde 0 habitantes y 360000000 en recursos.

Simulando conflicto entre USA y Honduras
USA tiene tecnologia avanzada, incrementando su poder militar a 100
Consecuencias del conflicto:
USA pierde 0 habitantes y 405000000 en recursos.
Honduras pierde 0 habitantes y 45000000 en recursos.

Simulando conflicto entre Rusia y Honduras
Rusia tiene tecnologia avanzada, incrementando su poder militar a 100
Consecuencias del conflicto:
Rusia pierde 0 habitantes y 324000000 en recursos.
Honduras pierde 0 habitantes y 40500000 en recursos.

Reporte Final:
Nacion: USA
Poblacion: 0
Recursos: 4.05E9
Poder Militar: 90
En Conflicto: true
Nacion: Rusia
Poblacion: 0
Recursos: 3.24E9
Poder Militar: 85
En Conflicto: true
Nacion: Venezuela
Poblacion: 0
Recursos: 8.1E8
Poder Militar: 40
En Conflicto: true
Nacion: Honduras
Poblacion: 0
Recursos: 4.05E8
Poder Militar: 35
En Conflicto: true

Total de conflictos simulados: 4
debug-single:
BUILD SUCCESSFUL (total time: 3 seconds)

*/