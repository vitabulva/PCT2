package DatabazeKnih;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;


import static DatabazeKnih.Funkce.pouzeCelaCisla;
import static DatabazeKnih.Databaze.knihy;

public class Test {


    public static void main(String [] args){

        Scanner sc=new Scanner(System.in);

        int romanORuceb;
        int volba;
        boolean run=true;

        while (run)
        {
            System.out.println("Vítej v databázi knih! zde jsou možnosti:");
            System.out.println("1  .. vložení nové knihu");
            System.out.println("2  .. úprava parametrů knihy");
            System.out.println("3  .. smazání knihy");
            System.out.println("4  .. vrácení/vypůjčení knihy");
            System.out.println("5  .. výpis knih z databáze");
            System.out.println("6  .. vyhledání knihy");
            System.out.println("7  .. díla daného autora");
            System.out.println("8  .. vyhledání knihy podle žánru");
            System.out.println("9  .. výpis vypůjčených knih");
            System.out.println("10 .. ulozeni databaze do souboru");
            System.out.println("11 .. nacteni databaze ze souboru");
            System.out.println("12 .. ukonceni aplikace");


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
               case 10:
                    try {
                        sc.nextLine(); // vyprazdneni scanneru
                        System.out.println("Zadejte jmeno souboru pro nacteni: ");
                        String jmeno = sc.nextLine();
                        Funkce.ulozKnihuDoSouboru(jmeno); // predani jmena do metody loadFromFile
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    break;
                case 11:
                    String jmeno;
                    System.out.println("Zadejte jméno souboru pro nahrání");
                    jmeno = sc.next() + sc.nextLine();
                    Funkce.nacteniZeSouboru(jmeno);
                    break;
                case 12:
                    run=false;
                    break;

            }
        }
    }
}
