/**
 * Problema 5 - Plataforma de apoyo a emprendimientos en Loja
Una organización local ha creado una plataforma digital para registrar y
* promover emprendimientos de la ciudad de Loja. Los emprendimientos pueden 
* clasificarse por tipo (tecnológico, artesanal, agrícola, gastronómico, etc.),
* y cada uno presenta información detallada sobre su misión, productos o 
* servicios, y datos de contacto. Algunos emprendimientos requieren 
* acompañamiento técnico por parte de mentores especializados, quienes brindan 
* asesoría en áreas como marketing, contabilidad o desarrollo de software.
* Además, los emprendimientos pueden participar en ferias locales, donde 
* presentan sus productos y compiten por reconocimientos. 
* Existen emprendimientos que evolucionan a lo largo del tiempo y extienden 
* sus líneas de productos o abren nuevas sedes.

Requisitos funcionales:
Diferenciar los distintos tipos de emprendimientos mediante herencia.
Asociar uno o más mentores a los emprendimientos que lo requieran.
Implementar comportamientos polimórficos en función del tipo de feria o
* actividad en la que participan.
Permitir registrar productos o servicios que ofrece cada emprendimiento.
Simular la evolución de un emprendimiento con el tiempo (crecimiento,
* diversificación, expansión).
Note

Plantee una solución polimórfica dada una jerarquía de clases con ventajas
* de herencia. Para la visualización de resultados use los toString() base.
 *@author Erick Malla , Johan Wang
 */


public class Problema_5_PlataformaEmprendimientos {
    public static void main(String[] args) {
        EmprendimientoTecnologico emprendimientoTecnologico = new EmprendimientoTecnologico("Tech Innovators", "Desarrollo de software", "Proveer soluciones tecnologicas innovadoras", 5);
        EmprendimientoArtesanal emprendimientoArtesanal = new EmprendimientoArtesanal("Artesanias Loja", "Artesania local", "Producir y vender artesanias autoctonas", 3);
        EmprendimientoGastronomico emprendimientoGastronomico = new EmprendimientoGastronomico("Sabores de Loja", "Restaurante", "Ofrecer gastronomia local", 7);

        Mentor mentor1 = new Mentor("Carlos Gomez", "Marketing");
        Mentor mentor2 = new Mentor("Marta Sanchez", "Contabilidad");

        emprendimientoTecnologico.agregarMentor(mentor1);
        emprendimientoArtesanal.agregarMentor(mentor2);

        emprendimientoTecnologico.registrarProducto("Software para gestion empresarial");
        emprendimientoArtesanal.registrarProducto("Mantas bordadas");
        emprendimientoGastronomico.registrarProducto("Ceviche de camaron");

        System.out.println(emprendimientoTecnologico);
        System.out.println(emprendimientoArtesanal);
        System.out.println(emprendimientoGastronomico);

        emprendimientoTecnologico.participarEnFeria("Feria de Innovacion Tecnologica");
        emprendimientoArtesanal.participarEnFeria("Feria de Artesanias Locales");
        emprendimientoGastronomico.participarEnFeria("Feria Gastronomica");

        emprendimientoTecnologico.evolucionar("Expansion a nuevas ciudades");
        emprendimientoArtesanal.evolucionar("Diversificacion de productos");
        emprendimientoGastronomico.evolucionar("Apertura de nuevas sedes");
    }
}

abstract class Emprendimiento {
    String nombre;
    String tipo;
    String mision;
    int aniosDeExperiencia;
    String productos;

    public Emprendimiento(String nombre, String tipo, String mision, int aniosDeExperiencia) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.mision = mision;
        this.aniosDeExperiencia = aniosDeExperiencia;
        this.productos = "";
    }

    public void registrarProducto(String producto) {
        this.productos += producto + ", ";
    }

    public abstract void participarEnFeria(String feria);
    public abstract void evolucionar(String evolucion);

    public void agregarMentor(Mentor mentor) {
        System.out.println("El emprendimiento " + nombre + " ahora tiene como mentor a " + mentor);
    }

    @Override
    public String toString() {
        return "Emprendimiento: " + nombre + "\nTipo: " + tipo + "\nMision: " + mision + "\nAnios de experiencia: " + aniosDeExperiencia + "\nProductos: " + productos;
    }
}

class EmprendimientoTecnologico extends Emprendimiento {
    public EmprendimientoTecnologico(String nombre, String mision, String productos, int aniosDeExperiencia) {
        super(nombre, "Tecnologico", mision, aniosDeExperiencia);
    }

    @Override
    public void participarEnFeria(String feria) {
        System.out.println("El emprendimiento " + nombre + " esta participando en la " + feria);
    }

    @Override
    public void evolucionar(String evolucion) {
        System.out.println("El emprendimiento " + nombre + " ha evolucionado: " + evolucion);
    }
}

class EmprendimientoArtesanal extends Emprendimiento {
    public EmprendimientoArtesanal(String nombre, String mision, String productos, int aniosDeExperiencia) {
        super(nombre, "Artesanal", mision, aniosDeExperiencia);
    }

    @Override
    public void participarEnFeria(String feria) {
        System.out.println("El emprendimiento " + nombre + " esta participando en la " + feria);
    }

    @Override
    public void evolucionar(String evolucion) {
        System.out.println("El emprendimiento " + nombre + " ha evolucionado: " + evolucion);
    }
}

class EmprendimientoGastronomico extends Emprendimiento {
    public EmprendimientoGastronomico(String nombre, String mision, String productos, int aniosDeExperiencia) {
        super(nombre, "Gastronomico", mision, aniosDeExperiencia);
    }

    @Override
    public void participarEnFeria(String feria) {
        System.out.println("El emprendimiento " + nombre + " esta participando en la " + feria);
    }

    @Override
    public void evolucionar(String evolucion) {
        System.out.println("El emprendimiento " + nombre + " ha evolucionado: " + evolucion);
    }
}

class Mentor {
    String nombre;
    String especialidad;

    public Mentor(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return nombre + " (Especialidad: " + especialidad + ")";
    }
}
/**
 * El emprendimiento Tech Innovators ahora tiene como mentor a Carlos Gomez (Especialidad: Marketing)
El emprendimiento Artesanias Loja ahora tiene como mentor a Marta Sanchez (Especialidad: Contabilidad)
Emprendimiento: Tech Innovators
Tipo: Tecnologico
Mision: Desarrollo de software
Anios de experiencia: 5
Productos: Software para gestion empresarial, 
Emprendimiento: Artesanias Loja
Tipo: Artesanal
Mision: Artesania local
Anios de experiencia: 3
Productos: Mantas bordadas, 
Emprendimiento: Sabores de Loja
Tipo: Gastronomico
Mision: Restaurante
Anios de experiencia: 7
Productos: Ceviche de camaron, 
El emprendimiento Tech Innovators esta participando en la Feria de Innovacion Tecnologica
El emprendimiento Artesanias Loja esta participando en la Feria de Artesanias Locales
El emprendimiento Sabores de Loja esta participando en la Feria Gastronomica
El emprendimiento Tech Innovators ha evolucionado: Expansion a nuevas ciudades
El emprendimiento Artesanias Loja ha evolucionado: Diversificacion de productos
El emprendimiento Sabores de Loja ha evolucionado: Apertura de nuevas sedes
debug-single:
BUILD SUCCESSFUL (total time: 3 seconds)

 */