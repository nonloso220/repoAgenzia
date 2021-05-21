/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenziadiviaggi;

import exception.*;
import java.io.Serializable;

/**
 * @author luca gabossi
 * this class is a representation of a user and can create an object of type User.
 */
public class User implements Serializable{
    private String name;
    private String surname;
    private String password;
    private String email;
    private int id;
    private float wallet;
    private int numTravelPresent=0;
    private Travel[] travelUser;
    private final int N_MAX_TRAVELS=20;
    /**
     * is the constructor receives as input a name, surname, password, id, email.
     * @param name:(String)the user's name.
     * @param surname:(String)the user's surname.
     * @param password:(String) the password to access the user's account.
     * @param id:(int) is an entire identification code, used to make a user unique.
     * @param email:(String)is the user's email.  
     * this constructor creates a user-type object.
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
     * is the constructor that receives as input a user-type object and creates a copy of it.
     * @param u:(User) object of type user.
     * this constructor creates an object of type User.
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
     * is a setter and is used to set the name.
     * @param name:(String) is the name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * is a setter and is used to set the surname.
     * @param surname:(String) is the user's last name.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    /**
     * is a setter and is used to set the password.
     * @param password:(String) is the user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * is a setter and is used to set the id.
     * @param id:(int) is the user's id.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * is a setter and is used to set the email.
     * @param email:(String) is the user's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return (String)the name
     */
    public String getName() {
        return name;
    }
    /**
     * @return (String)the surname
     */
    public String getSurname() {
        return surname;
    }
    /**
     * @return (String)the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @return (int)the id
     */
    public int getId() {
        return id;
    }
    /**
     * @return (int)the number of trips present, is used to know how many trips are present in the array
     */
    public int getNumTravelPresent() {
        return numTravelPresent;
    }
    /**
     * @return (Travel[])an array of travels
     */
    public Travel[] getTravelUser() {
        return travelUser;
    }
    public int getN_MAX_TRAVELS(){
        return this.N_MAX_TRAVELS;
    }
    /**
     * @return (String) the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @return (float)the wallet, how much money to a user on his account
     */
    public float getWallet() {
        return wallet;
    }
    /**
     * @return (String) to string
     */
    @Override
    public String toString(){
        String s="\nname: "+this.getName()+"\nsurname:"+this.getSurname()+"\npassword: "+this.getPassword()+"\nid: "+this.getId()+"\nnumber of travel: "+this.getNumTravelPresent()+"\nwallet: "+this.getWallet();
        return s;
    }
    /**
     * this method allows you to plan a trip (invoking the Travel class constructor) by entering a 
     * destination, an id (of the trip), a travel start day, a travel start month, a travel start year,
     * a travel end day, a month end of the trip, one year of end of the trip.
     * in addition to this it allows to make the payment of the trip if the wallet has the possibility, 
     * in case of overrun of the array (traveluser) the method will raise an exception called MaximumReached which will show 
     * an error output with the maximum that has been overrun.
     * @param destination:(String)is the travel destination.
     * @param id:(int)is an entire identification code, used to make a trip unique.
     * @param startDayOfMonths:(int) it is the day of departure of a trip.
     * @param startValueOfMonth:(int) is the month of departure of the trip.
     * @param startYear:(int) it is the year of departure of the trip.
     * @param endDayOfMonths:(int) is the end day of a trip.
     * @param endValueOfMonth:int) is the month of end of the trip.
     * @param endYear:(int) it is the year of end of the trip.
     * @throws MaximumReached :represents a customized version of the "ArrayIndexOutOfBoundsException" exception, and allows you to make the overflow limit visible.
     */
    public void travelPlanning(String destination, int id, int startDayOfMonths, int startValueOfMonth,int startYear,  int endDayOfMonths, int endValueOfMonth,int endYear) throws MaximumReached{
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
     * this method deletes a trip from the travel array by passing it an id (of the trip) as input,
     * obviously if it does not find it it raises an exception called ItemNotFound which sends an error message / warning to the user, 
     * if there are none voyages present in the user's travel array an exception named travelsNotFound is raised and shows an error.
     * @param idTravel: is the id of the trip you want to cancel.
     * @return (int) it always returns 0 if the operation is successful but in reality it is useless I used it only to exit the for so.
     * @throws ItemNotFound :it is a personal exception and is used for when a certain "item" is not found, for example if the travel id is never found it is raised by generating a warning / error for the user telling him that the travel id is not found.
     * @throws travelsNotFound :it is a personal exception and is for when there are no trips entered in the travel array.
     */
    public int cancelATravel(int idTravel) throws ItemNotFound, travelsNotFound{
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
        throw new exception.ItemNotFound("idTravel");
    }
    /**
     * @return (String) this method shows all the user's journeys sorted by entry, and returns them as a string.
     * @throws NullPointer : this exception occurs if you try to invoke a toString on the null trip.
     * @throws travelsNotFound : this exception occurs if the user has no travel.
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
     * @return (String) this method shows all the user's trips sorted by departure and returns them as a string.
     * @throws NullPointer : this exception occurs if you try to invoke a toString on the null trip.
     * @throws travelsNotFound : this exception occurs if the user has no travel.
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
     * this method simply allows you to change the departure and end date of a trip, 
     * passing it as inpuit the id of the trip, and the day, month, year of start and end, 
     * ATTENTION: this method will not do any kind of check on the data input so it is preferable to check before using it.
     * @param idTravel: is the identification code of the trip whose date you want to change.
     * @param startDayOfMonths: it is the day of departure of the trip.
     * @param startValueOfMonth:is the month of departure of the trip.
     * @param startYear:is the year of departure of the trip.
     * @param endDayOfMonths: it is the day of the end of the journey.
     * @param endValueOfMonth: is the month of the end of the trip
     * @param endYear: it is the year of the end of the journey.
     * @return (int) it can be neglected, because it only serves to make the method terminate quickly without too many problems, however it always returns a 0 if it is okay otherwise it raises the ItemNotFound exception.
     * @throws ItemNotFound : this exception occurs when you try to search for an item and it is not found.
     * @throws travelsNotFound : this exception occurs if the user has no travel.
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
     * this method will add to the wallet a share of the money entered by the user.
     * @param credit: (flaot)represents how much money the user wants to put into the wallet.
     */
    public void addCredit(float credit){
        this.wallet+=credit;
    }
    /**
     * @return (String)this method shows all the user's destinations without duplicates, obviously it only shows the names in a simple list without too much information, for example if user A has two trips both to france the method will return a string with france and not france france ,basically it puts the user's destinations in a string once.
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
