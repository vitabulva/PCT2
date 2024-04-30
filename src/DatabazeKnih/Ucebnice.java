package DatabazeKnih;

public class Ucebnice extends Kniha {

    public int rocnik;

    public Ucebnice(String jmeno, String autor, int rokvydani, boolean dostupnost, int rocnik) {
        super(jmeno, autor, rokvydani, dostupnost);
        this.jmeno = jmeno;
        this.rocnik = rocnik;
    }
    public int getRocnik(){
        return rocnik;
    }
    public void setRocnik (Integer rocnik) {
        this.rocnik = rocnik;
    }
   @Override
    public String getDruh() {
        return "učebnice";
    }

}