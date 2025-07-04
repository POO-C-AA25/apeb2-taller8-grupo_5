import java.util.ArrayList;
public class Problema_2_Menu {
    public static void main(String[] args) {
        Menu menualacarta = new menuCarta(7.5, 8.4);
    }
}
class Cuenta{
        public String nom_cliente;
        ArrayList<Menu> listmenu = new ArrayList<>();
        

}


abstract class  Menu {
    public String nom_menu;
    public double val_menu;
    public double valinsMenu;
    public abstract double calcValmenu();

    public Menu(String nom_menu, double val_menu, double valinsMenu) {
        this.nom_menu = nom_menu;
        this.val_menu = val_menu;
        this.valinsMenu = valinsMenu;
    }
    
}
class menuCarta extends Menu{
    public double valorporcion;
    public double valorBebida;

    public menuCarta(double valorporcion, double valorBebida) {
        super(nom_menu, val_menu, valinsMenu);
        this.valorporcion = valorporcion;
        this.valorBebida = valorBebida;
    }

    
    
    public double calcValmenu(){
        
    return valinsMenu + valorporcion + valorBebida;
    };

    @Override
    public String toString() {
        return "menuCarta{" + "valorporcion=" + valorporcion + ", valorBebida=" + valorBebida + '}';
    }
    
}