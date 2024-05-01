package DatabazeKnih;

public abstract class Kniha implements Comparable <Kniha> {

    public String   jmeno;
    public String   autor;
    public int      rokvydani;
    public boolean  dostupnost;

    public Kniha(String jmeno, String autor, int rokvydani, boolean dostupnost) {
        this.jmeno      = jmeno;
        this.autor      = autor;
        this.rokvydani  = rokvydani;
        this.dostupnost = dostupnost;
        }
    public String getJmeno(){
        return jmeno;
        }
    public void setJmeno (String jmeno) {
        this.jmeno = jmeno;
    }
    public String getAutor(){
        return autor;
    }
    public void setAutor (String autor) {
        this.autor= autor;
    }
    public int getRokVydani(){
        return rokvydani;
    }
    public void setRokVydani(int rokvydani) {
        this.rokvydani = rokvydani;
    }
    public String getDostupnost() {
        if (dostupnost) {
            return "dostupná";
        }
        else {
            return "nedostupná";
        }
    }
    public void setDostupnost(boolean dostupnost) {
        this.dostupnost = dostupnost;
    }
    public abstract String getDruh();

    @Override
    public int compareTo(Kniha o) {
        return Integer.compare(this.rokvydani, o.rokvydani);
    }

}