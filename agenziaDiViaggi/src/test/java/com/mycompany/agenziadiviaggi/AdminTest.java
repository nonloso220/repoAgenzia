/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenziadiviaggi;

import exception.MaximumReached;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.*;

/**
 *
 * @author luca gabossi
 */
public class AdminTest {
    Admin a;
    User[] users;
    LocalDate startTravel;
    int numberUserPresent;
    String dsetination;
    @Before
    public void Inizializzazione() throws MaximumReached {
        a=new Admin("luca", "gabossi", "Gatto45", "lucagabossi@gmail.com",0);
        users=new User[100];
        users[0]=new User("luca", "gabossi", "Gatto45", 0, "lucagabossi@gmail.com");
        users[1]=new User("franco", "gabossi", "Franco45", 0, "francogabossi@gmail.com");
        users[2]=new User("gigo", "topo", "topolino90", 0, "topolino90@gmail.com");
        users[0].travelPlanning("france", 0, 12, 12, 2021, 20, 12, 2021);
        users[1].travelPlanning("london", 0, 12, 12, 2021, 20, 12, 2021);
        users[2].travelPlanning("france", 0, 12, 12, 2021, 20, 12, 2021);
        startTravel=LocalDate.of(2021, 12, 12);
        numberUserPresent=3;
        dsetination="france";
    }
    /**
     * Test of viewParticipantsInATravel method, of class Admin.
     */
    @Test
    public void testViewParticipantsInATravel() {
        String expected="name:\tsurname:\n"+"luca\tgabossi\ngigo\ttopo\n";
        String actual=a.viewParticipantsInATravel(users, numberUserPresent, startTravel, dsetination);
        assertEquals("visualizza in modo coretto i partecipanti al viaggio", expected, actual);
    }
    
}
