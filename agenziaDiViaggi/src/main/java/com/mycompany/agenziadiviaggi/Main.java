/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenziadiviaggi;

import exception.MaximumReached;
import exception.NullPointer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luca gabossi
 */
public class Main {
    public static void main(String[] args) {
        Travel t=new Travel("san torini", 1, 1, 1, 1010, 2, 1, 1010);
        /*t.setTrueUserParty();
        t.setTrueUserReturned();*/
        User u=new User("franco", "gabossi", "1234", 0);
        try {
            u.travelPlanning(t);
        } catch (MaximumReached ex) {
            System.out.println(ex.toString());
        }
       
        /*try {
            u.setTrueUserParty(0);
        } catch (MaximumReached ex) {
            System.out.println(ex.toString());
        }
       /* try {
            u.setTrueUserReturned(0);
        } catch (MaximumReached ex) {
            System.out.println(ex.toString());
        }
       /* try {
            u.setTrueTravelPerformed(0);
        } catch (MaximumReached ex) {
            System.out.println(ex.toString());
        }*/
        
        try {
            System.out.println(u.showTravelsSortedByEntry());
        } catch (NullPointer ex) {
            System.out.println(ex.toString());
        }      
        try {
            System.out.println("\n"+u.showTravelSortedByDeparture());
        } catch (NullPointer ex) {
            System.out.println(ex.toString());
        }
        
        
    }
}
