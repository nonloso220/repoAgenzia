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
 */
public class Admin {
    private String name;
    private String surname;
    private String password;
    private String email;
    private int id;
    public Admin(String name, String surname, String password, String email, int id) {
        setName(name);
        setSurname(surname);
        setPassword(password);
        setEmail(email);
        setId(id);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "\nname: "+this.getName()+"\nsurname: "+this.getSurname()+"\npassword: "+this.getPassword()+"\nemail: "+this.getEmail()+"\nid: "+this.getId();
    }
    public String viewParticipantsInATravel(User users[],int numberUsersPresent,LocalDate date,String destination){
        if(numberUsersPresent==0)
            return "no user present";
        String s="name:\tsurname:\n";
        User[] u;
        u=Ordinatore.selectionSortAlphabeticUsers(numberUsersPresent, users);
        for(int i=0;i<numberUsersPresent;i++){
            Travel[] t=u[i].getTravelUser();
            for(int j=0;j<u[i].getNumTravelPresent();j++){
                if(t[j].getDestination().equalsIgnoreCase(destination)){
                    if(DAYS.between(t[j].getStartTravel(), date)==0){
                        s+=u[i].getName()+"\t"+u[i].getSurname()+"\n";
                    }
                }
            }
        }
        return s;
    }
}
