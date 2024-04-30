package DatabazeKnih;
import java.util.Scanner;
import java.util.Set;

import static DatabazeKnih.Romany.Zanr.*;
import static DatabazeKnih.Test.pouzeCelaCisla;


public class Funkce {

    public static Scanner scanner = new Scanner(System.in);
    static Databaze databaze = new Databaze();

    public static void addRoman() {

        Scanner sc=new Scanner(System.in);
        int volba;
        boolean run=true;
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



            while (run)

            {
                volba=pouzeCelaCisla(sc);
                switch (volba) {
                    case 1:
                        zanr = Komiksy;
                        run =false;
                        break;
                    case 2:
                        zanr = Kucharky;
                        run =false;
                        break;
                    case 3:
                        zanr = Pohadky;
                        run =false;
                        break;
                    case 4:
                        zanr = Detektivky;
                        run =false;
                        break;
                    case 5:
                        zanr = Horory;
                        run =false;
                        break;
                }
            Romany roman = new Romany (jmeno, autor, rokvydani, dostupnost, zanr);
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

        Ucebnice ucebnice = new Ucebnice (jmeno, autor, rokvydani, dostupnost, rocnik);
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
}
