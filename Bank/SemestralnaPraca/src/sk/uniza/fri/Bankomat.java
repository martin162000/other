package sk.uniza.fri;

/**
 * 1.4.2021 - 8:32
 *
 * @author marti
 */
public class Bankomat {
    private final Parser parser;
    private int indexUzivatela;

    public Bankomat() {
        this.parser = new Parser();
    }

    private void vytvorVypis() {
        System.out.println();
        System.out.println("\u001B[35m" + "Dobry deň!" + "\u001B[0m");
        System.out.println("\u001B[35m" + "Vitajte v bankomate UNIZA!" + "\u001B[0m");
        System.out.println();
        System.out.println("\u001B[34m" + "Prosím, overte svoje konto svojim PINOM (6 znakov)" + "\u001B[0m");

    }

    public void bSystem() {

        this.vytvorVypis();
        String peniazeUzivatlea;
        boolean dajKoniec = false;
        boolean overenyUzivatel = false;
        boolean vyberNiecoMenu = false;
        boolean vyberPeniaze = false;
        boolean vyberPeniazeOdkaz = false;
        boolean vkladPenazi = false;
        boolean vkladPenaziOdkaz = false;

        while (!dajKoniec) {
            while (!overenyUzivatel) {
                OverPrikaz prikaz = this.parser.nacitajPrikaz(1, 0);
                String nazovPrikazu = prikaz.getNazovPrikaz();
                overenyUzivatel = prikaz.isOvereny();

                if (nazovPrikazu != null && nazovPrikazu.equals("xadminx")) {
                    Admin vypisadmin = new Admin("Admin", "", prikaz.getKolkoZaznamov());
                    vypisadmin.info();
                    prikaz.vypisAdminVsetkychUzivatelov();


                    dajKoniec = true;
                    overenyUzivatel = true;
                    vyberNiecoMenu = true;
                    vyberPeniaze = true;
                    vyberPeniazeOdkaz = true;
                    vkladPenazi = true;
                    vkladPenaziOdkaz = true;
                    break;
                }

                if (overenyUzivatel) {
                    this.indexUzivatela = prikaz.getKtoryIndexUzivatel();
                    //prikaz.setKtoryIndexUzivatel(Integer.parseInt(this.pinUzivatela));

                    Uzivatel vypisuzivatela = new Uzivatel(prikaz.getMenoUzivatela(), prikaz.getPriezviskoUzivatela());
                    vypisuzivatela.info();
                    System.out.println("\u001B[35m" + "Zostatok: " + prikaz.getZostatokUzivatela() + "\u001B[0m");
                    System.out.println();
                    System.out.println("\u001B[34m" + "Vyberte príkaz, môžte použiť iba [vklad, vyber, ukonci]" + "\u001B[0m");
                }
                if (nazovPrikazu != null && nazovPrikazu.equals("ukonci")) {
                    dajKoniec = true;
                }

            }

            while (!vyberNiecoMenu) {

                OverPrikaz prikaz = this.parser.nacitajPrikaz(2, 0);
                String nazovPrikazu = prikaz.getNazovPrikaz();
                vyberNiecoMenu = prikaz.isMenu();
                if (vyberNiecoMenu) {

                    if (nazovPrikazu.equals("vklad")) {
                        vkladPenaziOdkaz = true;
                        System.out.println("\u001B[34m" + "Vložte peniaze, môžte zadavat len čisla maximálne do 10000." + "\u001B[0m");
                    } else if (nazovPrikazu.equals("vyber")) {
                        vyberPeniazeOdkaz = true;
                        System.out.println("\u001B[34m" + "Vyberte si peniaze, môžte maximálne vybrať: " + prikaz.getZostatokUzivatela() + "\u001B[0m");

                    }

                }

                if (nazovPrikazu != null && nazovPrikazu.equals("ukonci")) {
                    dajKoniec = true;
                }

            }

            while (!vkladPenazi && vkladPenaziOdkaz) {

                OverPrikaz prikaz = this.parser.nacitajPrikaz(3, this.indexUzivatela + 1);
                String nazovPrikazu = prikaz.getNazovPrikaz();

                vkladPenazi = prikaz.isPeniaze();
                if (vkladPenazi) {
                    peniazeUzivatlea = nazovPrikazu;
                    int plusStringy = Integer.parseInt(peniazeUzivatlea) + Integer.parseInt(prikaz.getZostatokUzivatela());
                    System.out.println("\u001B[35m" + "Vložil si: " + peniazeUzivatlea + "\u001B[0m");
                    System.out.println("\u001B[35m" + "Tvoj zostatok je: " + plusStringy + "\u001B[0m");
                    ZapisVklad logVklad = new ZapisVklad();
                    logVklad.zapis(prikaz.getMenoUzivatela(), prikaz.getPriezviskoUzivatela(), peniazeUzivatlea);
                    dajKoniec = true;

                }

            }

            while (!vyberPeniaze && vyberPeniazeOdkaz) {

                OverPrikaz prikaz = this.parser.nacitajPrikaz(4, this.indexUzivatela + 1);
                String nazovPrikazu = prikaz.getNazovPrikaz();

                vyberPeniaze = prikaz.isPeniaze();
                if (vyberPeniaze) {
                    peniazeUzivatlea = nazovPrikazu;
                    int minusStringy = Integer.parseInt(prikaz.getZostatokUzivatela()) - Integer.parseInt(peniazeUzivatlea);
                    System.out.println("\u001B[35m" + "Vybral si: " + peniazeUzivatlea + "\u001B[0m");
                    System.out.println("\u001B[35m" + "Tvoj zostatok je: " + minusStringy + "\u001B[0m");
                    ZapisVyber logVklad = new ZapisVyber();
                    logVklad.zapis(prikaz.getMenoUzivatela(), prikaz.getPriezviskoUzivatela(), peniazeUzivatlea);
                    dajKoniec = true;

                }

            }

        }

    }

}