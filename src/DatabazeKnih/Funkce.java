package DatabazeKnih;
import java.io.Serializable;
import java.io.*;


import java.util.Scanner;
import java.util.Set;
import java.io.IOException;

import static DatabazeKnih.Databaze.knihy;
import static DatabazeKnih.Romany.Zanr.*;

public class Funkce implements Serializable {

    public static Scanner scanner = new Scanner(System.in);
    public static Object nacteniZeSouboru;
    static Databaze databaze = new Databaze();

    public static int pouzeCelaCisla(Scanner sc) {
        int cislo = 0;
        try {
            cislo = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Nastala vyjímka typu" + e.toString());
            System.out.println("Zadejte prosím cele čislo");
            sc.next();
            cislo = pouzeCelaCisla(sc);
        }
        return cislo;
    }

    public static float pouzeCela(Scanner sc) {
        float cislo = 0;
        try {
            cislo = sc.nextFloat();
        } catch (Exception e) {
            System.out.println("Nastala vyjímka typu " + e.toString());
            System.out.println("zadejte prosim cislo ");
            sc.nextLine();
            cislo = pouzeCelaCisla(sc);
        }
        return cislo;
    }

    public static void addRoman() {

        Scanner sc = new Scanner(System.in);
        int volba;
        boolean run = true;
        Romany.Zanr zanr = null;

        System.out.println("Zadej název knihy");
        String jmeno = scanner.next();
        System.out.println("Zadej název autora");
        String autor = scanner.next();
        System.out.println("Zadej rok vydání");
        int rokvydani = scanner.nextInt();
        System.out.println("Zadej zda je dostupná");
        Boolean dostupnost = true;
        System.out.println("Vyber žánr stisknutí 1, 2, 3, 4, 5\n " +
                "Komiksy 1, Kucharky 2 , Pohadky 3 , Detektivky 4 , Horory 5 ");


        while (run) {
            volba = pouzeCelaCisla(sc);
            switch (volba) {
                case 1:
                    zanr = Komiksy;
                    run = false;
                    break;
                case 2:
                    zanr = Kucharky;
                    run = false;
                    break;
                case 3:
                    zanr = Pohadky;
                    run = false;
                    break;
                case 4:
                    zanr = Detektivky;
                    run = false;
                    break;
                case 5:
                    zanr = Horory;
                    run = false;
                    break;
            }
            Romany roman = new Romany(jmeno, autor, rokvydani, dostupnost, zanr);
            databaze.pridejKnihu(roman);

            break;
        }
    }

    public static void addUcebnice() {
        System.out.println("Zadej název knihy");
        String jmeno = scanner.nextLine();
        System.out.println("Zadej název autora");
        String autor = scanner.nextLine();
        System.out.println("Zadej rok vydání");
        int rokvydani = scanner.nextInt();
        System.out.println("Zadej zda je dostupná");
        Boolean dostupnost = true;
        System.out.println("Zadej ročník pro jaký je učebnice určená");
        int rocnik = scanner.nextInt();
        scanner.nextLine();

        Ucebnice ucebnice = new Ucebnice(jmeno, autor, rokvydani, dostupnost, rocnik);
        databaze.pridejKnihu(ucebnice);

    }

    public static String findKniha(Set<Kniha> knihy) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Zadej název knihy: ");
        String hledanyNazev = sc.nextLine();

        for (Kniha kniha : knihy) {
            if (kniha.getJmeno().equalsIgnoreCase(hledanyNazev)) {
                return hledanyNazev;
            }
        }

        System.out.println("Kniha nebyla nalezena.");
        return null;
    }

    public static String findAutora(Set<Kniha> knihy) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Zadej jméno autora: ");
        String hledanyAutor = sc.nextLine();

        for (Kniha kniha : knihy) {
            if (kniha.getAutor().equalsIgnoreCase(hledanyAutor)) {
                return hledanyAutor;
            }
        }

        System.out.println("Autor nebyl nalezen.");
        return null;
    }

    public static Romany.Zanr findZanr(Set<Kniha> knihy) {
        Scanner sc = new Scanner(System.in);

        int volba;
        boolean run = true;
        Romany.Zanr hledanyZanr = null;

        System.out.println("Vyber žánr stisknutí 1, 2, 3, 4, 5\n " +
                "Komiksy 1, Kucharky 2 , Pohadky 3 , Detektivky 4 , Horory 5 ");

        while (run) {
            volba = pouzeCelaCisla(sc);
            switch (volba) {
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
                default:
                    System.out.println("Zadejte platnou volbu (1-5).");
            }

        }
        return hledanyZanr;
    }

   /* public static void ulozKnihuDoSouboru(Set<Kniha> knihy, String jmeno) {

        try {
            FileOutputStream fileOut = new FileOutputStream(knihy.getJmeno() + ".ser");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(knihy);
            objectOut.close();
            fileOut.close();
            System.out.println("Kniha uložena do souboru: " + knihy.getJmeno() + ".ser");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/


    public static void ulozKnihuDoSouboru(String soubor) {

        String jmeno;
        String autor;
        int rokvydani;
        boolean dostupnost;
        Romany.Zanr zanr = null;
        int rocnik;

        try (PrintWriter writer = new PrintWriter(new FileWriter(soubor))) {
            for (Kniha kniha : knihy) {
                if (kniha instanceof Romany) {
                    writer.println(((Romany) kniha).getJmeno() + "\n" +
                            ((Romany) kniha).getAutor() + "\n" +
                            ((Romany) kniha).getRokVydani() + "\n" +
                            ((Romany) kniha).getStav() + "\n" +
                            ((Romany) kniha).getZanr());
                } else if (kniha instanceof Ucebnice) {
                    writer.println(((Ucebnice) kniha).getJmeno() + "\n" +
                            ((Ucebnice) kniha).getAutor() + "\n" +
                            ((Ucebnice) kniha).getRokVydani() + "\n" +
                            ((Ucebnice) kniha).getStav() + "\n" +
                            ((Ucebnice) kniha).getRocnik());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean nacteniZeSouboru(String jmeno) {
        String autor;
        int rokvydani;
        boolean dostupnost;
        int rocnik;
        String typ;

        try (Scanner load = new Scanner(new FileReader(jmeno))) // deklarovany scanner pro cteni ze souboru
        {
            int i = 0;
            while (load.hasNextLine()) // dokud je dalsi scannovatelna vec s souboru pokracuj
            {
                jmeno = load.next();
                autor = load.next();
                rokvydani = load.nextInt();
                dostupnost = load.nextBoolean();
                typ = load.next();


                if (Romany.Zanr.contains(typ)) {
                    Romany.Zanr zanr = Romany.Zanr.valueOf(typ);
                    Romany roman = new Romany(jmeno, autor, rokvydani, dostupnost, zanr);
                    databaze.pridejKnihu(roman);

                } else {

                    Ucebnice ucebnice = new Ucebnice(jmeno, autor, rokvydani, dostupnost, Integer.parseInt(typ));
                    databaze.pridejKnihu(ucebnice);

                }


                i++;
            }
        } catch (IOException e) {
            System.out.println("Vyjimka typu: " + e.toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Nebyl inicializovan array nebo pocet studentu je vetsi nez array");
        }

        return false;
    }

}
