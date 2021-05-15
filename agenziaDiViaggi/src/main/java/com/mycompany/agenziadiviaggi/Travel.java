/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenziadiviaggi;

import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 *
 * @author luca gabossi
 * 
 * 
 * 
 * 
 * 
 * 
 */
class Travel {
    private String destination;
    private int id;
    private LocalDate startTravel;
    private LocalDate endTravel;
    private static float FIXED_COST=25;
    private float cost;
    private boolean paidTravel=false;
    /**
     * 
     * @param destination
     * @param id
     * @param dayOfMonths
     * @param valueOfMonth
     * @param year 
     */
    public Travel(String destination, int id, int startDayOfMonths, int startValueOfMonth,int startYear,  int endDayOfMonths, int endValueOfMonth,int endYear)/*first costructor*/{
        setDestination(destination);
        setId(id);
        setStartTravel(startDayOfMonths, startValueOfMonth, startYear);
        setEndTravel(endDayOfMonths, endValueOfMonth, endYear);
        calculateTravelCost();
    }
    /**
     * 
     * @param t 
     */
    public Travel(Travel t) /*copy costructor*/{
        setDestination(t.getDestination());
        setId(t.getId());
        setStartTravel(t.getStartTravel().getDayOfMonth(), t.getStartTravel().getMonthValue(),t.getStartTravel().getYear());
        setEndTravel(t.getEndTravel().getDayOfMonth(), t.getEndTravel().getMonthValue(),t.getEndTravel().getYear());
        calculateTravelCost();
    }
    /**
     * 
     * @param destination 
     */
    public void setDestination(String destination) {
        this.destination = destination;
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
     * @param dayOfMonths
     * @param valueOfMonth
     * @param year 
     */
    public void setStartTravel( int dayOfMonths, int valueOfMonth,int year) {
        this.startTravel=LocalDate.of(year, valueOfMonth, dayOfMonths);
    }
    /**
     * 
     * @param dayOfMonths
     * @param valueOfMonth
     * @param year 
     */
    public void setEndTravel( int dayOfMonths, int valueOfMonth,int year) {
        this.endTravel=LocalDate.of(year, valueOfMonth, dayOfMonths);
    }
    /**
     * 
     */
    public void setPaidTravel() {
        this.paidTravel =true;
    }

    
    /**
     * 
     * @return 
     */
    public String getDestination() {
        return destination;
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
    public LocalDate getStartTravel() {
        return startTravel;
    }
    /**
     * 
     * @return 
     */
    public LocalDate getEndTravel() {
        return endTravel;
    }
    public String getPaidTravel(){
        String s="no";
        if(this.paidTravel)
            s="yes";
        return s;
    }

    public float getCost() {
        return cost;
    }
    
    /**
     * @return
     * 
     * 
     */
    @Override
    public String toString()/*to string*/ {
        String s="\ndestination: "+this.getDestination()+"\ntravel id: "+this.getId()+"\nstart travel: "+this.getStartTravel()+"\nend travel: "+this.getEndTravel()+"\ncost: "+this.getCost()+"\npaidTravel: "+this.getPaidTravel();
        return s;
    }
    /**
     * 
     * @return 
     * 
     * 
     */
    public float calculateTravelCost(){
        float daysBetween = DAYS.between(this.startTravel, this.endTravel);
        this.cost=FIXED_COST*daysBetween;
        return cost;
    }
}
