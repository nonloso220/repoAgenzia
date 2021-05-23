/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenziadiviaggi;

import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 * @author luca gabossi
 * this class is a representation of a admin and can create an object of type Admin.
 */
public class Admin {
    private String name;
    private String surname;
    private String password;
    private String email;
    private int id;
    /**
     * is the constructor receives as input a name, surname, password, id, email.
     * @param name:(String)the admin's name.
     * @param surname:(String)the admin's surname.
     * @param password:(String) the password to access the admin's account.
     * @param id:(int) is an entire identification code, used to make a admin unique.
     * @param email:(String)is the admin's email.  
     * this constructor creates a admin-type object.
     */
    public Admin(String name, String surname, String password, String email, int id) {
        setName(name);
        setSurname(surname);
        setPassword(password);
        setEmail(email);
        setId(id);
    }
    /**
     * @return (String)the name
     */
    public String getName() {
        return name;
    }
    /**
     * is a setter and is used to set the name.
     * @param name:(String) is the name of the admin.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return (String)the surname
     */
    public String getSurname() {
        return surname;
    }
    /**
     * is a setter and is used to set the surname.
     * @param surname:(String) is the admin's last name.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    /**
     * @return (String)the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * is a setter and is used to set the password.
     * @param password:(String) is the admin's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return (String) the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * is a setter and is used to set the email.
     * @param email:(String) is the admin's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return (int)the id
     */
    public int getId() {
        return id;
    }
    /**
     * is a setter and is used to set the id.
     * @param id:(int) is the admin's id.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return (String) to string
     */
    @Override
    public String toString() {
        return "\nname: "+this.getName()+"\nsurname: "+this.getSurname()+"\npassword: "+this.getPassword()+"\nemail: "+this.getEmail()+"\nid: "+this.getId();
    }
    /**
     * this method allows admins to be able to see all participants in a given trip by entering the travel destination and departure date.
     * @param users:(User[])is the array of users.
     * @param numberUsersPresent:(int) is the number of users in the array users of type User [].
     * @param date:(LocalDate)is the departure date.
     * @param destination:(String)is the travel destination that will then be searched for among users.
     * @return (String) a string containing all the users who are part of a trip.
     */
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
