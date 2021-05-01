/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenziadiviaggi;

import exception.*;
import java.util.Scanner;

/**
 *
 * @author luca gabossi
 */
public class Main {
    public static void main(String[] args) {
        Scanner keyboard=new Scanner(System.in);
        int N_MAX_USERS=100;
        int numberUsersPresent=1;
        int userChoice=0;
        int idTravel=0;
        int idUsers=0;
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
        User u=new User("luca", "gabossi", "123", 0, "lucagabossis@gmail.com");
        users[0]=new User(u);
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
                        System.out.println("email: ");
                        email=keyboard.nextLine();
                        System.out.println("account password: ");
                        password=keyboard.nextLine();
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
                                    switch(sceltaMenu01){
                                        case 0:{/*come back*/
                                            break;
                                        }
                                        case 1:{/*Travel planning*/
                                            int id=idTravel;
                                            System.out.println("the travel ID is: "+id+"\n");
                                            System.out.println("destination: ");
                                            String destination=keyboard.nextLine();
                                            System.out.println("startDayOfMonths: ");
                                            int startDayOfMonths=keyboard.nextInt(); 
                                            System.out.println("startValueOfMonth: ");
                                            int startValueOfMonth=keyboard.nextInt();
                                            System.out.println("startYear: ");
                                            int startYear=keyboard.nextInt();
                                            System.out.println("endDayOfMonths: ");
                                            int endDayOfMonths=keyboard.nextInt();
                                            System.out.println("endValueOfMonth: ");
                                            int endValueOfMonth=keyboard.nextInt();
                                            System.out.println("endYear: ");
                                            int endYear=keyboard.nextInt();
                                            try {
                                                users[i].travelPlanning(destination, id, startDayOfMonths, startValueOfMonth, startYear, endDayOfMonths, endValueOfMonth, endYear);
                                                System.out.println("Successful operation, press any key to continue");
                                                keyboard.nextLine();
                                                keyboard.nextLine();
                                                idTravel++;
                                            } catch (MaximumReached ex) {
                                                System.out.println(ex.toString());
                                            } catch (travelsNotFound ex) {
                                                System.out.println(ex.toString());
                                            }
                                            break;
                                        }
                                        case 2:{/*cancel a travel*/
                                            System.out.println("enter the ID of the trip you want to delete: ");
                                            int id=keyboard.nextInt();
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
                                            System.out.println("enter the ID of the trip you want to delete: ");
                                            int id=keyboard.nextInt();
                                            System.out.println("startDayOfMonths: ");
                                            int startDayOfMonths=keyboard.nextInt(); 
                                            System.out.println("startValueOfMonth: ");
                                            int startValueOfMonth=keyboard.nextInt();
                                            System.out.println("startYear: ");
                                            int startYear=keyboard.nextInt();
                                            System.out.println("endDayOfMonths: ");
                                            int endDayOfMonths=keyboard.nextInt();
                                            System.out.println("endValueOfMonth: ");
                                            int endValueOfMonth=keyboard.nextInt();
                                            System.out.println("endYear: ");
                                            int endYear=keyboard.nextInt();
                                            try {
                                                users[i].postponeTravel(id, startDayOfMonths, startValueOfMonth, startYear, endDayOfMonths, endValueOfMonth, endYear);
                                                System.out.println("\nSuccessful operation, press any key to continue");
                                                keyboard.nextLine();
                                            } catch (ItemNotFound ex) {
                                                System.out.println(ex.toString());
                                            } catch (travelsNotFound ex) {
                                                System.out.println(ex.toString());
                                            }
                                            break;
                                        }
                                        case 6:{/*delete the account*/
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
                    System.out.println("name:");
                    String name=keyboard.nextLine();
                    System.out.println("surname:");
                    String surname=keyboard.nextLine();
                    System.out.println("email:");
                    String email=keyboard.nextLine();
                    System.out.println("password:");
                    String password=keyboard.nextLine();
                    int id=idUsers;
                    users[numberUsersPresent]=new User(name, surname, password, id, email);
                    idUsers++;
                    numberUsersPresent++;
                    c2=true;
                    break;
                }
            }
        }while(userChoice!=0);
        
    }
}
