/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenziadiviaggi;

import exception.*;
import file.TextFile;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author luca gabossi
 */
public class Main {

    public static void main(String[] args) {
        Scanner keyboard=new Scanner(System.in);
        int N_MAX_USERS=100;
        int numberUsersPresent=0;
        int userChoice=-1;
        int idTravel=0;
        int idUsers=0;
        int startDayOfMonths, startValueOfMonth, startYear, endDayOfMonths, endValueOfMonth, endYear;
        boolean dateCorrect;
        boolean c2=false;/*when user selected a case 2(generete an account and log into it) is true*/
        String nsmeFileTxt="file-exported-to-users-CSV.txt";
        String nsmeFileBinary="binary-file-for-users.bin";
        String[] s=new String[5];
        User[] users=new User[N_MAX_USERS];
        s[0]="exit the program";
        s[1]="log into an existing account";
        s[2]="create an account and log into it";
        s[3]="exports users to CSV files";
        s[4]="save to binary file";
        String[] l = new String[8];
        l[0]="come back";
        l[1]="Travel planning";
        l[2]="cancel a travel";
        l[3]="show Travels Sorted By Entry";
        l[4]="show Travels Sorted By Departure";
        l[5]="show destinations";
        l[6]="postpone Travel";
        l[7]="delete the account";
        String[] k = new String[2];
        k[0]="come back";
        k[1]="View the list of participants in a particular trip in alphabetical order by entering the date and destination";
        Menu mLogin=new Menu(s);
        Menu mUser=new Menu(l);
        Menu mAdmin=new Menu(k);
        Admin admin=new Admin("admin", "admin", "Coniglio", "admin.Agency@Yahoo.com", 101);
        try{ 
            FileInputStream f1=new FileInputStream(nsmeFileBinary);
            ObjectInputStream reader=new ObjectInputStream(f1); 
            try{
                idTravel=(int)reader.readInt();//chiedi al prof
                idUsers=(int)reader.readInt();//chiedi al prof
                numberUsersPresent=(int)reader.readInt();//chiedi al prof
                users=(User[])reader.readObject();
                reader.close();
            }catch (ClassNotFoundException ex){
                reader.close();
                System.out.println("Error: unable to read file, press any key to continue");
                keyboard.nextLine();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error: cannot find the file, press any key to continue");
            keyboard.nextLine();
        } catch (IOException ex) {
            System.out.println("Error: unable to open file, press any key to continue");
            keyboard.nextLine();
        }
        try {
            do {
                if (c2) {
                    userChoice = 1;
                } else {
                    try {
                        userChoice = mLogin.sceltaMenu(0);
                    } catch (ItemNotFound ex) {
                        System.out.println(ex.toString() + " press any key to continue");
                        keyboard.nextLine();
                    }
                }
                switch (userChoice) {
                    case 0: {
                        System.out.println("Bye-Bye!");
                        break;
                    }
                    case 1: {
                        int i = 0;
                        String email = null;
                        String password = null;
                        if (!c2) {
                            email = InputControlls.inputAnalyzerString("email", 2);
                            password = InputControlls.inputAnalyzerString("account password", 8);
                        }
                        if(admin.getEmail().equalsIgnoreCase(email)){
                            int choiceMenu02=-1;
                            do{
                                 try {
                                        choiceMenu02 = mAdmin.sceltaMenu(0);
                                    } catch (ItemNotFound ex) {
                                        System.out.println(ex.toString() + " press any key to continue");
                                    }
                                switch(choiceMenu02){
                                    case 0:{
                                        break;
                                    }
                                    case 1:{
                                        if(numberUsersPresent==0){
                                            System.out.println("no user present");
                                            keyboard.nextLine();
                                            break;
                                        }
                                        System.out.println("destination: ");
                                        String destination = keyboard.nextLine();
                                        do {
                                            startDayOfMonths = InputControlls.inputAnalyzerInt("startDayOfMonths", 1);
                                            startValueOfMonth = InputControlls.inputAnalyzerInt("startValueOfMonth", 1);
                                            startYear = InputControlls.inputAnalyzerInt("startYear", 1);
                                            dateCorrect = isDataValida(startDayOfMonths, startValueOfMonth, startYear);
                                            if (dateCorrect) {
                                                break;
                                            } else {
                                                System.out.println("ERROR: invalid date");
                                            }
                                        } while (!dateCorrect);
                                        LocalDate date=LocalDate.of(startYear, startValueOfMonth, startDayOfMonths);
                                        System.out.println(admin.viewParticipantsInATravel(users, numberUsersPresent,date, destination));
                                        break;
                                    }
                                }
                            }while(choiceMenu02 != 0); 
                            break;
                        }
                        else if(numberUsersPresent!=0){
                            for (i = 0; i < numberUsersPresent; i++) {
                                if (c2) {
                                    i = numberUsersPresent - 1;
                                    email = users[i].getEmail();
                                    password = users[i].getPassword();
                                }
                                c2 = false;
                                if (users[i].getEmail().equalsIgnoreCase(email)) {
                                    if (users[i].getPassword().equalsIgnoreCase(password)) {
                                        int choiceMenu01 = -1;
                                        do {
                                            try {
                                                choiceMenu01 = mUser.sceltaMenu(0);
                                            } catch (ItemNotFound ex) {
                                                System.out.println(ex.toString() + " press any key to continue");
                                            }
                                            try {
                                                switch (choiceMenu01) {
                                                    case 0: {/*come back*/
                                                        break;
                                                    }
                                                    case 1: {/*Travel planning*/
                                                        if (users[i].getN_MAX_TRAVELS() == users[i].getNumTravelPresent()) {
                                                            System.out.println("maximum number of trips reached");
                                                            break;
                                                        }
                                                        int id = idTravel;
                                                        System.out.println("destination: ");
                                                        String destination = keyboard.nextLine();
                                                        do {
                                                            startDayOfMonths = InputControlls.inputAnalyzerInt("startDayOfMonths", 1);
                                                            startValueOfMonth = InputControlls.inputAnalyzerInt("startValueOfMonth", 1);
                                                            startYear = InputControlls.inputAnalyzerInt("startYear", 1);
                                                            dateCorrect = isDataValida(startDayOfMonths, startValueOfMonth, startYear);
                                                            if (dateCorrect) {
                                                                break;
                                                            } else {
                                                                System.out.println("ERROR: invalid date");
                                                            }
                                                        } while (!dateCorrect);
                                                        do {
                                                            endDayOfMonths = InputControlls.inputAnalyzerInt("endDayOfMonths", 1);
                                                            endValueOfMonth = InputControlls.inputAnalyzerInt("endValueOfMonth", 1);
                                                            endYear = InputControlls.inputAnalyzerInt("endYear", 1);
                                                            dateCorrect = isDataValida(endDayOfMonths, endValueOfMonth, endYear);
                                                            if (dateCorrect) {
                                                                break;
                                                            } else {
                                                                System.out.println("ERROR: invalid date");
                                                            }
                                                        } while (!dateCorrect);
                                                        try {
                                                            users[i].travelPlanning(destination, id, startDayOfMonths, startValueOfMonth, startYear, endDayOfMonths, endValueOfMonth, endYear);
                                                            System.out.println("the travel ID is: " + id);
                                                            System.out.println("Successful operation, press any key to continue");
                                                            keyboard.nextLine();
                                                            idTravel++;
                                                        } catch (MaximumReached ex) {
                                                            System.out.println(ex.toString());
                                                        } catch (travelsNotFound ex) {
                                                            System.out.println(ex.toString());
                                                        } catch (DateTimeException ex) {
                                                            System.out.println("Error: the date is invalid");
                                                            keyboard.nextLine();
                                                        }
                                                        break;
                                                    }
                                                    case 2: {/*cancel a travel*/
                                                        if (users[i].getNumTravelPresent() == 0) {
                                                            System.out.println("unable to perform this method, enter a trip first");
                                                            break;
                                                        }
                                                        try {
                                                            System.out.println(users[i].showTravelsSortedByEntry());
                                                        } catch (NullPointer ex) {
                                                            System.out.println(ex.toString());
                                                        } catch (travelsNotFound ex) {
                                                            System.out.println(ex.toString());
                                                        }
                                                        int id = InputControlls.inputAnalyzerInt("enter the ID of the trip you want to delete", 1);
                                                        try {
                                                            users[i].cancelATravel(id);
                                                            System.out.println("Successful operation, press any key to continue");
                                                            keyboard.nextLine();
                                                        } catch (ItemNotFound ex) {
                                                            System.out.println(ex.toString());
                                                        } catch (travelsNotFound ex) {
                                                            System.out.println(ex.toString());
                                                        }
                                                        break;
                                                    }
                                                    case 3: {/*show Travels Sorted By Entry*/
                                                        if (users[i].getNumTravelPresent() == 0) {
                                                            System.out.println("unable to perform this method, enter a trip first");
                                                            break;
                                                        }
                                                        try {
                                                            System.out.println(users[i].showTravelsSortedByEntry());
                                                            System.out.println("\nSuccessful operation, press any key to continue");
                                                            keyboard.nextLine();
                                                        } catch (NullPointer ex) {
                                                            System.out.println(ex.toString());
                                                        } catch (travelsNotFound ex) {
                                                            System.out.println(ex.toString());
                                                        }
                                                        break;
                                                    }
                                                    case 4: {/*show Travels Sorted By Departure*/
                                                        if (users[i].getNumTravelPresent() == 0) {
                                                            System.out.println("unable to perform this method, enter a trip first");
                                                            break;
                                                        }
                                                        try {
                                                            System.out.println(users[i].showTravelsSortedByDeparture());
                                                            System.out.println("\nSuccessful operation, press any key to continue");
                                                            keyboard.nextLine();
                                                        } catch (NullPointer ex) {
                                                            System.out.println(ex.toString());
                                                        } catch (travelsNotFound ex) {
                                                            System.out.println(ex.toString());
                                                        }
                                                        break;
                                                    }
                                                    case 5: {/*show destination*/
                                                        if (users[i].getNumTravelPresent() == 0) {
                                                            System.out.println("unable to perform this method, enter a trip first");
                                                            break;
                                                        }
                                                        System.out.println(users[i].showDestinations());
                                                        break;
                                                    }
                                                    case 6: {/*postpone Travel*/
                                                        if (users[i].getNumTravelPresent() == 0) {
                                                            System.out.println("unable to perform this method, enter a trip first");
                                                            break;
                                                        }
                                                        int id = InputControlls.inputAnalyzerInt("enter the ID of the trip you want to postpone", 1);
                                                        do {
                                                            startDayOfMonths = InputControlls.inputAnalyzerInt("startDayOfMonths", 1);
                                                            startValueOfMonth = InputControlls.inputAnalyzerInt("startValueOfMonth", 1);
                                                            startYear = InputControlls.inputAnalyzerInt("startYear", 1);
                                                            dateCorrect = isDataValida(startDayOfMonths, startValueOfMonth, startYear);
                                                            if (dateCorrect) {
                                                                break;
                                                            } else {
                                                                System.out.println("ERROR: invalid date");
                                                            }
                                                        } while (!dateCorrect);
                                                        do {
                                                            endDayOfMonths = InputControlls.inputAnalyzerInt("endDayOfMonths: ", 1);
                                                            endValueOfMonth = InputControlls.inputAnalyzerInt("endValueOfMonth: ", 1);
                                                            endYear = InputControlls.inputAnalyzerInt("endYear: ", 1);
                                                            dateCorrect = isDataValida(endDayOfMonths, endValueOfMonth, endYear);
                                                            if (dateCorrect) {
                                                                break;
                                                            } else {
                                                                System.out.println("ERROR: invalid date");
                                                            }
                                                        } while (!dateCorrect);
                                                        try {
                                                            users[i].postponeTravel(id, startDayOfMonths, startValueOfMonth, startYear, endDayOfMonths, endValueOfMonth, endYear);
                                                            System.out.println("\nSuccessful operation, press any key to continue");
                                                            keyboard.nextLine();
                                                        } catch (ItemNotFound ex) {
                                                            System.out.println(ex.toString());
                                                        } catch (travelsNotFound ex) {
                                                            System.out.println(ex.toString());
                                                        } catch (DateTimeException ex) {
                                                            System.out.println("Error: the date is invalid");
                                                            keyboard.nextLine();
                                                        }
                                                        break;
                                                    }
                                                    case 7: {/*delete the account*/
                                                        System.out.println("you are sure to delete this account once deleted you will not be able to go back, enter Y / N:");
                                                        String safety = keyboard.nextLine();
                                                        if (safety.compareToIgnoreCase("n") == 0) {
                                                            System.out.println("no problem, rest assured I have not deleted your account, be careful next time. ;P");
                                                            keyboard.nextLine();
                                                            break;
                                                        }
                                                        System.out.println("enter the password to confirm: ");
                                                        String passwordConfirm;
                                                        int ci = 0;
                                                        do {
                                                            passwordConfirm = keyboard.nextLine();
                                                            do {
                                                                if (passwordConfirm.length() < 3) {
                                                                    System.out.println("ERROR: the password cannot be less than 3 characters re-enter the password: ");
                                                                }
                                                            } while (passwordConfirm.length() < 3);
                                                            if (passwordConfirm.compareTo(users[i].getPassword()) != 0) {
                                                                ci++;
                                                                System.out.println("you made a mistake the password does not match, try to re-enter the password.");
                                                                keyboard.nextLine();
                                                                if (ci == 3) {
                                                                    System.out.println("you are sure to delete this account once deleted you will not be able to go back, enter Y / N:");
                                                                    safety = keyboard.nextLine();
                                                                    ci = 0;
                                                                    if (safety.compareToIgnoreCase("n") == 0) {
                                                                        System.out.println("no problem, rest assured I have not deleted your account, be careful next time. ;P");
                                                                        keyboard.nextLine();
                                                                        break;
                                                                    }
                                                                }
                                                                if (safety.compareToIgnoreCase("y") == 0) {
                                                                    System.out.println("you still have " + (3 - ci) + "attempts, enter the password to confirm: ");
                                                                }
                                                            }
                                                        } while (passwordConfirm.compareTo(users[i].getPassword()) != 0);
                                                        for (int j = i; j < numberUsersPresent - 1; j++) {
                                                            users[j] = users[j + 1];
                                                        }
                                                        users[numberUsersPresent - 1] = null;
                                                        numberUsersPresent--;
                                                        choiceMenu01 = 0;
                                                        System.out.println("\nSuccessful operation, press any key to continue");
                                                        keyboard.nextLine();
                                                        break;
                                                    }
                                                }
                                            } catch (InputMismatchException | NumberFormatException e1) {
                                                System.out.println("incorrect input, press any key to continue");
                                                keyboard.nextLine();
                                            }
                                        } while (choiceMenu01 != 0);
                                    } else {
                                        System.out.println("Error: check password may be incorrect");
                                        keyboard.nextLine();
                                        break;
                                    }
                                }
                                else{
                                    System.out.println("no accounts found");
                                    keyboard.nextLine();
                                    break;
                                }
                            }
                        } 
                        break;
                    }
                    case 2: {
                        String name = InputControlls.inputAnalyzerString("name", 3);
                        String surname = InputControlls.inputAnalyzerString("surname", 1);
                        String email = InputControlls.inputAnalyzerString("email", 2);
                        email = controlEmail(numberUsersPresent, users, email);
                        String password = InputControlls.inputAnalyzerString("password", 8);
                        int id = idUsers;
                        users[numberUsersPresent] = new User(name, surname, password, id, email);
                        idUsers++;
                        numberUsersPresent++;
                        c2 = true;
                        break;
                    }
                    case 3: {
                        try {
                            Main.salvaClassInFileCSV(nsmeFileTxt, numberUsersPresent, users);
                            System.out.println("ok");
                        } catch (IOException ex) {
                            System.out.println(ex.toString());
                        } catch (FileException ex) {
                            System.out.println(ex.toString());
                            keyboard.nextLine();
                        }
                    }
                    case 4: {
                        FileOutputStream f1;
                        try {
                            f1 = new FileOutputStream(nsmeFileBinary);
                            ObjectOutputStream writer=new ObjectOutputStream(f1);
                            writer.writeInt(idTravel);
                            writer.writeInt(idUsers);
                            writer.writeInt(numberUsersPresent);
                            writer.writeObject(users);
                            writer.flush();
                            writer.close();
                            System.out.println("\nSuccessful operation, press any key to continue");
                            keyboard.nextLine();
                        } catch(FileNotFoundException ex) {
                            System.out.println(ex.toString());
                        } catch(IOException ex) {
                            System.out.println(ex.toString());
                        }
                    }
                }
            } while (userChoice != 0);
        } catch (InputMismatchException | NumberFormatException e1) {
            System.out.println("incorrect input, press any key to continue");
            keyboard.nextLine();
        }
    }
    private static boolean isDataValida(int giorno, int mese, int anno) {
        if(giorno<0 || giorno>31) {
            return false;
        }
        if(mese<0 || mese>12) {
            return false;
        }
        if(anno<0 || anno>9999) {
            return false;
        } else {
            return true;
        }
    }
    public static void salvaClassInFileCSV(String nomeFile, int numberUsersPresent, User users[]) throws IOException, FileException {
        if(numberUsersPresent != 0){
            TextFile f1=new TextFile('W',nomeFile);
            for(int i=0;i<numberUsersPresent;i++) {
                if(users[i]!=null){
                    f1.toFile(users[i].getId()+";"+users[i].getName()+";"+users[i].getSurname()+";"+users[i].getEmail()+";"+users[i].getPassword()+";");
                }
            }
            f1.toFile(numberUsersPresent+";");
            f1.close();
        } else {
            throw new exception.FileException("no user present, press any key to continue");
        }
    }
    private static String controlEmail(int numberUsersPresent, User users[], String email) {
        for (int i=0;i<numberUsersPresent;i++) {
            if(email.compareToIgnoreCase(users[i].getEmail())==0) {
                System.out.println("Error: invalid email, re-enter");
                email=InputControlls.inputAnalyzerString("email", 2);
                email=controlEmail(numberUsersPresent, users, email);
                return email;
            }
        }
        return email;
    }
}