package sk.uniza.fri;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 1.4.2021 - 8:32
 *
 * @author marti
 */
public class ZapisVyber implements IZapisDoLogu {
    @Override
    public void zapis(String meno, String priezvisko, String ciastka) {
        try {
            File myObj = new File("src/log/log.txt");
            if (myObj.createNewFile()) {
                System.out.println("Subor vytvoreny: " + myObj.getName()); // Vytvorilo
            } else {
                FileWriter fw = new FileWriter("src/log/log.txt", true);
                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String strDate = dateFormat.format(date);
                fw.write("\nDATUM: " + strDate + "\nMENO: " + meno + " " + priezvisko + "\nVYBRAL: " + ciastka);
                fw.write("\n -------------------------------------------------------------------------------");
                fw.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
