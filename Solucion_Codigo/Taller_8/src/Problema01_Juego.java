/**
 *En un juego de rol, se desea implementar un sistema de combate en el que participen diferentes tipos de personajes: 
 *guerreros, magos y arqueros. Cada personaje tiene atributos y habilidades únicas, así como diferentes métodos de ataque y defensa.
 *El objetivo del juego es enfrentar a los personajes en batallas y determinar el ganador en función de sus habilidades, estrategias y atributos. 
 *Los guerreros se destacan por su fuerza y habilidades cuerpo a cuerpo, los magos por sus hechizos y poderes mágicos, 
 *y los arqueros por su precisión y habilidades a distancia.
 *El sistema debe permitir crear nuevos personajes de cada tipo, asignarles atributos iniciales, como puntos de vida 
 *y nivel de experiencia, y permitirles subir de nivel a medida que ganan batallas. 
 *Además, se debe implementar un algoritmo de combate que evalúe las habilidades de cada personaje y determine el resultado de la batalla.
 *Utilizando programación orientada a objetos, herencia y polimorfismo, implementa el sistema de combate y las clases necesarias para 
 *representar a los diferentes tipos de personajes. Asegúrate de que cada tipo de personaje tenga sus propias habilidades y métodos 
 *de ataque y defensa, y que puedan interactuar entre sí en las batallas.
 * @author  Erick Malla , Johan Wang
 */
import java.util.Scanner;
public class Problema01_Juego {
    public static Scanner tcl = new Scanner (System.in);
    public static Personaje p1 = iniciarP();
    public static Personaje p2 = iniciarP();
    public static int daño;
    public static int atac;
    public static int def;
    public static void main(String[] args) {
        estado(p1, p2);
        int distancia = distancia();
        batalla(p1, p2, distancia);
        estado(p1, p2);
    }
    public static Personaje iniciarP(){
        Personaje player;
        System.out.println("Escoja el combatiente");
        System.out.println("1. Mago");
        System.out.println("2. Guerrero");
        System.out.println("3. Arquero");
        int opc = tcl.nextInt();
        switch (opc) {
            case 1:
                System.out.println("Ingrese el nombre");
                player = new Mago(tcl.next());
                break;
            case 2:
                System.out.println("Ingrese el nombre");
                player = new Guerrero(tcl.next());
                break;
            case 3:
                System.out.println("Ingrese el nombre");
                player = new Arquero(tcl.next());
                break;    
            default:
                throw new AssertionError();
        }
        return player;
    }
    public static void interPers(Personaje pers){
        if (pers instanceof Mago) {
            System.out.println("       /\\");
            System.out.println("      /  \\");
            System.out.println("     /    \\");
            System.out.println("    /______\\");
            System.out.println("    " + pers.nombre);
            System.out.println(" --------------");
            if (pers.vivo == true ) {
                System.out.println(" |  -   ,   -  |");
            }else
                System.out.println(" |  x   ,   x  |");
        }
        if (pers instanceof Guerrero) {
            System.out.println("  _____________");
            System.out.println(" |-------------|");
            System.out.println("     " + pers.nombre);
            System.out.println(" --------------");
            if (pers.vivo == true) {
                System.out.println(" |  o   ,   o  |");
            }else
                System.out.println(" |  x   ,   x  |");
        }
        if (pers instanceof Arquero) {
            System.out.println("  _____________");
            System.out.println("   |         |");
            System.out.println("   " + pers.nombre);
            System.out.println(" --------------");
            if (pers.vivo == true) {
                System.out.println(" |  v   ,   v  |");
            }else
                System.out.println(" |  x   ,   x  |");
        }        
    }
    public static int distancia(){
        int distancia = (int) (Math.random() * 20);
        System.out.println("distancia " + distancia + " metros");
        System.out.print("Y");
        for (int i = 0; i < distancia; i++) {
            System.out.print("- ");
        }
        System.out.println("Y");
        return distancia;
    }
    public static void batalla(Personaje p1, Personaje p2,int distancia){
        do {            
            progreso(p1, p2, distancia);
            progreso(p2, p1, distancia);
            if (distancia > 0) {
               distancia -= 5;
            System.out.print("Acercamiento de 5 metros "); 
            }
            distancia = (distancia < 0)? 0 : distancia;
            System.out.println("distancia : " + distancia + " metros");
        } while (p1.vida > 0 && p2.vida > 0); 
        p1.vida = (p1.vida > 0 )? p1.vida : 0 ;
        p2.vida = (p2.vida > 0 )? p2.vida : 0 ;
        if (p1.vida == 0 && p2.vida == 0) {
            System.out.println("EMPATE");
            p1.vivo = false; 
            p2.vivo = false; 
            return;
        }else{
            if (p1.vida <= 0) {
                p2.nivel ++;
                p1.nivel --;
                System.out.println(p2.nombre + " ha ganado la batalla");
                System.out.println("Nivel de exp aumentado en 1");
                p1.vivo = false;
            }
            if (p2.vida <= 0) {
                p1.nivel ++;
                p2.nivel --;
                System.out.println(p1.nombre + " ha ganado la batalla");
                System.out.println("Nivel de exp aumentado en 1");
                p2.vivo = false;
            }
        }
    } 
    public static void progreso(Personaje p1, Personaje p2, int distancia){
        atac = p1.ataque(distancia);
        def = p2.defensa(distancia);
        daño = atac - def;
        daño =(daño > 0)? daño : 0;
        p1.vida -= daño;
        System.out.println(p1.nombre + " hace " + atac + " pts de daño : " + p2.nombre + " defiende " + def + " pts" + " : daño final " + daño);
    }
    public static void estado(Personaje p1, Personaje p2){
        System.out.println("Player 1 " + p1);
        interPers(p1);
        System.out.println("Player 2 " + p2);
        interPers(p2);
    }
    
}
abstract class Personaje{
    public int vida;
    public int nivel;
    public boolean vivo;
    public String nombre;
    public abstract int ataque(int dis);
    public abstract int defensa(int dis);
}
class Guerrero extends Personaje{
    
