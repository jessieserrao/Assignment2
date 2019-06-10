package de.uniba.wiai.dsg.ajp.assignment2.literature.logic.impl;

import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment2.literature.ui.ConsoleHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;


// switch cases implementation are incomplete and load issue still unsolved..  OBS:
/**
 Zur Interaktion mit der Kommandozeile ist die Hilfsklasse ConsoleHelper verfuegbar. Diese erlaubt u.a.
 das Einlesen von Zeichenketten, Zahlen und der Auswahl eines oder mehrerer Elemente aus einem Array von der Konsole.
 Die JavaDoc-Beschreibungen der oeffentlichen Methoden enthalten Anwendungsbeispiele, die Sie als Starthilfe verwenden koennen.
*/
public class ConsoleMenu extends DatabaseServiceImpl {

    public ConsoleMenu(){ super(); }

    // this is the Main Menu first step!
    public static void Menu() {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            System.out.println("::::::::::::::::::::::::: Uni Bamberg Database :::::::::::::::::::::::::\n" +
                    "( 1 ) Load and Validate Literature Database\n" +
                    "( 2 ) Create New Literature Database\n" +
                    "( 0 ) Exit System\n" +
                    "::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n" +
                     "Enter with your choice here: ");

            int Zahl=-1;
            while (Zahl != 0) {// I cant think a better way to solve this condition() right now


                int option = Integer.parseInt(br.readLine());

                switch (option) {
                    case 1: //Load and Validate Literature Database !! here still doesn't working because of the  load
                            MainServiceImpl msI = new MainServiceImpl();
                            System.out.println("Please insert the XML File to be loaded and validated in the Database: %s %s ");
                            //msI.load("");
                            //msI.validate("");
                            br.close();
                            isr.close();
                            break;

                    case 2: //Create New Literature Database and calls CreateNewDatabase method.
                            System.out.println("########## UNI Bamberg Database ########## ");
                            ConsoleMenu.subMenu();
                            br.close();
                            isr.close();
                            break;

                    case 3:
                            // give a option to end the program and close the Resource
                            br.close();
                            isr.close();
                            break;
                }
            }
        } catch (IOException e) {
            System.out.println("the following Error occurred: " + e.getMessage());
        }finally {

        }
    }


    // Second Menu if the User choose the the second option(2)!
    public static void subMenu () {
            System.out.println( "(1) Add Author\n" +
                                "(2) Remove Author\n" +
                                "(3) Add Publication\n" +
                                "(4) Remove Publication\n" +
                                "(5) List Authors\n" +
                                "(6) List Publications\n" +
                                "(7) Print XML on Console\n" +
                                "(8) Save XML to File\n" +
                                "(0) Back to main menu / close without saving");

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

       try {  // we need to figure out how to implement this Menu!

           int option = Integer.parseInt(br.readLine());
           String read;
           switch (option) {
               case 1:
                       DatabaseServiceImpl newAuthor = new DatabaseServiceImpl();
                       System.out.println("Please enter with the name, E-Mail and a ID to the new author: ");
                       newAuthor.addAuthor("name", "email", "id");
                   break;

               case 2:
                       DatabaseServiceImpl removeAuthor = new DatabaseServiceImpl();
                       System.out.println("Please give the ID of the author to be removed:  ");
                       //removeAuthor.removePublicationByID();
                        System.out.format("%s", "Id");
                   break;

               case 3:
                   System.out.println("Please give the ID the name od the Author: ");
                   break;

               case 4:
                   System.out.println("Please give the ID the name od the Author: ");
                   break;

               case 5:
                   System.out.println("Please give the ID the name od the Author: ");
                   //Ausgabe der Autoren bzw. Publikationen auf der Konsole erwartet.
                   break;

               case 6:
                   System.out.println("Please give the ID the name od the Author: ");
                   //Ausgabe der Autoren bzw. Publikationen auf der Konsole erwartet.
                   break;

               case 7:
                   System.out.println("Please give the ID the name od the Author: ");
                   break;

               case 8:
                   System.out.println("Please give the ID the name od the Author: ");
                   break;

               case 0:

                   ConsoleMenu.Menu();
                   break;


           }
       }catch (LiteratureDatabaseException e) {
           System.out.println("the following Error occurred: " + e.getMessage());

       }catch (IOException f){
           System.out.println(MessageFormat.format("the following Error occurred: {0}", f.getMessage()));
       }finally {
                   try{
                       isr.close();
                       br.close();
                   }catch (IOException e){
                       System.out.println("the following Error occurred: " + e.getMessage());
                   }
       }
    }



}


