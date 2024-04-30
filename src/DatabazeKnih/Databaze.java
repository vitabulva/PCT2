package DatabazeKnih;

import java.util.*;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import static DatabazeKnih.Funkce.scanner;
import static DatabazeKnih.Romany.Zanr.*;
import static DatabazeKnih.Test.pouzeCelaCisla;


public class Databaze {
    private String nazev;
    static Set<Kniha> knihy = new TreeSet<Kniha>(new Comparator<Kniha>() {

        @Override
        public int compare(Kniha k1, Kniha k2) {
            return k1.getJmeno().compareTo(k2.getJmeno());
        }
    });


    public Set<Kniha> getKnihy() {
        return knihy;
    }

    public void pridejKnihu(Kniha kniha) {
        knihy.add(kniha);
        System.out.println("nova kniha" + knihy + kniha.getJmeno());
    }

    public static String hledanyNazev = null;

    public static void upravaKnih(Set<Kniha> knihy) {
        Scanner sc = new Scanner(System.in);
        Iterator<Kniha> itr = knihy.iterator();

        int volba;
        boolean run = true;
        Boolean dostupnost;
        int stav;

        System.out.println("Zadej název knihy, kterou chceš editovat:\n ");
        String hledanyNazev = Funkce.findKniha(knihy);
        System.out.println("Nalezená kniha: " + hledanyNazev);


        if (hledanyNazev != null) {


            while (run) {

                System.out.println("1 .. změna autora");
                System.out.println("2 .. změna roku vydání");
                System.out.println("3 .. změna stavu dostupnosti");

                volba = pouzeCelaCisla(sc);


                switch (volba) {
                    case 1:
                        System.out.println("Zadej nové jméno autora");

                        while (itr.hasNext()) {
                            Kniha kniha = itr.next();
                            if (kniha.getJmeno().equals(hledanyNazev)) {
                                String novyAutor = sc.next();
                                kniha.setAutor(novyAutor);
                                System.out.println("Autor knihy " + kniha.getJmeno() + " byl úspěšně změněn na " + kniha.getAutor());
                                return;
                            }
                        }


                        break;
                    case 2:
                        System.out.println("Zadej nový rok vydání");

                        while (itr.hasNext()) {
                            Kniha kniha = itr.next();
                            if (kniha.getJmeno().equals(hledanyNazev)) {
                                int novyRok = sc.nextInt();
                                kniha.setRokVydani(novyRok);
                                System.out.println("Rok vydání knihy " + kniha.getJmeno() + " byl úspěšně změněn na " + kniha.getRokVydani());
                                return;
                            }
                        }
                        break;
                    case 3:
                        System.out.println("Vyber změnu stavu 1- dostupná, 2- nedostupná");
                        stav = pouzeCelaCisla(sc);
                        switch (stav) {
                            case 1:
                                while (itr.hasNext()) {
                                    Kniha kniha = itr.next();
                                    if (kniha.getJmeno().equals(hledanyNazev)) {
                                        kniha.setDostupnost(true);
                                        System.out.println("Stav knihy " + kniha.getJmeno() + " byl úspěšně změněn na " + kniha.getDostupnost());
                                        return;
                                    }
                                }
                                break;
                            case 2:
                                while (itr.hasNext()) {
                                    Kniha kniha = itr.next();
                                    if (kniha.getJmeno().equals(hledanyNazev)) {
                                        kniha.setDostupnost(false);
                                        System.out.println("Stav knihy " + kniha.getJmeno() + " byl úspěšně změněn na " + kniha.getDostupnost());
                                        return;
                                    }
                                }
                                break;
                            default:
                                System.out.println("Neplatná volba");
                                break;
                        }

                }

            }

        } else {
            System.out.println("Kniha nebyla nalezena " + hledanyNazev + " nebyla nalezena.");
        }

    }

    public static void smazaniKnihy(Set<Kniha> knihy) {
        Scanner sc = new Scanner(System.in);
        Iterator<Kniha> itr = knihy.iterator();

        System.out.println("Zadej název knihy, kterou chceš smazat:\n ");
        String hledanyNazev = Funkce.findKniha(knihy);

        System.out.println("Nalezená kniha: " + hledanyNazev);
        while (itr.hasNext()) {
            Kniha kniha = itr.next();
            if (kniha.getJmeno().equals(hledanyNazev)) {
                itr.remove();
                System.out.println("Kniha " + hledanyNazev + " byla úspěšně smazána.");
                return;
            }
        }

        System.out.println("Kniha s názvem " + hledanyNazev + " nebyla nalezena.");
    }

