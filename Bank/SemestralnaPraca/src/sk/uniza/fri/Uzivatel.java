package sk.uniza.fri;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

/**
 * 1.4.2021 - 8:32
 *
 * @author marti
 */
public class Uzivatel {

    private final String meno;
    private final String priezvisko;

    public Uzivatel(String meno, String priezvisko) {

        this.meno = meno;
        this.priezvisko = priezvisko;

    }

    public void info() {
        System.out.println();
        System.out.println("\u001B[35m" + "Prihlásený ako: " + meno + " " + priezvisko + "\u001B[0m");
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        System.out.println("Dnešny dátum a čas: " + strDate);
    }

    public String getMeno() {
        return this.meno;
    }
    public String getPriezvisko() {
        return this.priezvisko;
    }
}
