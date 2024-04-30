package DatabazeKnih;

import javax.xml.crypto.Data;
import java.util.Scanner;
import java.util.TreeSet;

import static DatabazeKnih.Databaze.knihy;

public class Test {

    public static int pouzeCelaCisla(Scanner sc){
        int cislo=0;
        try{
            cislo = sc.nextInt();
        }
        catch (Exception e){
            System.out.println("Nastala vyjímka typu"+e.toString());
            System.out.println("Zadejte prosím cele čislo");
            sc.next();
            cislo = pouzeCelaCisla(sc);
        }
        return cislo;
    }

    public static float pouzeCela(Scanner sc){
        float cislo = 0;
        try{
            cislo = sc.nextFloat();
        }
        catch (Exception e){
            System.out.println("Nastala vyjímka typu "+e.toString());
            System.out.println("zadejte prosim cislo ");
            sc.nextLine();
            cislo = pouzeCelaCisla(sc);
        }
        return cislo;
    }
    public static void main(String [] args){

        Scanner sc=new Scanner(System.in);

        int romanORuceb;
        int volba;
        boolean run=true;

        while (run)
        {
            System.out.println("Vítej v databázi knih! zde jsou možnosti:");
            System.out.println("1 .. vložení nové knihu");
            System.out.println("2 .. úprava parametrů knihy");
            System.out.println("3 .. smazání knihy");
            System.out.println("4 .. vrácení/vypůjčení knihy");
            System.out.println("5 .. výpis knih z databáze");
            System.out.println("6 .. vyhledání knihy");
            System.out.println("7 .. díla daného autora");
            System.out.println("8 .. vyhledání knihy podle žánru");
            System.out.println("9 .. výpis vypůjčených knih");

            //....

            volba=pouzeCelaCisla(sc);

            switch (volba){
                case 1:
                    System.out.println("Vyber druh knihy - román 1 nebo učebnice 2 ");
                    romanORuceb=pouzeCelaCisla(sc);
                    switch (romanORuceb) {
                        case 1:
                            System.out.println("Přidání románu: ");
                                Funkce.addRoman();
                                break;
                        case 2:
                            System.out.println("Přidání učebnice: ");
                                Funkce.addUcebnice();
                                break;
                        default:
                            System.out.println("Neplatná volba");
                                break;
                    }
                        break;
                case 2:
                    System.out.println("Vyber knihu, dle názvu a uprav autora, rok vydání nebo stav dostupnosti");
                        Databaze.upravaKnih(knihy);
                        break;
                case 3:
                    System.out.println("Vyber knihu, kterou chceš smazat");
                        Databaze.smazaniKnihy(knihy);
                        break;
                case 4:
                    System.out.println("Vyber knihu, kterou si půjčil nebo vrátil");
                        Databaze.pujceniKnihy(knihy);
                        break;
                case 5:
                    System.out.println("Výpis knih v databázi");
                        Databaze.vypisKnih(knihy);
                        break;
                case 6:
                    System.out.println("Vyhledání knihy v databázi");
                        Databaze.vyhledaniKnihy(knihy);
                        break;
                case 7:
                    System.out.println("Vyhledání knih podle autora");
                        Databaze.knihyAutora(knihy);
                        break;
                case 8:
                    System.out.println("Vyhledání knih podle žánru");
                        Databaze.knihyPodleZanru(knihy);
                        break;
                case 9:
                    System.out.println("Výpis vypůjčených knih");
                        Databaze.vypujceneKnihy(knihy);
                        break;

            }
        }
    }
}