    public static void pujceniKnihy(Set<Kniha> knihy) {
        Scanner sc = new Scanner(System.in);
        Iterator<Kniha> itr = knihy.iterator();

        Boolean dostupnost;
        boolean run = true;
        int stav;

        System.out.println("Zadej název knihy, kterou jsi vratil nebo půjčil:\n ");
        String hledanyNazev = Funkce.findKniha(knihy);

        System.out.println("Nalezená kniha: " + hledanyNazev);
        while (run) {
            System.out.println("Kniha je  1- vrácena , 2- půjčená");
            stav = pouzeCelaCisla(sc);
            switch (stav) {
                case 1:
                    while (itr.hasNext()) {
                        Kniha kniha = itr.next();
                        if (kniha.getJmeno().equals(hledanyNazev)) {
                            kniha.setDostupnost(true);
                            System.out.println("Stav knihy " + kniha.getJmeno() + " byl úspěšně změněn na " + kniha.getDostupnost());
                            return;
                        }
                    }
                    break;
                case 2:
                    while (itr.hasNext()) {
                        Kniha kniha = itr.next();
                        if (kniha.getJmeno().equals(hledanyNazev)) {
                            kniha.setDostupnost(false);
                            System.out.println("Stav knihy " + kniha.getJmeno() + " byl úspěšně změněn na " + kniha.getDostupnost());
                            return;
                        }
                    }
                    break;
                default:
                    System.out.println("Neplatná volba");
                    break;
            }

        }

        System.out.println("Kniha s názvem " + hledanyNazev + " nebyla nalezena.");
    }

    public static void vypisKnih(Set<Kniha> knihy) {

        Iterator<Kniha> itr = knihy.iterator();
        while (itr.hasNext()) {
            Kniha kniha = itr.next();
            System.out.println(" Jméno: " + kniha.getJmeno()
                    + ", Autor: " + kniha.getAutor()
                    + ", rok vydání: " + kniha.getRokVydani()
                    + ", Dostupnost: " + kniha.getDostupnost());
            if (kniha instanceof Romany) {
                Romany romany = (Romany) kniha;
                System.out.println("Žanr: " + romany.getZanr());
            } else {
                Ucebnice ucebnice = (Ucebnice) kniha;
                System.out.println("Ročník: " + ucebnice.getRocnik());

            }

        }

    }

    public static void vyhledaniKnihy(Set<Kniha> knihy) {

        Iterator<Kniha> itr = knihy.iterator();
        System.out.println("Zadej název knihy, kterou hledáš:\n ");
        String hledanyNazev = Funkce.findKniha(knihy);

        System.out.println("Hledená kniha: " + hledanyNazev);

        while (itr.hasNext()) {
            Kniha kniha = itr.next();
            if (kniha.getJmeno().equals(hledanyNazev)) {
                System.out.println(" Jméno: " + kniha.getJmeno()
                        + ", Autor: " + kniha.getAutor()
                        + ", rok vydání: " + kniha.getRokVydani()
                        + ", Dostupnost: " + kniha.getDostupnost());
                if (kniha instanceof Romany) {
                    Romany romany = (Romany) kniha;
                    System.out.println("Žanr: " + romany.getZanr());
                } else {
                    Ucebnice ucebnice = (Ucebnice) kniha;
                    System.out.println("Ročník: " + ucebnice.getRocnik());

                }
                return;
            }
            break;

        }

    }

    public static void knihyAutora(Set<Kniha> knihy) {

        System.out.println("Zadej jméno autora, jehož díla hledáš:\n ");
        String hledanyAutor = Funkce.findAutora(knihy);

        Comparator<Kniha> comparator = new Comparator<Kniha>() {
            @Override
            public int compare(Kniha k1, Kniha k2) {
                int rok1 = k1.getRokVydani();
                int rok2 = k2.getRokVydani();
                return Integer.compare(rok1, rok2);
            }
        };

        Set<Kniha> knihyAutora = new TreeSet<>(comparator);

        for (Kniha kniha : knihy) {
            if (kniha.getAutor().equalsIgnoreCase(hledanyAutor)) {
                knihyAutora.add(kniha);
            }
        }

        System.out.println("Hledaný autor: " + hledanyAutor);


        for (Kniha kniha : knihyAutora) {
            System.out.println("Autor: " + kniha.getAutor()
                    + ", Jméno: "      + kniha.getJmeno()
                    + ", rok vydání: " + kniha.getRokVydani()
                    + ", Dostupnost: " + kniha.getDostupnost()
            );
            if (kniha instanceof Romany) {
                Romany romany = (Romany) kniha;
                System.out.println("Žanr: " + romany.getZanr());
            } else {
                Ucebnice ucebnice = (Ucebnice) kniha;
                System.out.println("Ročník: " + ucebnice.getRocnik());

            }
        }
    }

    public static void knihyPodleZanru (Set<Kniha> knihy) {

        Iterator<Kniha> itr = knihy.iterator();
        System.out.println("Zadej žánr, hledaný žanr:\n ");
        Romany.Zanr hledanyZanr = Funkce.findZanr(knihy);

        System.out.println("Hledená kniha: " + hledanyZanr);

        while (itr.hasNext()) {
            Kniha kniha = itr.next();
            if (kniha instanceof Romany) {
                Romany romany = (Romany) kniha;
                if (romany.getZanr() == hledanyZanr) {
                    System.out.println("Jméno: " + kniha.getJmeno()
                            + ", Autor: " + kniha.getAutor()
                            + ", Rok vydání: " + kniha.getRokVydani()
                            + ", Dostupnost: " + kniha.getDostupnost()
                            + ", Žánr: " + romany.getZanr());
                }
            }
        }
    }
 //   public static void

}