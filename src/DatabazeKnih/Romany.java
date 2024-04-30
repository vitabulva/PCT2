package DatabazeKnih;

public class Romany extends Kniha {
    public Zanr zanr;
    public Romany(String jmeno, String autor, int rokvydani, boolean dostupnost, Zanr zanr) {
        super(jmeno, autor, rokvydani, dostupnost);
        this.zanr = zanr;
    }
    public enum Zanr{
        Komiksy, Kucharky, Pohadky, Detektivky, Horory
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
