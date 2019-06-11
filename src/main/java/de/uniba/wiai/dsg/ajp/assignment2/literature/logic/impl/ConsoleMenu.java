package de.uniba.wiai.dsg.ajp.assignment2.literature.logic.impl;

import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.ValidationHelper;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Author;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Publication;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.PublicationType;
import de.uniba.wiai.dsg.ajp.assignment2.literature.ui.ConsoleHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.ArrayList;


public class ConsoleMenu extends DatabaseServiceImpl {

    public ConsoleMenu() {
        super();
    }



    public static void Menu() {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            int Zahl = 1;
            while (Zahl != 0) {
                System.out.println("::::::::::::::::::::::::: Uni Bamberg Database :::::::::::::::::::::::::\n" +
                        "( 1 ) Load and Validate Literature Database\n" +
                        "( 2 ) Create New Literature Database\n" +
                        "( 0 ) Exit System\n" +
                        "::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");

                int option = Integer.parseInt(br.readLine());

                switch (option) {
                    case 1:
                        MainServiceImpl msI = new MainServiceImpl();
                        System.out.println("Please enter with the XML File to be loaded in the Database and validated: ");
                        String XMLFile = br.readLine();
                        try {
                            msI.load(XMLFile);
                            msI.validate(XMLFile);
                        }catch (LiteratureDatabaseException e){
                            System.out.println("the following Error occurred: " + e.getMessage());
                        }
                        System.out.println("The given XML File was successfully added and validated in our database: ");
                        break;

                    case 2:
                        System.out.println("########## UNI Bamberg Database ########## ");
                        MainServiceImpl mainService = new MainServiceImpl();
                        try{
                            mainService.create();
                            ConsoleMenu.CreateNewDatabase();
                        }catch (LiteratureDatabaseException e){
                            e.getMessage();
                        }


                    case 0:

                        Zahl = 0;
                        System.out.println("Program will now stop");

                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("the following Error occurred: " + e.getMessage());
        }
    }


    public static void CreateNewDatabase() {

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        DatabaseServiceImpl createdDatabase = new DatabaseServiceImpl();
        int option;
        try {

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
                        System.out.println("The author " + authorName + " with email " + authorEmail + " and id " + authorID + " has been added to the database");
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

                        System.out.println("Please choose the type of the publication:\n"+
                                "(1) Book\n" +
                                "(2) Article\n" +
                                "(3) Inproceedings\n" +
                                "(4) Masterthesis\n" +
                                "(5) PHD Thesis\n" +
                                "(6) Techreport\n");
                        PublicationType publicationType = null;
                        int pubTypeChooser = Integer.parseInt(br.readLine());
                        switch (pubTypeChooser){
                            case 1:
                                publicationType = PublicationType.BOOK;
                                break;
                            case 2:
                                publicationType = PublicationType.ARTICLE;
                                break;
                            case 3:
                                publicationType = PublicationType.INPROCEEDINGS;
                                break;
                            case 4:
                                publicationType = PublicationType.MASTERSTHESIS;
                                break;
                            case 5:
                                publicationType = PublicationType.PHDTHESIS;
                                break;
                            case 6:
                                publicationType = PublicationType.TECHREPORT;
                                break;
                        }

                        ArrayList<String> pubAuthorsStringList = new ArrayList<String>();
                        System.out.println("Please enter the author of the publication: ");
                        String pubAuthorsString = br.readLine();
                        pubAuthorsStringList.add(pubAuthorsString);

                        boolean moreAuthors = true;
                        do {
                            System.out.println("Do you want to enter additional authors for the publication?(y/n)");
                            String yesOrNo = br.readLine();
                            switch (yesOrNo) {
                                case "y":
                                    System.out.println("Please enter another author of the publication: ");
                                    pubAuthorsString = br.readLine();
                                    pubAuthorsStringList.add(pubAuthorsString);
                                    break;
                                case "n":
                                    moreAuthors = false;
                                    break;
                            }
                        } while(moreAuthors);

                        Author pubAuthor = new Author();
                        ArrayList<Author> pubAuthors = new ArrayList<Author>();
                        for(int k = 0; k<pubAuthorsStringList.size(); k++) {
                            pubAuthor.setName(pubAuthorsStringList.get(k));
                            pubAuthors.add(pubAuthor);
                            k++;
                        }

                        System.out.println("Please enter the id of the publication: ");
                        String pubID = br.readLine();
                        while (!ValidationHelper.isId(pubID)) {
                            System.out.println("please enter a valid id");
                            pubID = br.readLine();
                        }



                        createdDatabase.addPublication(pubTitle,pubYear,publicationType,pubAuthors,pubID);

                        System.out.println("The publication " + pubTitle + " published in year " + pubYear + " of the type " + publicationType + " with the author(s) "+pubAuthorsStringList+" and the id "+pubID+" has been added to the database");

                        Publication publication = new Publication();
                        publication.setTitle(pubTitle);
                        publication.setYearPublished(pubYear);
                        publication.setType(publicationType);
                        publication.setAuthors(pubAuthors);
                        publication.setId(pubID);


                        for (i = 0; i < createdDatabase.getAuthors().size(); i++) {
                            if (createdDatabase.getAuthors().get(i).getId().contains(pubAuthorsString)) {
                                System.out.println("Author with the Name " + pubAuthorsString + "is already in the database. The Publication "+publication.getTitle()+" will be linked to the author.");
                                createdDatabase.getAuthors().get(i).getPublications().add(publication);

                            }
                        }
                        break;

                    case 4:
                        System.out.println("Please enter the id of the publication that should be removed ");
                        String removePubID = (br.readLine());
                        boolean found = false;

                        for (i = 0; i < createdDatabase.getPublications().size(); i++) {
                            if (createdDatabase.getPublications().get(i).getId().contains(removePubID)) {
                                System.out.println("Publication with the id " + removePubID + " has been found and removed from the database");
                                createdDatabase.removePublicationByID(removePubID);
                                found = true;
                            }
                        }
                        if (!found)
                            System.out.println("Publication with the id " + removePubID + " has not been found in the database");
                        break;
                    case 5:
                        System.out.println("Authors currently in Database: ");
                        System.out.println(createdDatabase.getAuthors().toString());
                        break;

                    case 6:
                        System.out.println("Publications currently in Database: ");
                        System.out.println(createdDatabase.getPublications().toString());
                        break;

                    case 7:
                        System.out.println("################# Currently Database: ################# ");
                        createdDatabase.printXMLToConsole();
                        System.out.println("#######################################################\n ");
                        break;

                    case 8:
                        System.out.println("Please enter with the XML-File to save the currently Database: ");
                        String saveToFile = br.readLine();
                        createdDatabase.saveXMLToFile(saveToFile);
                        System.out.println("The file " + saveToFile + " has been added in our the database");
                        break;

                    case 0:
                        Menu();


                }
            } while (option != 0);
        } catch (LiteratureDatabaseException e) {
            System.out.println("the following Error occurred: " + e.getMessage());

        } catch (IOException f) {
            System.out.println(MessageFormat.format("the following Error occurred: {0}", f.getMessage()));
        } finally {
            try {
                isr.close();
                br.close();
            } catch (IOException e) {
                System.out.println("the following Error occurred: " + e.getMessage());
            }
        }


    }



}


