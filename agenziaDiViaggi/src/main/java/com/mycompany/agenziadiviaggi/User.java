/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenziadiviaggi;

import exception.*;
import java.io.Serializable;

/**
 *
 * @author luca gabossi
 * 
 * 
 * 
 * 
 */
public class User implements Serializable{
    private String name;
    private String surname;
    private String password;
    private String email;
    private int id;
    private float wallet;
    private int numTravelPresent=0;/*counter travel*/
    private Travel[] travelUser;/*array travel*/
    private final int N_MAX_TRAVELS=20;/*maximum number of travels that can be entered*/
    /**
     * @param name
     * @param surname
     * @param password
     * @param id 
     */
    public User(String name, String surname, String password, int id, String email){
        setName(name);
        setSurname(surname);
        setPassword(password);
        setId(id);
        setEmail(email);
        addCredit(0);
        this.travelUser=new Travel[N_MAX_TRAVELS];/*generates a series(array) of travelUser*/
    }
    /**
     * 
     * @param u 
     */
    public User(User u){
        setName(u.getName());
        setSurname(u.getSurname());
        setPassword(u.getPassword());
        setId(u.getId());
        setEmail(u.getEmail());
        addCredit(u.getWallet());
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
     * @param email 
     */
    public void setEmail(String email) {
        this.email = email;
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
    public String getEmail() {
        return email;
    }
    /**
     * 
     * @return 
     */
    public float getWallet() {
        return wallet;
    }
    /**
     * 
     * @return 
     */
    @Override
    public String toString()/*to string*/ {
        String s="\nname: "+this.getName()+"\nsurname:"+this.getSurname()+"\npassword: "+this.getPassword()+"\nid: "+this.getId()+"\nnumber of travel: "+this.getNumTravelPresent()+"\nwallet: "+this.getWallet();
        return s;
    }
    /**
     * 
     * @param destination
     * @param id
     * @param startDayOfMonths
     * @param startValueOfMonth
     * @param startYear
     * @param endDayOfMonths
     * @param endValueOfMonth
     * @param endYear
     * @throws MaximumReached
     * @throws travelsNotFound 
     */
    public void travelPlanning(String destination, int id, int startDayOfMonths, int startValueOfMonth,int startYear,  int endDayOfMonths, int endValueOfMonth,int endYear) throws MaximumReached, travelsNotFound{
        try{
            travelUser[numTravelPresent]=new Travel(destination, id, startDayOfMonths, startValueOfMonth, startYear, endDayOfMonths, endValueOfMonth, endYear);
            if(wallet>travelUser[numTravelPresent].calculateTravelCost()){
                this.wallet-=travelUser[numTravelPresent].calculateTravelCost();
                travelUser[numTravelPresent].setPaidTravel();
            }
            numTravelPresent++;
        }
        catch(ArrayIndexOutOfBoundsException firstException){
            throw new exception.MaximumReached(N_MAX_TRAVELS);
        }
    }
    /**
     * 
     * @param t
     * @throws MaximumReached 
     */
    public void travelPlanning(Travel t) throws MaximumReached{
        try{
            travelUser[numTravelPresent]=new Travel(t);
            if(wallet>travelUser[numTravelPresent].calculateTravelCost()){
                this.wallet-=travelUser[numTravelPresent].calculateTravelCost();
                travelUser[numTravelPresent].setPaidTravel();
            }
            numTravelPresent++;
        }
        catch(ArrayIndexOutOfBoundsException firstException){
            throw new exception.MaximumReached(N_MAX_TRAVELS);
        }
    }
    /**
     * 
     * @param idTravel
     * @return
     * @throws ItemNotFound
     * @throws travelsNotFound 
     */
    public int cancelATravel(int idTravel) throws ItemNotFound, travelsNotFound
    {
        if(this.numTravelPresent==0)
            throw new exception.travelsNotFound();
        for(int i=0;i<numTravelPresent;i++){
            if(travelUser[i].getId()==idTravel){
                for(int j=i;j<numTravelPresent-1;j++){
                    travelUser[j]=travelUser[j+1];
                }
                travelUser[numTravelPresent-1]=null;
                numTravelPresent--;
                return 0;
            }
        }
        throw new exception.ItemNotFound("idTravel");/*id not found*/
    }
    /**
     * 
     * @return
     * @throws NullPointer
     * @throws travelsNotFound 
     */
    public String showTravelsSortedByEntry() throws NullPointer, travelsNotFound{
        if(this.numTravelPresent==0)
            throw new exception.travelsNotFound();
        String s="";
        try{
            s+="\nTRAVEL USER: \n";
            for(int i=0;i<numTravelPresent;i++){
                s+="\n----------------------------------------";
                s+=travelUser[i].toString();
                s+="\n----------------------------------------";
            }
            return s;
        }
        catch(NullPointerException exception){
            throw new exception.NullPointer();
        }
    }
    /**
     * 
     * @return
     * @throws NullPointer
     * @throws travelsNotFound 
     */
    public String showTravelsSortedByDeparture() throws NullPointer, travelsNotFound{
        if(this.numTravelPresent==0)
            throw new exception.travelsNotFound();
        String s="";
        try{
            Travel[] array=new Travel[this.numTravelPresent];
            array=Ordinatore.selectionSortStartTravelCrescente(travelUser);
            s+="\nTRAVEL USER: \n";
            for(int i=0;i<numTravelPresent;i++){
                s+="\n----------------------------------------";
                s+=array[i].toString();
                s+="\n----------------------------------------";
            }
            return s;
        }
        catch(NullPointerException exception){
            throw new exception.NullPointer();
        }
    }
    /**
     * 
     * @param idTravel
     * @param startDayOfMonths
     * @param startValueOfMonth
     * @param startYear
     * @param endDayOfMonths
     * @param endValueOfMonth
     * @param endYear
     * @return
     * @throws ItemNotFound
     * @throws travelsNotFound 
     */
    public int postponeTravel(int idTravel, int startDayOfMonths, int startValueOfMonth,int startYear,  int endDayOfMonths, int endValueOfMonth,int endYear) throws ItemNotFound, travelsNotFound{
        if(this.numTravelPresent==0)
            throw new exception.travelsNotFound();
        for(int i=0;i<numTravelPresent;i++){
            if(travelUser[i].getId()==idTravel){
                travelUser[i].setEndTravel(endDayOfMonths, endValueOfMonth, endYear);
                travelUser[i].setStartTravel(startDayOfMonths, startValueOfMonth, startYear);
                return 0;
            }
        }
        throw new exception.ItemNotFound("idTrave");
    }
    /**
     * 
     * @param credit 
     */
    public void addCredit(float credit){
        this.wallet+=credit;
    }
    /**
     * 
     * @return 
     */
    public String showDestinations(){
        String s="";
        if(this.numTravelPresent==0){
            return s+="there are no travel";
        }
        s+="\nDESTINATIONS OF YOUR TRAVELS:\n";
        if(this.numTravelPresent==1){
            for(int i=0;i<this.numTravelPresent;i++){
                if(this.travelUser[i]!=null){
                    s+=this.travelUser[i].getDestination()+"\n";
                    return s;
                }
            }
        }
        for(int i=0;i<this.numTravelPresent;i++){
            for(int j=1;j<this.numTravelPresent;j++){
                if(i!=j){
                    if(this.travelUser[i].getDestination().compareToIgnoreCase(this.travelUser[j].getDestination())!=0){
                        if(j==this.numTravelPresent-1){
                            s+=this.travelUser[i].getDestination()+"\n";
                        }    
                    }
                    else if(this.travelUser[i].getDestination().compareToIgnoreCase(this.travelUser[j].getDestination())==0){
                        if(i<j)
                            break;
                    }
                }
                else if(i==this.numTravelPresent-1){
                    s+=this.travelUser[i].getDestination()+"\n";
                }      
            }
        } 
        return s;
    }
}
