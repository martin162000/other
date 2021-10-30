package sk.uniza.fri;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * 1.4.2021 - 8:32
 *
 * @author marti
 */
public class DatabazaMysql {

    private final ArrayList<ArrayList<String>> arrLIST = new ArrayList<>();

    private String mysqldbnazov;
    private String mysqldbmeno;
    private String mysqldbheslo;
    private String mysqldns;
    private String mysqlport;
    private String mysqlconnectstring;
    private static Connection conn = null;

    public DatabazaMysql() {

        this.ziskajMysqlNastavenia();
        this.myslqPripojit();
    }

    private void ziskajMysqlNastavenia() {

        try (InputStream input = new FileInputStream("src/dbnastavenie/configdb.properties")) {

            Properties prop = new Properties();

            prop.load(input);

            this.mysqldbnazov = prop.getProperty("nazov_databazy");
            this.mysqldbmeno = prop.getProperty("meno_do_databazy");
            this.mysqldbheslo = prop.getProperty("heslo_do_databazy");
            this.mysqldns = prop.getProperty("dns_databaza");
            this.mysqlport = prop.getProperty("port");
            this.mysqlconnectstring = "jdbc:mysql://" + mysqldns + ":" + mysqlport + "/" + mysqldbnazov + "?user=" + mysqldbmeno + "&password=" + mysqldbheslo + "&useUnicode=true&characterEncoding=UTF-8";

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void myslqPripojit() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(this.mysqlconnectstring);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from banka");
            while (rs.next()) {
                ArrayList<String> a1 = new ArrayList<>();
                a1.add(rs.getString(1)); // id
                a1.add(rs.getString(2)); // pin
                a1.add(rs.getString(3)); // meno
                a1.add(rs.getString(4)); // priezvisko
                a1.add(rs.getString(5)); // zostatok
                this.arrLIST.add(a1);

            }

            conn.close();

        } catch (Exception e) {
            System.err.println("ERROR : Nie je mozne pripojit sa k databaze.");
            System.err.println(e.getMessage());
        }

    }

    public void update(String pin, String zostatok) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(this.mysqlconnectstring);
            String query = "UPDATE banka SET zostatok = ? " +
                    "WHERE PIN = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, zostatok);
            ps.setString(2, pin);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<ArrayList<String>> getArrList() {
        return this.arrLIST;
    }

}