    public Guerrero() {
        
    }
    public Guerrero(String nombre){
        this.nombre = nombre;
        this.vida = 100;
        this.nivel = 1;
        this.vivo = true;
    }
    public int ataque(int dis){
        if (dis < 5) {
            return 50;
        }else if(dis < 10){
            return 30;
        }else
            return 10;
    }
    public int defensa(int dis){
        if (dis < 5) {
            return 10;
        }else if(dis < 10){
            return 15;
        }else
            return 25;
    }
    public String toString() {
        return "Personaje{" + " Nombre = " + this.nombre + ", nivel = " + this.nivel + ", vida = " + this.vida + '}';
    }
}
    
class Mago extends Personaje{

    public Mago() {
    }
    
    public Mago(String nombre){
        this.nombre = nombre;
        vida = 90;
        nivel = 1;
        this.vivo = true;
    }
    public int ataque(int dis){
        if (dis < 5) {
            return 40;
        }else if(dis < 10){
            return 45;
        }else
            return 30;
    }
    public int defensa(int dis){
        if(dis < 10){
            return 5;
        }else
            return 20;
    }
    public String toString() {
        return "Personaje{" + " Nombre = " + this.nombre + ", nivel = " + this.nivel + ", vida = " + this.vida + '}';
    }
}
class Arquero extends Personaje{
    public Arquero (String nombre){
        this.nombre = nombre;
        vida = 80;
        nivel = 1;
        this.vivo = true;
    }
    public int ataque(int dis){
        if (dis < 5) {
            return 10;
        }else if(dis < 15){
            return 25;
        }else
            return 60;
    }
    public int defensa(int dis){
        if (dis < 5) {
            return 5;
        }else if(dis < 10){
            return 10;
        }else
            return 20;
    }
    public String toString() {
        return "Personaje{" + " Nombre = " + this.nombre + ", nivel = " + this.nivel + ", vida = " + this.vida + '}';
    }
}