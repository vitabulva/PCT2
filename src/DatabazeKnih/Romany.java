package DatabazeKnih;
import java.io.*;


public class Romany extends Kniha implements Serializable {
    private static final long serialVersionUID = 659128769256618066L;

    public Zanr zanr;

    public Romany(String jmeno, String autor, int rokvydani, boolean dostupnost, Zanr zanr) {
        super(jmeno, autor, rokvydani, dostupnost);
        this.zanr = zanr;
    }
    public static Romany nacistZeSouboru(File soubor) throws IOException, ClassNotFoundException {
        try (FileInputStream fileIn = new FileInputStream(soubor);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (Romany) in.readObject();
        }
    }

    public enum Zanr{
        Komiksy, Kucharky, Pohadky, Detektivky, Horory;

        public static boolean contains(String testString) {
            try {
                Zanr.valueOf(testString);
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        }
    }

    public Zanr getZanr(){
        return zanr;
    }
    public void setZanr (Zanr zanr) {
        this.zanr = zanr;
    }
   @Override
    public String getDruh(){
        return "román";
    }
}
