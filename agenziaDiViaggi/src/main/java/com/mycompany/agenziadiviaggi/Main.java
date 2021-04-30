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
        int sceltaUtente=0;
        String[] s=new String[3];
        User[] users=new User[N_MAX_USERS];
        s[0]="exit the program";
        s[1]="log into an existing account";
        s[2]="create an account and log into it";
        String[] l=new String[6];
        l[0]="come back";
        l[1]="Travel planning";
        l[2]="cancel a travel";
        l[3]="show Travels Sorted By Entry";
        l[4]="show Travels Sorted By Departure";
        l[5]="postpone Travel";
        Menu mLogin=new Menu(s);
        Menu mUser=new Menu(l);
        
        User u=new User("luca", "gabossi", "123", 0, "lucagabossis@gmail.com");
        users[0]=new User(u);
        do{
            sceltaUtente=mLogin.sceltaMenu(0);
            switch(sceltaUtente){
                case 0:{
                    System.out.println("ciao");
                    break;
                }
                case 1:{
                    if(numberUsersPresent==0){
                        System.out.println("Error: cannot perform this option because there are not even one users");
                        keyboard.nextLine();
                        break;
                    }    
                    System.out.println("email: ");
                    String email=keyboard.nextLine();
                    System.out.println("account password: ");
                    String password=keyboard.nextLine();
                    for(int i=0;i<numberUsersPresent;i++){
                        if(users[i].getEmail().equalsIgnoreCase(email)){
                            if(users[i].getPassword().equalsIgnoreCase(password)){
                                int sceltaMenu01;
                                do{
                                    sceltaMenu01=mUser.sceltaMenu(0);
                                    switch(sceltaMenu01){
                                        case 0:{
                                            break;
                                        }
                                        case 1:{
                                            
                                            break;
                                        }
                                        case 2:{
                                            break;
                                        }
                                        case 3:{
                                            break;
                                        }
                                        case 4:{
                                            break;
                                        }
                                        case 5:{
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
                    
                    break;
                }
            }
        }while(sceltaUtente!=0);
        
    }
}
