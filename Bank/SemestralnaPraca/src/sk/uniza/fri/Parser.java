package sk.uniza.fri;

import java.util.Scanner;

/**
 * 1.4.2021 - 8:32
 *
 * @author marti
 */
public class Parser {
    private final Scanner precitaj;         // zdroj vstupov od hraca
    public Parser() {
        this.precitaj = new Scanner(System.in);
    }

    public OverPrikaz nacitajPrikaz(int cisloPrikazu, int cisloIndexUz) {
        System.out.print("--> ");     // vyzva pre hraca na zadanie prikazu
        String vstupnyRiadok = this.precitaj.nextLine();
        String prikaz = null;

        Scanner citac = new Scanner(vstupnyRiadok);
        if (citac.hasNext()) {
            prikaz = citac.next();
        }

        return new OverPrikaz(prikaz, cisloPrikazu, cisloIndexUz);



    }


}
