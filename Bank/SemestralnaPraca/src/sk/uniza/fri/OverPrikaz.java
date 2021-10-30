package sk.uniza.fri;

import java.util.ArrayList;

/**
 * 1.4.2021 - 8:32
 *
 * @author marti
 */
public class OverPrikaz {
    private final ArrayList < ArrayList < String >> arrlistcely;
    private static final String[] BANKOVE_PRIKAZY = {"vyber", "vklad", "ukonci"};
    private String bankovyPrikaz;
    private int ktoryIndexUzivatel;

    private boolean overeny;
    private boolean menu;
    private boolean peniaze;

    public OverPrikaz(String prikaz, int volba, int indexPouz) {
        DatabazaMysql databaza = new DatabazaMysql();
        this.bankovyPrikaz = prikaz;
        this.arrlistcely = databaza.getArrList();

        if (indexPouz > 0) {
            this.ktoryIndexUzivatel = indexPouz;
            this.ktoryIndexUzivatel--;
        }

        if (volba == 1) {
            this.overUzivatela();
        } else if (volba == 2) {
            this.overMenu();
        } else if (volba == 3) {
            this.overPeniaze(this.bankovyPrikaz);

            if (Integer.parseInt(this.bankovyPrikaz) < 10000) {

                int plusStringy = Integer.parseInt(this.bankovyPrikaz) + Integer.parseInt(this.getZostatokUzivatela());
                String vsetkoDokopyPlus = String.valueOf(plusStringy);
                databaza.update(this.getPinUzivatela(), vsetkoDokopyPlus);

            } else {
                this.peniaze = false;
                System.out.println("\u001B[31m" + "Nemozes naraz vlozit viac ako 10000. Ak chces odist napis 0" + "\u001B[0m");

            }
        } else if (volba == 4) {
            this.overPeniaze(this.bankovyPrikaz);

            if (Integer.parseInt(this.getZostatokUzivatela()) > Integer.parseInt(this.bankovyPrikaz)) {
                int plusStringy = Integer.parseInt(this.getZostatokUzivatela()) - Integer.parseInt(this.bankovyPrikaz);
                String vsetkoDokopyPlus = String.valueOf(plusStringy);

                databaza.update(this.getPinUzivatela(), vsetkoDokopyPlus);
            } else {
                this.peniaze = false;
                System.out.println("\u001B[31m" + "Nemas dostatok penazi. Ak chces odist napis 0" + "\u001B[0m");

            }

        }

    }

    private void overMenu() {
        if (this.jePlatny(this.bankovyPrikaz)) {
            this.menu = true;

        } else {
            this.menu = false;
            System.out.println("\u001B[31m" + "Neplatný prikaz, mozte pouzit iba [vklad, vyber, ukonci]." + "\u001B[0m");
        }

    }

    private void overPeniaze(String cislo) {
        String dajLenCisla = "";

        if (cislo != null) {
            dajLenCisla = cislo.replaceAll("[^\\d]", "");
        }

        if (dajLenCisla.equals("")) {
            dajLenCisla = null;
            this.bankovyPrikaz = "0";
        }

        if (dajLenCisla != null && Integer.parseInt(dajLenCisla) >= 0) {
            this.peniaze = true;

        } else {
            this.peniaze = false;
            System.out.println("\u001B[31m" + "Neplatný vstup, prosím, zadajte len čisla." + "\u001B[0m");
        }

    }

    private void overUzivatela() {
        if (this.jePlatnyArr(this.bankovyPrikaz)) {
            this.overeny = true;

        } else {
            this.overeny = false;
            if (this.bankovyPrikaz != null && !this.bankovyPrikaz.equals("xadminx")) {
                System.out.println("\u001B[31m" + "Neplatný PIN, skúste znova." + "\u001B[0m");
            } else if (this.bankovyPrikaz == null) {
                System.out.println("\u001B[31m" + "Neplatný PIN, skúste znova." + "\u001B[0m");
            }
        }

    }

    public String getNazovPrikaz() {
        return this.bankovyPrikaz;
    }

    public String getPinUzivatela() {
        return this.arrlistcely.get(this.ktoryIndexUzivatel).get(1);
    }

    public String getMenoUzivatela() {
        return this.arrlistcely.get(this.ktoryIndexUzivatel).get(2);
    }

    public String getPriezviskoUzivatela() {
        return this.arrlistcely.get(this.ktoryIndexUzivatel).get(3);
    }

    public String getZostatokUzivatela() {
        return this.arrlistcely.get(this.ktoryIndexUzivatel).get(4);
    }

    public int getKtoryIndexUzivatel() {
        return this.ktoryIndexUzivatel;
    }

    public boolean isOvereny() {
        return this.overeny;
    }

    public boolean isPeniaze() {
        return this.peniaze;
    }

    public boolean isMenu() {
        return this.menu;
    }
    public int getKolkoZaznamov() {
        int pocetZaz = 0;
        for (int i = 0; i < this.arrlistcely.size(); i++) {
            pocetZaz++;
        }
        return pocetZaz;
    }

    public void vypisAdminVsetkychUzivatelov() {
        for (ArrayList<String> strings : this.arrlistcely) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }

    private boolean jePlatny(String nazov) {
        for (int i = 0; i < OverPrikaz.BANKOVE_PRIKAZY.length; i++) {
            if (OverPrikaz.BANKOVE_PRIKAZY[i].equals(nazov)) {
                return true;
            }
        }
        return false;
    }

    private boolean jePlatnyArr(String nazov) {
        for (int i = 0; i < this.arrlistcely.size(); i++) {
            if (this.arrlistcely.get(i).get(1).equals(nazov)) { // get(i).get(1) hladanie PINOV
                this.ktoryIndexUzivatel = i;
                return true;
            }
        }
        return false;
    }

}