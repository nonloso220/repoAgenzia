/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenziadiviaggi;

import exception.*;
import java.time.DateTimeException;
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
        int userChoice=0;
        int idTravel=0;
        int idUsers=0;
        int startDayOfMonths,startValueOfMonth,startYear,endDayOfMonths,endValueOfMonth,endYear;
        boolean dateCorrect;
        boolean c2=false;/*when user selected a case 2(generete an account and log into it) is true*/
        String[] s=new String[3];
        User[] users=new User[N_MAX_USERS];
        s[0]="exit the program";
        s[1]="log into an existing account";
        s[2]="create an account and log into it";
        String[] l=new String[7];
        l[0]="come back";
        l[1]="Travel planning";
        l[2]="cancel a travel";
        l[3]="show Travels Sorted By Entry";
        l[4]="show Travels Sorted By Departure";
        l[5]="postpone Travel";
        l[6]="delete the account";
        Menu mLogin=new Menu(s);
        Menu mUser=new Menu(l);
        //User u=new User("luca", "gabossi", "123", 0, "lucagabossis@gmail.com");
        //users[0]=new User(u);
        try{
            do{
                if(c2)
                    userChoice=1;
                else
                    userChoice=mLogin.sceltaMenu(0);
                switch(userChoice){
                    case 0:{
                        System.out.println("Bye-Bye!");
                        break;
                    }
                    case 1:{
                        if(numberUsersPresent==0){
                            System.out.println("Error: cannot perform this option because there are not even one users");
                            keyboard.nextLine();
                            break;
                        }  
                        int i=0;
                        String email = null;
                        String password = null;
                        if(!c2){
                            email=inputAnalyzer("email",2);
                            password=inputAnalyzer("account password",8);
                        }
                        for(i=0;i<numberUsersPresent;i++){
                            if(c2){
                                i=numberUsersPresent-1;
                                email=users[i].getEmail();
                                password=users[i].getPassword();
                            }
                            c2=false;
                            if(users[i].getEmail().equalsIgnoreCase(email)){
                                if(users[i].getPassword().equalsIgnoreCase(password)){
                                    int sceltaMenu01;
                                    do{
                                        sceltaMenu01=mUser.sceltaMenu(0);
                                        try{
                                            switch(sceltaMenu01){
                                                case 0:{/*come back*/
                                                    break;
                                                }
                                                case 1:{/*Travel planning*/
                                                    if(users[i].getN_MAX_TRAVELS()==users[i].getNumTravelPresent()){
                                                        System.out.println("maximum number of trips reached");
                                                        break;
                                                    }
                                                    int id=idTravel;
                                                    System.out.println("destination: ");
                                                    String destination=keyboard.nextLine();
                                                    do{
                                                        startDayOfMonths=Integer.valueOf(inputAnalyzer("startDayOfMonths",1));//CONTROLLO INSERIMENTO INTERO 
                                                        System.out.println("startValueOfMonth: ");
                                                        startValueOfMonth=Integer.valueOf(inputAnalyzer("startValueOfMonth",1));//CONTROLLO INSERIMENTO INTERO 
                                                        System.out.println("startYear: ");
                                                        startYear=Integer.valueOf(inputAnalyzer("startYear",1));//CONTROLLO INSERIMENTO INTERO 
                                                        dateCorrect=isDataValida(startDayOfMonths, startValueOfMonth, startYear);
                                                        if(dateCorrect)
                                                            break;
                                                        else{
                                                            System.out.println("ERROR: invalid date");
                                                        }
                                                    }while(!dateCorrect);
                                                    do{
                                                        System.out.println("endDayOfMonths: ");//CONTROLLO INSERIMENTO INTERO 
                                                        endDayOfMonths=keyboard.nextInt();
                                                        System.out.println("endValueOfMonth: ");//CONTROLLO INSERIMENTO INTERO 
                                                        endValueOfMonth=keyboard.nextInt();
                                                        System.out.println("endYear: ");//CONTROLLO INSERIMENTO INTERO 
                                                        endYear=keyboard.nextInt();
                                                        dateCorrect=isDataValida(endDayOfMonths, endValueOfMonth, endYear);
                                                        if(dateCorrect)
                                                            break;
                                                        else{
                                                            System.out.println("ERROR: invalid date");
                                                        }
                                                    }while(!dateCorrect);
                                                    try {
                                                        users[i].travelPlanning(destination, id, startDayOfMonths, startValueOfMonth, startYear, endDayOfMonths, endValueOfMonth, endYear);
                                                        System.out.println("the travel ID is: "+id+"\n");
                                                        System.out.println("Successful operation, press any key to continue");
                                                        keyboard.nextLine();
                                                        keyboard.nextLine();
                                                        idTravel++;
                                                    } catch (MaximumReached ex) {
                                                        System.out.println(ex.toString());
                                                    } catch (travelsNotFound ex) {
                                                        System.out.println(ex.toString());
                                                    }catch(DateTimeException ex){
                                                        System.out.println("Error: the date is invalid");
                                                        keyboard.nextLine();
                                                        keyboard.nextLine();
                                                    }
                                                    break;
                                                }
                                                case 2:{/*cancel a travel*/
                                                    if(users[i].getNumTravelPresent()==0){
                                                        System.out.println("unable to perform this method, enter a trip first");
                                                        break;
                                                    }
                                                    int id=Integer.valueOf(inputAnalyzer("enter the ID of the trip you want to delete",1));//CONTROLLO INSERIMENTO INTERO 
                                                    try {
                                                        users[i].cancelATravel(id);
                                                        System.out.println("Successful operation, press any key to continue");
                                                        keyboard.nextLine();
                                                        keyboard.nextLine();
                                                    } catch (ItemNotFound ex) {
                                                        System.out.println(ex.toString());
                                                    } catch (travelsNotFound ex) {
                                                        System.out.println(ex.toString());
                                                    }
                                                    break;
                                                }
                                                case 3:{/*show Travels Sorted By Entry*/
                                                    if(users[i].getNumTravelPresent()==0){
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
                                                case 4:{/*show Travels Sorted By Departure*/
                                                    if(users[i].getNumTravelPresent()==0){
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
                                                case 5:{/*postpone Travel*/
                                                    if(users[i].getNumTravelPresent()==0){
                                                        System.out.println("unable to perform this method, enter a trip first");
                                                        break;
                                                    }
                                                    int id=Integer.valueOf(inputAnalyzer("enter the ID of the trip you want to postpone",1));
                                                    do{
                                                        System.out.println("startDayOfMonths: ");//CONTROLLO INSERIMENTO INTERO 
                                                        startDayOfMonths=keyboard.nextInt();
                                                        System.out.println("startValueOfMonth: ");//CONTROLLO INSERIMENTO INTERO 
                                                        startValueOfMonth=keyboard.nextInt();
                                                        System.out.println("startYear: ");//CONTROLLO INSERIMENTO INTERO 
                                                        startYear=keyboard.nextInt();
                                                        dateCorrect=isDataValida(startDayOfMonths, startValueOfMonth, startYear);
                                                        if(dateCorrect)
                                                            break;
                                                        else{
                                                            System.out.println("ERROR: invalid date");
                                                        }
                                                    }while(!dateCorrect);
                                                    do{
                                                        System.out.println("endDayOfMonths: ");//CONTROLLO INSERIMENTO INTERO 
                                                        endDayOfMonths=keyboard.nextInt();
                                                        System.out.println("endValueOfMonth: ");//CONTROLLO INSERIMENTO INTERO 
                                                        endValueOfMonth=keyboard.nextInt();
                                                        System.out.println("endYear: ");//CONTROLLO INSERIMENTO INTERO 
                                                        endYear=keyboard.nextInt();
                                                        dateCorrect=isDataValida(endDayOfMonths, endValueOfMonth, endYear);
                                                        if(dateCorrect)
                                                            break;
                                                        else{
                                                            System.out.println("ERROR: invalid date");
                                                        }
                                                    }while(!dateCorrect);
                                                    try {
                                                        users[i].postponeTravel(id, startDayOfMonths, startValueOfMonth, startYear, endDayOfMonths, endValueOfMonth, endYear);
                                                        System.out.println("\nSuccessful operation, press any key to continue");
                                                        keyboard.nextLine();
                                                    } catch (ItemNotFound ex) {
                                                        System.out.println(ex.toString());
                                                    } catch (travelsNotFound ex) {
                                                        System.out.println(ex.toString());
                                                    }catch(DateTimeException ex){
                                                        System.out.println("Error: the date is invalid");
                                                        keyboard.nextLine();
                                                        keyboard.nextLine();
                                                    }
                                                    break;
                                                }
                                                case 6:{/*delete the account*/
                                                    System.out.println("you are sure to delete this account once deleted you will not be able to go back, enter Y / N:");
                                                    String safety=keyboard.nextLine();
                                                    if(safety.compareToIgnoreCase("n")==0){
                                                        System.out.println("no problem, rest assured I have not deleted your account, be careful next time. ;P");
                                                        keyboard.nextLine();
                                                        break;
                                                    }  
                                                    System.out.println("enter the password to confirm: ");
                                                    String passwordConfirm;
                                                    int ci=0;
                                                    do{
                                                        passwordConfirm=keyboard.nextLine();
                                                        do{
                                                            if(passwordConfirm.length()<3){
                                                            System.out.println("ERROR: the password cannot be less than 3 characters re-enter the password: ");
                                                            }
                                                        }while(passwordConfirm.length()<3);
                                                        if(passwordConfirm.compareTo(users[i].getPassword())!=0){
                                                            ci++;
                                                            System.out.println("you made a mistake the password does not match, try to re-enter the password.");
                                                            keyboard.nextLine();
                                                            if(ci==3){
                                                                System.out.println("you are sure to delete this account once deleted you will not be able to go back, enter Y / N:");
                                                                safety=keyboard.nextLine();
                                                                ci=0;
                                                                if(safety.compareToIgnoreCase("n")==0){
                                                                    System.out.println("no problem, rest assured I have not deleted your account, be careful next time. ;P");
                                                                    keyboard.nextLine();
                                                                    break;
                                                                }
                                                            }
                                                            if(safety.compareToIgnoreCase("y")==0)
                                                                System.out.println("you still have "+(3-ci)+"attempts, enter the password to confirm: ");
                                                        }
                                                    }while(passwordConfirm.compareTo(users[i].getPassword())!=0);
                                                    for(int j=i;j<numberUsersPresent-1;j++){
                                                        users[j]=users[j+1];
                                                    }
                                                    users[numberUsersPresent-1]=null;
                                                    numberUsersPresent--;
                                                    sceltaMenu01=0;
                                                    System.out.println("\nSuccessful operation, press any key to continue");
                                                    keyboard.nextLine();
                                                    break;
                                                }
                                            }
                                        }catch(InputMismatchException | NumberFormatException e1){
                                            System.out.println("incorrect input, press any key to continue");
                                            keyboard.nextLine();
                                        }
                                    }while(sceltaMenu01!=0);
                                }
                                else{
                                    System.out.println("Error: check password may be incorrect");
                                    keyboard.nextLine();
                                    break;
                                }
                            } 
                        }
                        break;
                    }
                    case 2:{
                        
                        String name=inputAnalyzer("name",3);
                        String surname=inputAnalyzer("surname",1);
                        String email=inputAnalyzer("email",2);
                        String password=inputAnalyzer("password",8);
                        int id=idUsers;
                        users[numberUsersPresent]=new User(name, surname, password, id, email);
                        idUsers++;
                        numberUsersPresent++;
                        c2=true;
                        break;
                    }
                }
            }while(userChoice!=0);
        }catch(InputMismatchException | NumberFormatException e1){
            System.out.println("incorrect input, press any key to continue");
            keyboard.nextLine();
        }
    }
    private static String inputAnalyzer(String item,int length){
        Scanner keyboard=new Scanner(System.in);
        System.out.println(item+": ");
        String s=keyboard.nextLine();
        do{
           if(s.length()==0){
               System.out.println("ERROR: invalid input insert something before sending, re-enter "+item+": ");
               s=keyboard.nextLine();
           }
           if(s.length()<length){
               System.out.println("ERROR: invalid input because "+item+" length is less than "+length+", re-enter "+item+": ");
               s=keyboard.nextLine();
           }
        }while(s.length()==0 || s.length()<length);
        return s;
    }
    private static boolean isDataValida(int giorno, int mese, int anno)
    {
        if(giorno<0 || giorno>31)
            return false;
        if(mese<0 || mese>12)
            return false;
        if(anno<0 || anno>9999)
            return false;
        else
            return true;
    }
}
