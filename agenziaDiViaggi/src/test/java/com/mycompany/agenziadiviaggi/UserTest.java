/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenziadiviaggi;
import exception.MaximumReached;
import static org.junit.Assert.*;
import org.junit.*;

/**
 *
 * @author luca gabossi
 */
public class UserTest {
    User u;
    Travel expected;
    @Before
    public void Inizializzazione() throws MaximumReached {
        u=new User("luca", "gabossi", "Gatto45", 0, "lucagabossi@gmail.com");
        u.travelPlanning("france", 0, 12, 12, 2021, 20, 12, 2021);
    }
    /**
     * Test of travelPlanning method, of class User.
     */
    @Test
    public void testTravelPlanning() throws Exception {
        expected=new Travel("france", 0, 12, 12, 2021, 20, 12, 2021);
        Travel[] actual=u.getTravelUser();
        assertEquals("è stato inserito corettamente il viaggio", expected, actual[0]);
    }
    /**
     * Test of cancelATravel method, of class User.
     */
    @Test
    public void testCancelATravel() throws Exception {
        expected=null;
        int expected2=0;
        int actual2=u.cancelATravel(0);//restituisce 0 se viene cancellato in modo coretto
        Travel[] actual=u.getTravelUser();
        assertEquals("il viaggio è stato cancellato con successo", expected, actual[0]);
        assertEquals("il viaggio è stato cancellato con successo conferma", expected2,actual2);
    }
    /**
     * Test of showTravelsSortedByEntry method, of class User.
     */
    @Test
    public void testShowTravelsSortedByEntry() throws Exception {
        String expe="\nTRAVEL USER: \n"+"\n----------------------------------------"+"\ndestination: france"+"\ntravel id: 0"+"\nstart travel: 2021-12-12"+"\nend travel: 2021-12-20"+"\ncost: 200.0"+"\npaidTravel: no"+"\n----------------------------------------";
        String actual=u.showTravelsSortedByEntry();
        assertEquals("i viaggi dell'utente sono stati visualizzati in modo esatto", expe, actual);
    }
    /**
     * Test of showTravelsSortedByDeparture method, of class User.
     */
    @Test
    public void testShowTravelsSortedByDeparture() throws Exception {
        String expe="\nTRAVEL USER: \n"+"\n----------------------------------------"+"\ndestination: france"+"\ntravel id: 0"+"\nstart travel: 2021-12-12"+"\nend travel: 2021-12-20"+"\ncost: 200.0"+"\npaidTravel: no"+"\n----------------------------------------";
        String actual=u.showTravelsSortedByDeparture();
        assertEquals("i viaggi dell'utente sono stati visualizzati in modo esatto", expe, actual);
    }
    /**
     * Test of showDestinations method, of class User.
     */
    @Test
    public void testShowDestinations() throws Exception{
        String expe="\nDESTINATIONS OF YOUR TRAVELS:\n"+"france\n";
        String actual=u.showDestinations();
        assertEquals("le destinazioni sono visualizzate in modo corretto", expe, actual);
    }
}
