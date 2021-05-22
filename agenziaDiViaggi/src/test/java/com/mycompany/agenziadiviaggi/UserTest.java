/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenziadiviaggi;
import exception.*;
import org.junit.*;

/**
 *
 * @author luca gabossi
 */
public class UserTest {
    User user;
    String nsmeFileTxt;
    String nsmeFileBinary;
    public UserTest() {
    }
    @Before
    public void Init(){
        nsmeFileTxt="fileExportedToUsersCSV.txt";
        nsmeFileBinary="binaryFileForUsers.bin";
    }
    /**
     * Test of travelPlanning method, of class User.
     */
    @Test
    public void testTravelPlanning() throws Exception {
        user.travelPlanning("frnace", 0, 12, 9, 2021, 20, 9, 2021);
        //Travel atteso=new Travel("frnace", 0, 12, 9, 2021, 20, 9, 2021);
        //Travel[] restituito=user.getTravelUser();
        //assert equals?? confronto l'array restituito in posizione 0 con l'atteso???
    }
    /**
     * Test of cancelATravel method, of class User.
     */
    @Test
    public void testCancelATravel() throws Exception {
        //String atteso=null;
        user.travelPlanning("frnace", 0, 12, 9, 2021, 20, 9, 2021);
        user.travelPlanning("dubai", 1, 12, 7, 2022, 20, 7, 2022);
        user.cancelATravel(0);
        //assert equals con null e quello in posizione 0??????
    }
    /**
     * Test of showTravelsSortedByEntry method, of class User.
     */
    @Test
    public void testShowTravelsSortedByEntry() throws Exception {
    }
    /**
     * Test of showTravelsSortedByDeparture method, of class User.
     */
    @Test
    public void testShowTravelsSortedByDeparture() throws Exception {
    }
    /**
     * Test of showDestinations method, of class User.
     */
    @Test
    public void testShowDestinations() {
    }
}
