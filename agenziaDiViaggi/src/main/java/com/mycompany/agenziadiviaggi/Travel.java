/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenziadiviaggi;

import java.io.Serializable;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 * @author luca gabossi
 * this class is a representation of a journey and can create an object of type Travel.
 */
class Travel implements Serializable{
    private String destination;
    private int id;
    private LocalDate startTravel;
    private LocalDate endTravel;
    private static float FIXED_COST=25;
    private float cost;
    private boolean paidTravel=false;
    /**
     * is the constructor receives as input a destination, id, dayOfMonth, valueOfMonth, year.
     * @param destination:(String)is the travel destination.
     * @param id:(int)is an entire identification code, used to make a trip unique.
     * @param dayOfMonths:(int) it is the day of departure of a trip.
     * @param valueOfMonth:(int) is the month of departure of the trip.
     * @param year:(int) it is the year of departure of the trip.
     * this constructor creates an object of type travel.
     */
    public Travel(String destination, int id, int startDayOfMonths, int startValueOfMonth,int startYear,  int endDayOfMonths, int endValueOfMonth,int endYear)/*first costructor*/{
        setDestination(destination);
        setId(id);
        setStartTravel(startDayOfMonths, startValueOfMonth, startYear);
        setEndTravel(endDayOfMonths, endValueOfMonth, endYear);
        calculateTravelCost();
    }
    /**
     * is the constructor receives as input an object of type travel and creates a copy of it.
     * @param t:(Travel) object of type travel.
     * this constructor creates an object of type travel.
     */
    public Travel(Travel t){
        setDestination(t.getDestination());
        setId(t.getId());
        setStartTravel(t.getStartTravel().getDayOfMonth(), t.getStartTravel().getMonthValue(),t.getStartTravel().getYear());
        setEndTravel(t.getEndTravel().getDayOfMonth(), t.getEndTravel().getMonthValue(),t.getEndTravel().getYear());
        calculateTravelCost();
    }

    Travel(Travel[] travelUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * is a setter and is used to set the destination.
     * @param destination: (String)is the travel destination.
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }
    /**
     * is a setter and is used to set the id.
     * @param id :(int)is an entire identification code, used to make a user unique.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * is a setter and is used to set the start travel.
     * @param dayOfMonths:(int) it is the day of departure of a trip.
     * @param valueOfMonth:(int) is the month of departure of the trip.
     * @param year:(int) it is the year of departure of the trip.
     */
    public void setStartTravel( int dayOfMonths, int valueOfMonth,int year) {
        this.startTravel=LocalDate.of(year, valueOfMonth, dayOfMonths);
    }
    /**
     * is a setter and is used to set the end travel.
     * @param dayOfMonths:(int) it is the day of end of a trip.
     * @param valueOfMonth:(int) is the month of end of the trip.
     * @param year:(int) it is the year of end of the trip.
     */
    public void setEndTravel( int dayOfMonths, int valueOfMonth,int year) {
        this.endTravel=LocalDate.of(year, valueOfMonth, dayOfMonths);
    }
    /**
     * is a setter and is used to set the paid travel = true.
     */
    public void setPaidTravel() {
        this.paidTravel =true;
    }
    /**
     * @return (string) travel destination 
     */
    public String getDestination() {
        return destination;
    }
    /**
     * @return (int) id
     */
    public int getId() {
        return id;
    }
    /**
     * @return (localDate) the travel departure date
     */
    public LocalDate getStartTravel() {
        return startTravel;
    }
    /**
     * @return (localDate) the travel end date
     */
    public LocalDate getEndTravel() {
        return endTravel;
    }
    /**
     * @return (String) the payment of the trip
     */
    public String getPaidTravel(){
        String s="no";
        if(this.paidTravel)
            s="yes";
        return s;
    }
    /**
     * @return (flaot) the cost of the trip
     */
    public float getCost() {
        return cost;
    }
    /**
     * @return (String) to string
     */
    @Override
    public String toString(){
        String s="\ndestination: "+this.getDestination()+"\ntravel id: "+this.getId()+"\nstart travel: "+this.getStartTravel()+"\nend travel: "+this.getEndTravel()+"\ncost: "+this.getCost()+"\npaidTravel: "+this.getPaidTravel();
        return s;
    }
    /**
     * @return (flaot) calculate the cost of the trip
     */
    public float calculateTravelCost(){
        float daysBetween = DAYS.between(this.startTravel, this.endTravel);
        this.cost=FIXED_COST*daysBetween;
        return cost;
    }
}
