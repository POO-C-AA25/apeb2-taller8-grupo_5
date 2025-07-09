import java.util.ArrayList;

public class Problema_2_Menu {
    public static void main(String[] args) {
        Cuenta cuenta = new Cuenta("Juan Perez");
        
        menuCarta menu1 = new menuCarta("Menu A la Carta", 15.0, 12.0, 5.0, 3.0);
        menuDia menu2 = new menuDia("Menu del Dia", 12.0, 10.0, 2.0, 2.5);
        menuNinos menu3 = new menuNinos("Menu Ninos", 8.0, 7.0, 1.5, 2.0);
        menuEconomico menu4 = new menuEconomico("Menu Economico", 10.0, 8.0, 15.0);
        
        cuenta.agregarMenu(menu1);
        cuenta.agregarMenu(menu2);
        cuenta.agregarMenu(menu3);
        cuenta.agregarMenu(menu4);
        
        System.out.println(cuenta);
        
        for (Menu menu : cuenta.listmenu) {
            System.out.println(menu);
        }
    }
}

class Cuenta {
    public String nom_cliente;
    public ArrayList<Menu> listmenu = new ArrayList<>();
    
    public Cuenta(String nom_cliente) {
        this.nom_cliente = nom_cliente;
    }
    
    public void agregarMenu(Menu menu) {
        listmenu.add(menu);
    }
    
    public double calcularSubtotal() {
        double subtotal = 0;
        for (Menu menu : listmenu) {
            subtotal += menu.calcValmenu();
        }
        return subtotal;
    }
    
    public double calcularIVA() {
        return calcularSubtotal() * 0.19; 
    }
    
    public double calcularTotal() {
        return calcularSubtotal() + calcularIVA();
    }
    
    @Override
    public String toString() {
        return "Cuenta{" +
               "Cliente='" + nom_cliente + '\'' +
               ", Subtotal=" + calcularSubtotal() +
               ", IVA=" + calcularIVA() +
               ", Total=" + calcularTotal() +
               '}';
    }
}

abstract class Menu {
    public String nom_menu;
    public double val_menu;
    public double valinsMenu;
    
    public Menu(String nom_menu, double val_menu, double valinsMenu) {
        this.nom_menu = nom_menu;
        this.val_menu = val_menu;
        this.valinsMenu = valinsMenu;
    }
    
    public abstract double calcValmenu();
    
    @Override
    public String toString() {
        return "Menu{" +
               "nombre='" + nom_menu + '\'' +
               ", valor=" + val_menu + 
               ", valor inicial=" + valinsMenu + '}';
    }
}

class menuCarta extends Menu {
    public double valorporcion;
    public double valorBebida;
    
    public menuCarta(String nom_menu, double val_menu, double valinsMenu, double valorporcion, double valorBebida) {
        super(nom_menu, val_menu, valinsMenu);
        this.valorporcion = valorporcion;
        this.valorBebida = valorBebida;
    }
    
    @Override
    public double calcValmenu() {
        return valinsMenu + valorporcion + valorBebida;
    }

    @Override
    public String toString() {
        return super.toString() + 
               ", valor porcion=" + valorporcion +
               ", valor bebida=" + valorBebida;
    }
}

class menuDia extends Menu {
    public double valorPostre;
    public double valorBebida;

    public menuDia(String nom_menu, double val_menu, double valinsMenu, double valorPostre, double valorBebida) {
        super(nom_menu, val_menu, valinsMenu);
        this.valorPostre = valorPostre;
        this.valorBebida = valorBebida;
    }

    @Override
    public double calcValmenu() {
        return valinsMenu + valorPostre + valorBebida;
    }

    @Override
    public String toString() {
        return super.toString() + 
               ", valor postre=" + valorPostre +
               ", valor bebida=" + valorBebida;
    }
}

class menuNinos extends Menu {
    public double valorHelado;
    public double valorPastel;
    
    public menuNinos(String nom_menu, double val_menu, double valinsMenu, double valorHelado, double valorPastel) {
        super(nom_menu, val_menu, valinsMenu);
        this.valorHelado = valorHelado;
        this.valorPastel = valorPastel;
    }
    
    @Override
    public double calcValmenu() {
        return valinsMenu + valorHelado + valorPastel;
    }

    @Override
    public String toString() {
        return super.toString() + 
               ", valor helado=" + valorHelado +
               ", valor pastel=" + valorPastel;
    }
}

class menuEconomico extends Menu {
    public double porcentajeDescuento;

    public menuEconomico(String nom_menu, double val_menu, double valinsMenu, double porcentajeDescuento) {
        super(nom_menu, val_menu, valinsMenu);
        this.porcentajeDescuento = porcentajeDescuento;
    }
    
    @Override
    public double calcValmenu() {
        return valinsMenu - (valinsMenu * (porcentajeDescuento / 100));
    }

    @Override
    public String toString() {
        return super.toString() + 
               ", descuento=" + porcentajeDescuento + "%";
    }
}
