
/**
 * Problema 4 - Sistema de monitoreo de impactos del cambio climático en Ecuador
Una red de monitoreo ambiental tiene como objetivo registrar, analizar y 
* reportar los impactos del cambio climático en diferentes regiones. En cada 
* ubicación se instalan dispositivos capaces de medir distintos indicadores 
* climáticos como temperatura, precipitación, calidad del aire, y humedad del 
* suelo. Dependiendo de la región (costa, sierra y oriente), los dispositivos
* pueden variar en capacidades y protocolos de recolección.

Los datos recolectados deben almacenarse y analizarse periódicamente. Además, 
* ciertas ubicaciones requieren generar reportes personalizados que destaquen 
* riesgos ambientales como sequías, deslizamientos o contaminación del aire. 
* Algunos dispositivos pueden comportarse de forma especializada para detectar 
* únicamente ciertos tipos de indicadores dependiendo de la región (costa,
* sierra y oriente).

Requisitos funcionales:
Representar diferentes tipos de dispositivos y sus especializaciones, para
* la costa, sierra y oriente.
Implementar métodos polimórficos que permitan procesar los datos según los 
* tipos de dispositivos y sus especializaciones, para la costa, sierra y oriente.
Generar reportes dinámicos en función del tipo de riesgo ambiental detectado
* según la región
Note

Plantee una solución polimórfica dada una jerarquía de clases con ventajas 
* de herencia. Y para la generación de reportería, use los toString() base.
 *@author Erick Malla , Johan Wang
 */

public class Problema_4_MonitoreoClimatico {
    public static void main(String[] args) {
        DispositivoCosta dispositivoCosta = new DispositivoCosta("Dispositivo Costa", 30.5, 85.2, 75.0);
        DispositivoSierra dispositivoSierra = new DispositivoSierra("Dispositivo Sierra", 22.0, 60.0, 45.0);
        DispositivoOriente dispositivoOriente = new DispositivoOriente("Dispositivo Oriente", 28.5, 92.0, 80.0);
        
        System.out.println(dispositivoCosta);
        System.out.println(dispositivoSierra);
        System.out.println(dispositivoOriente);
    }
}

abstract class Dispositivo {
    String nombre;
    double temperatura;
    double precipitacion;
    double calidadAire;

    public Dispositivo(String nombre, double temperatura, double precipitacion, double calidadAire) {
        this.nombre = nombre;
        this.temperatura = temperatura;
        this.precipitacion = precipitacion;
        this.calidadAire = calidadAire;
    }

    public abstract void procesarDatos();
    
    @Override
    public String toString() {
        return "Dispositivo: " + nombre + "\nTemperatura: " + temperatura + " C\nPrecipitacion: " + precipitacion + " mm\nCalidad del Aire: " + calidadAire;
    }
}

class DispositivoCosta extends Dispositivo {
    public DispositivoCosta(String nombre, double temperatura, double precipitacion, double calidadAire) {
        super(nombre, temperatura, precipitacion, calidadAire);
    }

    @Override
    public void procesarDatos() {
        System.out.println("Procesando datos para la region Costa...");
        if (precipitacion < 50.0) {
            System.out.println("Posible riesgo de sequia en la Costa.");
        }
        if (calidadAire > 100.0) {
            System.out.println("Alerta: Contaminacion del aire en la Costa.");
        }
    }

    @Override
    public String toString() {
        procesarDatos();  
        return super.toString() + "\nRiesgo detectado: Sequia/Contaminacion en la Costa";
    }
}

class DispositivoSierra extends Dispositivo {
    public DispositivoSierra(String nombre, double temperatura, double precipitacion, double calidadAire) {
        super(nombre, temperatura, precipitacion, calidadAire);
    }

    @Override
    public void procesarDatos() {
        System.out.println("Procesando datos para la region Sierra...");
        if (precipitacion > 100.0) {
            System.out.println("Posible riesgo de deslizamiento de tierra en la Sierra.");
        }
        if (calidadAire < 50.0) {
            System.out.println("Alerta: Calidad del aire muy baja en la Sierra.");
        }
    }

    @Override
    public String toString() {
        procesarDatos(); 
        return super.toString() + "\nRiesgo detectado: Deslizamientos/Contaminacion en la Sierra";
    }
}

class DispositivoOriente extends Dispositivo {
    public DispositivoOriente(String nombre, double temperatura, double precipitacion, double calidadAire) {
        super(nombre, temperatura, precipitacion, calidadAire);
    }

    @Override
    public void procesarDatos() {
        System.out.println("Procesando datos para la region Oriente...");
        if (precipitacion > 200.0) {
            System.out.println("Posible riesgo de inundacion en la region Oriente.");
        }
        if (calidadAire > 100.0) {
            System.out.println("Alerta: Contaminacion del aire en la region Oriente.");
        }
    }

    @Override
    public String toString() {
        procesarDatos();  
        return super.toString() + "\nRiesgo detectado: Inundacion/Contaminacion en la region Oriente";
    }
}
/**
 * Procesando datos para la region Costa...
Dispositivo: Dispositivo Costa
Temperatura: 30.5 C
Precipitacion: 85.2 mm
Calidad del Aire: 75.0
Riesgo detectado: Sequia/Contaminacion en la Costa
Procesando datos para la region Sierra...
Alerta: Calidad del aire muy baja en la Sierra.
Dispositivo: Dispositivo Sierra
Temperatura: 22.0 C
Precipitacion: 60.0 mm
Calidad del Aire: 45.0
Riesgo detectado: Deslizamientos/Contaminacion en la Sierra
Procesando datos para la region Oriente...
Dispositivo: Dispositivo Oriente
Temperatura: 28.5 C
Precipitacion: 92.0 mm
Calidad del Aire: 80.0
Riesgo detectado: Inundacion/Contaminacion en la region Oriente
debug-single:
BUILD SUCCESSFUL (total time: 3 seconds)

 */