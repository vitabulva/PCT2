package DatabazeKnih;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;

import static DatabazeKnih.Databaze.knihy;
import static DatabazeKnih.Romany.Zanr.*;

public class SQL {
    private static final SQL INSTANCE = new SQL();
    private SQL() {}
    public static SQL getInstance() {
        return INSTANCE;
    }

    public Connection conn;
    private int numberOfBook = 0;

    public boolean connect() {
        conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\db\\Databaze");
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public void disconnect() {

        if (conn != null) {
            try {conn.close();}
            catch (SQLException ex) {System.out.println(ex.getMessage());}
        }
    }
    public boolean novaTabulka(){
        if (conn==null) return false;
        try{
        String sql_k = "CREATE TABLE IF NOT EXISTS knihy ("
                + "id integer PRIMARY KEY,"
                + "jmeno varchar NOT NULL,"
                + "autor varchar NOT NULL,"
                + "rokVydani integer NOT NULL,"
                + "dostupnost bit NOT NULL,"
                + "typ integer NOT NULL,"
                + "knihaid integer FOREIGN KEY )";

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql_k);
            stmt.close();
            return true;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean vyprazdniTabulku() {
        if (conn==null) return false;
        String sql_k = "DROP TABLE knihy";
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(sql_k);
            return true;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean insertKnihy(Kniha kniha) {
        if (conn==null) return false;
        try {
            numberOfBook++;
            String sql = "INSERT INTO knihy(jmeno,autor,rokVydani,dostupnost,typ) VALUES(?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
          //  pstmt.setInt(1, numberOfBook);
            pstmt.setString(2, kniha.getJmeno());
            pstmt.setString(3, kniha.getAutor());
            pstmt.setInt(4, kniha.getRokVydani());
            pstmt.setBoolean(5, kniha.dostupnost);
            pstmt.setInt(6, (kniha instanceof Romany)?1:2);
            if(kniha instanceof Romany) {pstmt.setInt(7, ((Romany)kniha).getZanr().ordinal());}
            else {pstmt.setInt(7, ((Ucebnice)kniha).getRocnik());}
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public Set<Kniha> knihyDatabasePull() {

        boolean run = true;
        Romany.Zanr hledanyZanr = null;

        Set<Kniha> knihy = new TreeSet<>();

        if (conn==null) return knihy;
        try {
            String sql_k = "SELECT * FROM knihy";
            Statement stmt  = conn.createStatement();
            ResultSet rs_k  = stmt.executeQuery(sql_k);
            while (rs_k.next()) {
                Set<String> autorSet = new TreeSet<>();
                String sql_a = "SELECT jmeno FROM autor WHERE knihaid = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql_a);
                pstmt.setInt(1, rs_k.getInt("id"));
                ResultSet rs_a = pstmt.executeQuery();
                while (rs_a.next()) {
                    autorSet.add(rs_a.getString("jmeno"));
                }
                Romany.Zanr zanr = null;
                switch(rs_k.getInt("typ")) {
                    case 1:
                        switch(rs_k.getInt("property")) {
                            case 1:
                                hledanyZanr = Komiksy;
                                run = false;
                                break;
                            case 2:
                                hledanyZanr = Kucharky;
                                run = false;
                                break;
                            case 3:
                                hledanyZanr = Pohadky;
                                run = false;
                                break;
                            case 4:
                                hledanyZanr = Detektivky;
                                run = false;
                                break;
                            case 5:
                                hledanyZanr = Horory;
                                run = false;
                                break;
                        }
                        knihy.add(new Romany(rs_k.getString("jmeno"),rs_k.getString("autor"),rs_k.getInt("rokVydani"), rs_k.getBoolean("dostupnost"), zanr));
                        break;
                    case 2:
                        knihy.add(new Ucebnice(rs_k.getString("jmeno"),rs_k.getString("autor"),rs_k.getInt("rokVydani"), rs_k.getBoolean("dostupnost"), rs_k.getInt ("rocnik")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return knihy;
    }

}