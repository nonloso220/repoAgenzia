/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenziadiviaggi;

import exception.*;

/**
 *
 * @author luca gabossi
 * 
 * 
 * 
 * 
 */
public class User {
    private String name;
    private String surname;
    private String password;
    private int id;
    private int numTravelPresent=0;/*counter travel*/
    private Travel[] travelUser;/*array travel*/
    private final int N_MAX_TRAVELS=20;/*maximum number of travels that can be entered*/
    /**
     * 
     * @param name
     * @param surname
     * @param password
     * @param id 
     */
    public User(String name, String surname, String password, int id)/*first costructor*/{
        setName(name);
        setSurname(surname);
        setPassword(password);
        setId(id);
        this.travelUser=new Travel[N_MAX_TRAVELS];/*generated a series(array) of travelUser*/
    }
    /**
     * 
     * @param u 
     */
    public User(User u)/*copy costructor*/{
        setName(u.getName());
        setSurname(u.getSurname());
        setPassword(u.getPassword());
        setId(u.getId());
        this.travelUser=new Travel[N_MAX_TRAVELS];/*generated a series(array) of travelUser*/
        if(this.numTravelPresent!=0){
            for(int i=0;i<u.getNumTravelPresent();i++)/*copy series(array) of travelUser inside new travelUser*/{
                this.travelUser[i]=new Travel(u.travelUser[i]);
            }
        }
    }
    /**
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * 
     * @param surname 
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    /**
     * 
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * 
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * 
     * @param numTravelPresent 
     */
    public void setNumTravelPresent(int numTravelPresent) {
        this.numTravelPresent = numTravelPresent;
    }
    /**
     * 
     * @return 
     */
    public String getName() {
        return name;
    }
    /**
     * 
     * @return 
     */
    public String getSurname() {
        return surname;
    }
    /**
     * 
     * @return 
     */
    public String getPassword() {
        return password;
    }
    /**
     * 
     * @return 
     */
    public int getId() {
        return id;
    }
    /**
     * 
     * @return 
     */
    public int getNumTravelPresent() {
        return numTravelPresent;
    }
    /**
     * 
     * @return 
     */
    public Travel[] getTravelUser() {
        return travelUser;
    }
    public int getN_MAX_TRAVELS(){
        return this.N_MAX_TRAVELS;
    }
    /**
     * 
     * @return 
     */
    @Override
    public String toString()/*to string*/ {
        String s="\nname: "+this.getName()+"\nsurname:"+this.getSurname()+"\npassword: "+this.getPassword()+"\nid: "+this.getId()+"\nnumber of travel: "+this.getNumTravelPresent();
        return s;
    }
    /*Other methods*/
    public void travelPlanning(String destination, int id, int startDayOfMonths, int startValueOfMonth,int startYear,  int endDayOfMonths, int endValueOfMonth,int endYear) throws MaximumReached, travelsNotFound
    {
        /*
        parameter checks must be done within the calling method / class
        */
        if(this.numTravelPresent==0)
            throw new exception.travelsNotFound();
        try{
            travelUser[numTravelPresent]=new Travel(destination, id, startDayOfMonths, startValueOfMonth, startYear, endDayOfMonths, endValueOfMonth, endYear);
            numTravelPresent++;
        }
        catch(ArrayIndexOutOfBoundsException firstException){
            throw new exception.MaximumReached(N_MAX_TRAVELS);
        }
    }
    public void travelPlanning(Travel t) throws MaximumReached, travelsNotFound
    {
        /*
        parameter checks must be done within the calling method / class
        */
        if(this.numTravelPresent==0)
            throw new exception.travelsNotFound();
        try{
            travelUser[numTravelPresent]=new Travel(t);
            numTravelPresent++;
        }
        catch(ArrayIndexOutOfBoundsException firstException){
            throw new exception.MaximumReached(N_MAX_TRAVELS);
        }
    }
    public int cancelATravel(int idTravel) throws ItemNotFound, travelsNotFound
    {
        /*
        parameter checks must be done within the calling method / class
        */
        if(this.numTravelPresent==0)
            throw new exception.travelsNotFound();
        for(int i=0;i<numTravelPresent;i++){
            if(travelUser[i].getId()==idTravel){
                for(int j=i;j<numTravelPresent-1;j++){
                    travelUser[j]=travelUser[j+1];
                }
                travelUser[numTravelPresent]=null;
                numTravelPresent--;
                return 0;
            }
        }
        throw new exception.ItemNotFound("idTravel");/*id not found*/
    }
    public String showTravelsSortedByEntry() throws NullPointer, travelsNotFound{
        if(this.numTravelPresent==0)
            throw new exception.travelsNotFound();
        String s="";
        try{
            for(int i=0;i<numTravelPresent;i++){
                s+="TRAVEL: "+i+travelUser[i].toString();
            }
            return s;
        }
        catch(NullPointerException exception){
            throw new exception.NullPointer(184);
        }
    }
    public String showTravelSortedByDeparture() throws NullPointer, travelsNotFound{//ERROR CONTROL THIS METHOD (null pointer declared)
        if(this.numTravelPresent==0)
            throw new exception.travelsNotFound();
        String s="";
        try{
            Travel[] array=new Travel[this.numTravelPresent];
            //ordinare travelUser
            array=Ordinatore.selectionSortStartTravelCrescente(travelUser);
            for(int i=0;i<numTravelPresent;i++){
                s+="travel to location "+i+" is:\n"+array[i].toString();
            }
            return s;
        }
        catch(NullPointerException exception){
            throw new exception.NullPointer(202);
        }
    }
    public int postponeTravel(int idTravel, int startDayOfMonths, int startValueOfMonth,int startYear,  int endDayOfMonths, int endValueOfMonth,int endYear) throws ItemNotFound, travelsNotFound{
        /*
        parameter checks must be done within the calling method / class
        */
        if(this.numTravelPresent==0)
            throw new exception.travelsNotFound();
        for(int i=0;i<numTravelPresent;i++){
            if(travelUser[i].getId()==idTravel){
                travelUser[i].setEndTravel(endDayOfMonths, endValueOfMonth, endYear);
                travelUser[i].setStartTravel(startDayOfMonths, startValueOfMonth, startYear);
                return 0;
            }
        }
        throw new exception.ItemNotFound("idTrave");/*id not found*/
    }
    private void travelPlanner() throws travelsNotFound{
        if(this.numTravelPresent==0)
            throw new exception.travelsNotFound();
        boolean exchangeTookPlace=false;
        for(int i=0;i<this.getN_MAX_TRAVELS();i++){
            exchangeTookPlace=false;
            if(this.travelUser[i]==null){
                for(int j=i+1;j<this.getN_MAX_TRAVELS()-1;j++){
                    if(this.travelUser[j]!=null && exchangeTookPlace!=true){
                        Ordinatore.scambia(travelUser, i, j);
                        exchangeTookPlace=true;
                    }
                }
            }
        }
    }
    
}
