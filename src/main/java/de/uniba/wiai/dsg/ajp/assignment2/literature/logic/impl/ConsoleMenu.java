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

    public ConsoleMenu() {
        super();
    }


    // this is the Main Menu first step!
    public static void Menu() {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            int Zahl = 1;
            while (Zahl != 0) {// I cant think a better way to solve this condition() right now
                System.out.println("::::::::::::::::::::::::: Uni Bamberg Database :::::::::::::::::::::::::\n" +
                        "( 1 ) Load and Validate Literature Database\n" +
                        "( 2 ) Create New Literature Database\n" +
                        "( 0 ) Exit System\n" +
                        "::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");

                int option = Integer.parseInt(br.readLine());

                switch (option) {
                    case 1: //Load and Validate Literature Database !! here still doesn't working because of the  load
                        MainServiceImpl msI = new MainServiceImpl();
                        System.out.println("Please insert the XML File to be loaded in the Database and validated: ");
                        //msI.load("");
                        //msI.validate("");
                        break;

                    case 2: //Create New Literature Database and calls CreateNewDatabase method.
                        System.out.println("########## UNI Bamberg Database ########## ");
                        MainServiceImpl mainService = new MainServiceImpl();
                        mainService.create();
                        ConsoleMenu.CreateNewDatabase();

                    case 0:
                        // give a option to end the program and close the Ressource
                        Zahl = 0;
                        System.out.println("Program will now stop");

                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("the following Error occurred: " + e.getMessage());
        }
    }


    // Second Menu if the User choose the the second option(2)!
    public static void CreateNewDatabase() {

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        DatabaseServiceImpl createdDatabase = new DatabaseServiceImpl();
        int option;
        try {  // we need to figure out how to implement this Menu!

            do {
                System.out.println("(1) Add Author\n" +
                        "(2) Remove Author\n" +
                        "(3) Add Publication\n" +
                        "(4) Remove Publication\n" +
                        "(5) List Authors\n" +
                        "(6) List Publications\n" +
                        "(7) Print XML on Console\n" +
                        "(8) Save XML to File\n" +
                        "(0) Back to main menu / close without saving");

                ConsoleHelper.build();
                option = Integer.parseInt(br.readLine());
                switch (option) {
                    case 1:
                        System.out.println("Please enter the name of the new author: ");
                        String authorName = br.readLine();
                        System.out.println("Please enter the email of the new author: ");
                        String authorEmail = br.readLine();
                        while (!ValidationHelper.isEmail(authorEmail)) {
                            System.out.println("please enter a valid email");
                            authorEmail = br.readLine();
                        }

                        System.out.println("Please enter the id of the new author: ");
                        String authorID = (br.readLine());
                        while (!ValidationHelper.isId(authorID)) {
                            System.out.println("please enter a valid id");
                            authorID = br.readLine();
                        }
                        createdDatabase.addAuthor(authorName, authorEmail, authorID);
                        System.out.println("The author: " + authorName + " with email: " + authorEmail + " and id: " + authorID + " has been added to the database");
                        break;

                    case 2:
                        System.out.println("Please enter the id of the author that should be removed ");
                        String removeAuthorID = (br.readLine());
                        boolean wasFound = false;
                        int i;
                        for (i = 0; i < createdDatabase.getAuthors().size(); i++) {
                            if (createdDatabase.getAuthors().get(i).getId().contains(removeAuthorID)) {
                                System.out.println("Author with the id " + removeAuthorID + " has been found and removed from the database");
                                createdDatabase.removeAuthorByID(removeAuthorID);
                                wasFound = true;
                            }
                        }
                        if (!wasFound)
                            System.out.println("Author with the id " + removeAuthorID + " has not been found in the database");
                        break;

                    case 3:

                        System.out.println("Please enter the title of the publication: ");
                        String pubTitle = br.readLine();

                        System.out.println("Please enter the year the publication was published: ");
                        int pubYear = Integer.parseInt(br.readLine());

                        System.out.println("Please enter the type of the publication: ");
                        String pubType = br.readLine();

                        System.out.println("Please enter the author(s) of the publication: ");
                        String pubAuthorsString = br.readLine();

                        System.out.println("Please enter the id of the publication: ");
                        String pubID = br.readLine();

                        // createdDatabase.addPublication();
                        break;

                    case 4:

                        break;

                    case 5:
                        System.out.println("Authors currently in Database: ");
                        for (int j = 0; j < createdDatabase.getAuthors().size(); j++) {
                            System.out.println("Name:" + createdDatabase.getAuthors().get(j).getName() + " ID:" + createdDatabase.getAuthors().get(j).getId());
                        }
                        break;

                    case 6:

                        break;

                    case 7:

                        break;

                    case 8:

                        break;

                    case 0:

                        break;


                }
            } while (option != 0);
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


