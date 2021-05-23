/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenziadiviaggi;

import exception.ItemNotFound;
import java.util.Scanner;

/**
 *
 * @author luca gabossi
 */
public class Menu {
    //attributi

    /**
     *ciao mondo
     */
    private String[] elencoVoci;
    private int numeroVoci;
    Scanner keyboard=new Scanner(System.in);
    //costruttore
    public Menu(String[] elencoVoci)
    {
        numeroVoci=elencoVoci.length;
        this.elencoVoci=new String[numeroVoci];
        for(int i=0;i<numeroVoci;i++)
            this.elencoVoci[i]=elencoVoci[i];
    }
    private void visualizzaMenu()
    {
        System.out.println("MENU:");
        for(int i=0;i<numeroVoci;i++)
            System.out.println(i+"---> "+this.elencoVoci[i]);
    }
    public int sceltaMenu()
    {
        /*
        METHOD RETURN RULES:
        se dopo la visualizzazione l'utente inserisce una stringa numerica valida ritorno il valore numerico(che verrà gestito nel main)
        */
        boolean inputUtenteOk=true;
        int sceltaUtente=0;
        do
        {
            inputUtenteOk=true;
            visualizzaMenu();
            System.out.println("scelta---> ");
            String s=keyboard.nextLine();
            for(int i=0;i<s.length();i++)
            {
                if(s.charAt(i)>=0 && s.charAt(i)<=9)
                    i++;
                else
                {
                    inputUtenteOk=false;
                    break;
                }
            }
            if(inputUtenteOk)
            {
                sceltaUtente=Integer.parseInt(s);
                if(sceltaUtente<0 || sceltaUtente>this.numeroVoci-1)
                    inputUtenteOk=false;
            }
            if(!inputUtenteOk)
            {
                System.out.println("errore");
                keyboard.nextLine();
            }
        }while(!inputUtenteOk);
        return sceltaUtente;
    }
    /**
     * 
     * @param numeroNonUsato:fghfhgfh
     * @return
        METHOD RETURN RULES:
        se dopo la visualizzazione l'utente inserisce una stringa non numerica un errore di -1 con il reinserimento
        se dopo la visualizzazione l'utente inserisce una stringa numerica non valida da un errore di -2 con il reinserimento
        se dopo la visualizzazione l'utente inserisce una stringa numerica valida ritorno il valore numerico(che verrà gestito nel main)
     */
    public int sceltaMenu(int numeroNonUsato) throws ItemNotFound
    {
        /*
        METHOD RETURN RULES:
        se dopo la visualizzazione l'utente inserisce una stringa numerica non valida da una eccezione con il reinserimento
        se dopo la visualizzazione l'utente inserisce una stringa numerica valida ritorno il valore numerico(che verrà gestito nel main)
        */
        int sceltaUtente=0;
        visualizzaMenu();
        sceltaUtente=InputControlls.inputAnalyzerInt("scelta", 0);
        if(sceltaUtente<0 || sceltaUtente>this.numeroVoci-1)
            throw new exception.ItemNotFound("scelta");
        else 
            return sceltaUtente;
    }
}
