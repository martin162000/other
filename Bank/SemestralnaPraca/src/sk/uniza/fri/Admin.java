package sk.uniza.fri;

/**
 * 1.4.2021 - 8:32
 *
 * @author marti
 */
public class Admin extends Uzivatel {

    private final int zaznamy;


    public Admin(String meno, String priezvisko, int pocetZaznamov) {
        super(meno, priezvisko);
        this.zaznamy = pocetZaznamov;
    }
    public void info() {
        super.info();
        System.out.println("-----------------------------------");
        System.out.println("\u001B[35m" + "Pocet zaznamov " + this.zaznamy + "\u001B[0m");
    }
}

