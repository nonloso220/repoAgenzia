/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenziadiviaggi;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author luca gabossi
 */
public class InputControlls {
    public static String inputAnalyzerString(String item,int length){
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
    public static int inputAnalyzerInt(String item,int length){
        String s;
        int n=0;
        boolean execution;
        do{
            s=inputAnalyzerString(item, length);
            try{
                n=Integer.parseInt(s);
                execution=true;
            }catch(NumberFormatException e){
                System.out.println("ERROR:invalid input insert because "+item+" contains letters");
                execution=false;
            }
        }while(!execution);
        return n;
    }
    public static String inputAnalyzerStringNominative(String item,int length){
        String s;
        boolean execution;
        do{
            s=inputAnalyzerString(item, length);
            if(s.matches("[a-zA-Z]+")==true){
                execution=true; 
            } else{
                System.out.println("ERROR:invalid input insert because "+item+" contains number or special characters");
                execution=false;
            }
        }while(!execution);
        return s;
    }
}
