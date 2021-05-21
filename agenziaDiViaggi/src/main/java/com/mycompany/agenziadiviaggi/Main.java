/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenziadiviaggi;

import exception.*;
import file.TextFile;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author luca gabossi
 */
public class Main {

    public static void main(String[] args) {
        Scanner keyboard=new Scanner(System.in);
        int N_MAX_USERS=100;
        int numberUsersPresent=0;
        int userChoice=-1;
        int idTravel=0;
        int idUsers=0;
        int startDayOfMonths = 0, startValueOfMonth = 0, startYear = 0, endDayOfMonths = 0, endValueOfMonth = 0, endYear = 0;
        boolean dateCorrect = false;
        boolean c2=false;/*when user selected a case 2(generete an account and log into it) is true*/
        String nsmeFileTxt="file-exported-to-users-CSV.txt";
        String nsmeFileBinary="binary-file-for-users.bin";
        User[] users=new User[N_MAX_USERS];
        String[] login=new String[5];
        login[0]="exit the program";
        login[1]="log into an existing account";
        login[2]="create an account and log into it";
        login[3]="exports users to CSV files";
        login[4]="save to binary file";
        String[] user = new String[8];
        user[0]="come back";
        user[1]="Travel planning";
        user[2]="cancel a travel";
        user[3]="show Travels Sorted By Entry";
        user[4]="show Travels Sorted By Departure";
        user[5]="show destinations";
        user[6]="postpone Travel";
        user[7]="delete the account";
        String[] admi = new String[2];
        admi[0]="come back";
        admi[1]="View the list of participants in a particular trip in alphabetical order by entering the date and destination";
        String[] travel=new String[3];
        travel[0]="come back";
        travel[1]="autonomous travel planning";
        travel[2]="guided travel planning";
        String[] destinescion=new String[7];
        destinescion[0]="come back";
        destinescion[1]="show destinations of Europe";
        destinescion[2]="show destinations of Asia";
        destinescion[3]="shows destinations of America";
        destinescion[4]="shows destinations of Australia";
        destinescion[5]="shows destinations of Africa";
        destinescion[6]="shows destinations of Antarctica";
        String[] europe=new String[26];
        europe[0]="come back";
        europe[1]="Londra";
        europe[2]="Parigi";
        europe[3]="Istanbul";
        europe[4]="Antalya";
        europe[5]="Roma";
        europe[6]="Praga";
        europe[7]="Amsterdam";
        europe[8]="Barcellona";
        europe[9]="Milano";
        europe[10]="Vienna";
        europe[11]="Berlino";
        europe[12]="Madrid";
        europe[13]="Venezia";
        europe[14]="Mosca";
        europe[15]="Dublino";
        europe[16]="Atene";
        europe[17]="Firenze";
        europe[18]="Monaco";
        europe[19]="Budapest";
        europe[20]="San Pietroburgo";
        europe[21]="Lisbona";
        europe[22]="Heraklion (Creta)";
        europe[23]="Bruxelles";
        europe[24]="Copenaghen";
        europe[25]="Cracovia";	
        String[] asia=new String[1];
        asia[0]="come back";
        
        String[] america=new String[1];
        america[0]="come back";
        
        String[] australia=new String[1];
        australia[0]="come back";
        
        String[] africa=new String[1];
        africa[0]="come back";
        
        String[] antarctica=new String[1];
        antarctica[0]="come back";
        
        Menu mLogin=new Menu(login);
        Menu mUser=new Menu(user);
        Menu mAdmin=new Menu(admi);
        Menu mTravel=new Menu(travel);
        Menu mDestinescion=new Menu(destinescion);
        Menu mEurope=new Menu(europe);
        Menu mAsia=new Menu(asia);
        Menu mAmerica=new Menu(america);
        Menu mAustralia=new Menu(australia);
        Menu mAfrica=new Menu(africa);
        Menu mAntarctica=new Menu(antarctica);
        Admin admin=new Admin("admin", "admin", "Coniglio", "admin.Agency@Yahoo.com", 101);
        try{ 
            FileInputStream f1=new FileInputStream(nsmeFileBinary);
            ObjectInputStream reader=new ObjectInputStream(f1); 
            try{
                idTravel=(int)reader.readInt();//chiedi al prof
                idUsers=(int)reader.readInt();//chiedi al prof
                numberUsersPresent=(int)reader.readInt();//chiedi al prof
                users=(User[])reader.readObject();
                reader.close();
            }catch (ClassNotFoundException ex){
                reader.close();
                System.out.println("Error: unable to read file, press any key to continue");
                keyboard.nextLine();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error: cannot find the file, press any key to continue");
            keyboard.nextLine();
        } catch (IOException ex) {
            System.out.println("Error: unable to open file, press any key to continue");
            keyboard.nextLine();
        }
        try {
            do {
                if (c2) {
                    userChoice = 1;
                } else {
                    try {
                        userChoice = mLogin.sceltaMenu(0);
                    } catch (ItemNotFound ex) {
                        System.out.println(ex.toString() + " press any key to continue");
                        keyboard.nextLine();
                    }
                }
                switch (userChoice) {
                    case 0: {
                        System.out.println("Bye-Bye!");
                        break;
                    }
                    case 1: {
                        int i = 0;
                        String email = null;
                        String password = null;
                        if (!c2) {
                            email = InputControlls.inputAnalyzerString("email", 2);
                            password = InputControlls.inputAnalyzerString("account password", 8);
                        }
                        if(admin.getEmail().equalsIgnoreCase(email)){
                            int choiceMenu02=-1;
                            do{
                                 try {
                                        choiceMenu02 = mAdmin.sceltaMenu(0);
                                    } catch (ItemNotFound ex) {
                                        System.out.println(ex.toString() + " press any key to continue");
                                    }
                                switch(choiceMenu02){
                                    case 0:{
                                        break;
                                    }
                                    case 1:{
                                        if(numberUsersPresent==0){
                                            System.out.println("no user present");
                                            keyboard.nextLine();
                                            break;
                                        }
                                        System.out.println("destination: ");
                                        String destination = keyboard.nextLine();
                                        do {
                                            startDayOfMonths = InputControlls.inputAnalyzerInt("startDayOfMonths", 1);
                                            startValueOfMonth = InputControlls.inputAnalyzerInt("startValueOfMonth", 1);
                                            startYear = InputControlls.inputAnalyzerInt("startYear", 1);
                                            dateCorrect = isDataValida(startDayOfMonths, startValueOfMonth, startYear);
                                            if (dateCorrect) {
                                                break;
                                            } else {
                                                System.out.println("ERROR: invalid date, press any key to continue");
                                                keyboard.nextLine();
                                            }
                                        } while (!dateCorrect);
                                        LocalDate date=LocalDate.of(startYear, startValueOfMonth, startDayOfMonths);
                                        System.out.println(admin.viewParticipantsInATravel(users, numberUsersPresent,date, destination));
                                        break;
                                    }
                                }
                            }while(choiceMenu02 != 0); 
                            break;
                        }
                        else if(numberUsersPresent!=0){
                            boolean counterNotFound=true;
                            for (i = 0; i < numberUsersPresent; i++) {
                                if (c2) {
                                    i = numberUsersPresent - 1;
                                    email = users[i].getEmail();
                                    password = users[i].getPassword();
                                }
                                c2 = false;
                                if(users[i]!=null){
                                    if (users[i].getEmail().equalsIgnoreCase(email)) {
                                        int passwordCounter=0;
                                        counterNotFound=false;
                                        while(passwordCounter<4){
                                            if (users[i].getPassword().equalsIgnoreCase(password)) {
                                                int choiceMenu01 = -1;
                                                do {
                                                    try {
                                                        choiceMenu01 = mUser.sceltaMenu(0);
                                                    } catch (ItemNotFound ex) {
                                                        System.out.println(ex.toString() + " press any key to continue");
                                                    }
                                                    try {
                                                        switch (choiceMenu01) {
                                                            case 0: {/*come back*/
                                                                break;
                                                            }
                                                            case 1: {/*Travel planning*/
                                                                if (users[i].getN_MAX_TRAVELS() == users[i].getNumTravelPresent()) {
                                                                    System.out.println("maximum number of trips reached");
                                                                    break;
                                                                }
                                                                int choiceMenu010= -1;
                                                                try {
                                                                    choiceMenu010 = mTravel.sceltaMenu(0);
                                                                    switch(choiceMenu010){
                                                                        case 0:{/*come back*/
                                                                            break;
                                                                        }
                                                                        case 1:{/*autonomous Travel planning*/
                                                                            System.out.println("destination: ");
                                                                            String destination = keyboard.nextLine();
                                                                            int id = idTravel;
                                                                            do {
                                                                                startDayOfMonths = InputControlls.inputAnalyzerInt("startDayOfMonths", 1);
                                                                                startValueOfMonth = InputControlls.inputAnalyzerInt("startValueOfMonth", 1);
                                                                                startYear = InputControlls.inputAnalyzerInt("startYear", 1);
                                                                                dateCorrect = isDataValida(startDayOfMonths, startValueOfMonth, startYear);
                                                                                if (dateCorrect) {
                                                                                    break;
                                                                                } else {
                                                                                    System.out.println("ERROR: invalid date");
                                                                                }
                                                                            } while (!dateCorrect);
                                                                            do {
                                                                                endDayOfMonths = InputControlls.inputAnalyzerInt("endDayOfMonths", 1);
                                                                                endValueOfMonth = InputControlls.inputAnalyzerInt("endValueOfMonth", 1);
                                                                                endYear = InputControlls.inputAnalyzerInt("endYear", 1);
                                                                                dateCorrect = isDataValida(endDayOfMonths, endValueOfMonth, endYear);
                                                                                if (dateCorrect) {
                                                                                    break;
                                                                                } else {
                                                                                    System.out.println("ERROR: invalid date");
                                                                                }
                                                                            } while (!dateCorrect);
                                                                            try {
                                                                                users[i].travelPlanning(destination, id, startDayOfMonths, startValueOfMonth, startYear, endDayOfMonths, endValueOfMonth, endYear);
                                                                                System.out.println("the travel ID is: " + id);
                                                                                System.out.println("Successful operation, press any key to continue");
                                                                                keyboard.nextLine();
                                                                                idTravel++;
                                                                            } catch (MaximumReached ex) {
                                                                                System.out.println(ex.toString());
                                                                            } catch (DateTimeException ex) {
                                                                                System.out.println("Error: the date is invalid");
                                                                                keyboard.nextLine();
                                                                            }
                                                                            break;
                                                                        }
                                                                        case 2:{/*guided Travel planning*/
                                                                            int choiceMenu011 =-1;
                                                                            int choiceMenu0111=-1;
                                                                            boolean travelCompleted=false;
                                                                            do{
                                                                                choiceMenu011 = mDestinescion.sceltaMenu(0);
                                                                                switch(choiceMenu011){
                                                                                    case 0: {/*come back*/
                                                                                        travelCompleted=true;
                                                                                        break;
                                                                                    }
                                                                                    case 1: {
                                                                                        do{
                                                                                            choiceMenu0111=mEurope.sceltaMenu(0);
                                                                                            String destination = null;
                                                                                            String destinationChoice = null;
                                                                                            switch(choiceMenu0111){
                                                                                                case 0:{
                                                                                                    break;
                                                                                                }
                                                                                                case 1:{
                                                                                                    System.out.println("London, United Kingdom: 20,715,900 visitors\n" +
                                                                                                    "The capital of England looks like an immense metropolis and offers a lot to the visitor,\n" +
                                                                                                    "for this reason it is in first place in this ranking. The British Museum, with free admission,\n" +
                                                                                                    "it is a fascinating place where numerous archaeological finds are preserved,\n" +
                                                                                                    "including the famous Rosetta Stone. Westminster Abbey is the historic London cathedral,\n" +
                                                                                                    "but those who love green walks will undoubtedly appreciate Hyde Park.\n" +
                                                                                                    "A visit to Buckingham Palace is a must,\n" +
                                                                                                    "maybe during the changing of the guard. In Piccadilly Circus you can go shopping,\n" +
                                                                                                    "as well as at Harrods and Camden Town.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("\n[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="London";
                                                                                                    break;
                                                                                                }
                                                                                                case 2:{
                                                                                                    System.out.println("Paris, France: 16,863,500 visitors\n" +
                                                                                                    "How many marriage proposals or declarations of love have been declaimed in the light of the Eiffel Tower?\n" +
                                                                                                    "The best of European history and culture is condensed in this city,\n" +
                                                                                                    "those who visit it can enjoy incredible places of interest such as the Louvre Museum\n" +
                                                                                                    "but also decide to get lost in fascinating neighborhoods such as Montmartre or the Marais.\n" +
                                                                                                    "  The city offers the ultimate in romanticism along the Seine,\n" +
                                                                                                    "with some truly picturesque views,\n" +
                                                                                                    "  its manicured gardens and the many outdoor bistros.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("\n[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Paris";
                                                                                                    break;
                                                                                                }
                                                                                                case 3:{
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 4:{
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 5:{
                                                                                                    System.out.println("Rome, Italy: 9,703,200 visitors\n" +
                                                                                                    "What about Rome, which has not yet been said?\n" +
                                                                                                    "An open-air museum, they defined it, and how can this claim be denied?\n" +
                                                                                                    "What is universally considered one of the most beautiful cities in the world - if not the most beautiful -\n" +
                                                                                                    "is the eternal city, Caput Mundi, the city of \\ \"La dolce vita \\\" ... Tourists come for the Colosseum,\n" +
                                                                                                    "Piazza Navona,\n" +
                                                                                                    "the Pantheon,\n" +
                                                                                                    "the Trevi Fountain,\n" +
                                                                                                    "the Vatican Museums, l\n" +
                                                                                                    "at the Spanish Steps,\n" +
                                                                                                    "the Sistine Chapel ... and they go away with its blue sky and sunsets along the Tiber in the heart.");
                                                                                                   destinationChoice=InputControlls.inputAnalyzerStringNominative("\n[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Rome";
                                                                                                    break;
                                                                                                }
                                                                                                case 6:{
                                                                                                    System.out.println("Prague, Czech Republic: 9,038,900 visitors\n" +
                                                                                                    "The Old Town Hall with the famous Prague Astronomical Clock, the winding streets of the Jewish Quarter,\n" +
                                                                                                    "already known by those who have read the novels of Franz Kafka,\n" +
                                                                                                    "immersed in the legend of the Golem, the cafes that just look at them invite you to enter and sit down, the boutiques,\n" +
                                                                                                    "cruises on the Vltava ... These are just some of the attractions that make Prague one of the most visited cities in the world.\n" +
                                                                                                    "The Charles Bridge is a majestic Gothic bridge and the St. Nicholas Church in Mala Strana is the most beautiful Baroque church in Prague.\n" +
                                                                                                    "The city presents itself as changeable, romantic and successful, ancient and modern, but above all cosmopolitan in all respects.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Prague";
                                                                                                    break;
                                                                                                }
                                                                                                case 7:{
                                                                                                     System.out.println("Amsterdam, the Netherlands: 8,476,600 visitors\n" +
                                                                                                    "With its world-famous canals and museums, Amsterdam welcomes millions of visitors every season.\n" +
                                                                                                    "The historic center makes it one of the most romantic and beautiful cities in Europe: cruises along its canals are an alternative, captivating and different way to visit the city.\n" +
                                                                                                    "Amsterdam is also a city of diversity tolerance, as well as enjoying all the benefits of a great center: rich culture, vibrant nightlife, international restaurants, efficient public transport.\n" +
                                                                                                    "On top of that, it is also relatively quiet and, largely thanks to its wide canals, has very little road traffic.\n" +
                                                                                                    "On the other hand, experience the thrill of cycling along it, joining the flow of hundreds of citizens who use it every day!");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Amsterdam";
                                                                                                    break;
                                                                                                }
                                                                                                case 8:{
                                                                                                    System.out.println("Barcelona, Spain: 6,726,000 visitors\n" +
                                                                                                    "Barcelona has a rich heritage, both culturally and historically. Barcelona has so many attractions that choosing which ones to mention is no easy feat.\n" +
                                                                                                    "The Barrio Gótico (the Gothic Quarter) and the Ramblas are great places to stroll and enjoy the stalls and street performers.\n" +
                                                                                                    "The Sagrada Familia Cathedral, visible from practically every point of the city, is one of the most representative monuments of Barcelona.\n" +
                                                                                                    "No visit to Barcelona can be considered complete if you don't try the local dishes in a small restaurant in the center or at the large market.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Barcelona";
                                                                                                    break;
                                                                                                }
                                                                                                case 9:{
                                                                                                    System.out.println("Milan, Italy: 6,513,000 visitors\n" +
                                                                                                    "Headquarters of the Italian stock exchange, industrial power and internationally accepted arbiter of fashion and design, Milan is a seething metropolis.\n" +
                                                                                                    "The great Gothic cathedral, the Duomo, sits at the center of what was once a Roman imperial capital, and expresses the love for beauty and strength that still drives the city today.\n" +
                                                                                                    "Its strengths are the ancient and modern art collections,\n" +
                                                                                                    "unparalleled shopping opportunities, one of the largest trade fair complexes in Europe, a vibrant nightlife,\n" +
                                                                                                    "the prestige of the Teatro alla Scala, the sign of the genius of Leonardo da Vinci,\n" +
                                                                                                    "an almost religious addiction to football and endless possibilities to eat the best of Italian and Lombard cuisine.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Milan";
                                                                                                    break;
                                                                                                }
                                                                                                case 10:{
                                                                                                    System.out.println("Vienna, Austria: 6,303,800 visitors\n" +
                                                                                                    "It is one of the great European capitals, and has been the land of the Habsburg rulers of the Austro-Hungarian Empire for centuries.\n" +
                                                                                                    "Today the memories of that period are carefully preserved by lovers of the Viennese tradition.\n" +
                                                                                                    "Past artistic glories revive thanks to the cultural heritage left by brilliant personalities such as Mozart, Beethoven, Schubert, Strauss and Gustav Klimt.\n" +
                                                                                                    "Today's visitors discover a city with a special grace and a very uniform architectural character, which distinguishes it from other capitals.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Vienna";
                                                                                                    break;
                                                                                                }
                                                                                                case 11:{
                                                                                                    System.out.println("Berlin, Germany: 5,770,900 visitors\n" +
                                                                                                    "Berlin is a young, unpredictable, extravagant destination.\n" +
                                                                                                    "It is a modern city, full of clubs and entertainment, where you can appreciate the various street food, fashion, art, design and music.\n" +
                                                                                                    "All this makes Berlin a real cultural capital.\n" +
                                                                                                    "The symbol of the city is the Brandenburg Gate, which represents the old division of the two Germanys and the subsequent reunion.\n" +
                                                                                                    "Although little of the original remains, there are numerous references to the Second World War and the subsequent division.\n" +
                                                                                                    "Emblematic are the remains of the famous Wall, the Reichstad, Checkpoint Charlie and the Holocaust Memorial.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Berlin";
                                                                                                    break;
                                                                                                }
                                                                                                case 12:{
                                                                                                    System.out.println("Madrid, Spain: 5,512,600 visitors\n" +
                                                                                                    "Madrid is a city full of passion, the one that its citizens will pass on to visitors. It is a modern and cosmopolitan metropolis but, at the same time, there is a great cultural and artistic heritage.\n" +
                                                                                                    "Culture and art, in fact, play a primary role in this city,\n" +
                                                                                                    "where there are 73 museums,\n" +
                                                                                                    "including the Prado Museum, one of the most important art galleries in the world, and the Centro Nacional de Arte Reina Sofía which also boasts important works by Picasso, Joan Miró and Salvador Dalí.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Madrid";
                                                                                                    break;
                                                                                                }
                                                                                                case 13:{
                                                                                                    System.out.println("Venice, Italy: 5,406,800 visitors\n" +
                                                                                                    "Venice is one of the Italian cities present in this Top 25 that is literally invaded by tourists in every season.\n" +
                                                                                                    "On the other hand it is a unique city of its kind! Its particular conformation, where the roads are not made of asphalt but of water channels, remains etched in the memory and in the heart of every visitor.\n" +
                                                                                                    "The beauty of the multitude of monuments and works of art captivates the tourist, as well as Piazza San Marco with its famous Basilica, the beating heart of the city.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Venice";
                                                                                                    break;
                                                                                                }
                                                                                                case 14:{
                                                                                                    System.out.println("Moscow, Russia: 5,404,500 visitors\n" +
                                                                                                    "It is thought that the origins of this city date back to more than eight and a half centuries ago, the first references to Moscow date back to 1147.\n" +
                                                                                                    "It was founded by the prince of Suzdal Yuri Dolgorukiy on the sweeping bend of the Moskva River.\n" +
                                                                                                    "From its humble beginnings (it was initially a hunting village) it has grown steadily to become a fortress city, to then become the nerve center of the entire great Russian nation.\n" +
                                                                                                    "Its beautiful architecture boasts real symbols such as the Kremlin, Red Square and St. Basil's Cathedral.\n" +
                                                                                                    "Moscow is one of the largest cities in the world, has grown rapidly in recent decades and, as a result, includes affluent neighborhoods surrounding a relatively compact historical center stuffed with the undeniable charm of ancient architecture.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Moscow";
                                                                                                    break;
                                                                                                }
                                                                                                case 15:{
                                                                                                    System.out.println("Dublin, Ireland: 5,185,000 visitors\n" +
                                                                                                    "Dublin is a city on a human scale, which welcomes many visitors - especially young people - every year.\n" +
                                                                                                    "It is a fairly small city and is also suitable for a weekend or a few days stay.\n" +
                                                                                                    "It is located at the mouth of the River Liffey and is also remembered for being the birthplace of literary geniuses such as Oscar Wilde, Jonathan Swift, James Joyce and Samuel Beckett. Founded by some Viking populations more than ten centuries ago, it is a city rich in history and culture, but also entertainment!\n" +
                                                                                                    "\"It is certainly known for its very high concentration of pubs and clubs, which makes it practically impossible to go out without knowing what to do, especially in the evening!");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Dublin";
                                                                                                    break;
                                                                                                }
                                                                                                case 16:{
                                                                                                    System.out.println("");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 17:{
                                                                                                    System.out.println("Florence, Italy: 5,015,400 visitors\n" +
                                                                                                    "Here is the first of the Italian cities in this Top 25. What about Florence? Even visiting it several times you would never be able to see everything. The \\ \"cradle of the Renaissance \\\" is one of the main attractions for foreign tourists who intend to visit our beautiful country.\n" +
                                                                                                    "Florence is magnetic, romantic and busy. The center, which can be explored on foot, conquers its guests with its buildings, cafes and restaurants where you can enjoy the best Italian food.\n" +
                                                                                                    "The city is also a perfect destination for luxury shopping.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Florence";
                                                                                                    break;
                                                                                                }
                                                                                                case 18:{
                                                                                                    System.out.println("Munich, Germany: 4,036,700 visitors\n" +
                                                                                                    "Munich has a thousand faces, from that of the affluent to that of Mediterranean-style outdoor cafes, to that of beer flowing in rivers and overflowing with huge mugs.\n" +
                                                                                                    "A trip to this city is very popular, statistics show, in fact, that more and more visitors choose it as a destination, especially during the period of the famous Oktoberfest.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Munich";
                                                                                                    break;
                                                                                                }
                                                                                                case 19:{
                                                                                                    System.out.println("Budapest, Hungary: 4,004,400 visitors\n" +
                                                                                                    "Often referred to as the \\ \"little Paris \\\", Budapest is famous for monuments that tell its thousand-year history.\n" +
                                                                                                    "The capital of Hungary is divided into two areas - Buda and Pest - which extend along the two banks of the Danube and represent two different aspects of the city.\n" +
                                                                                                    "Buda and its historic Castle are made up of medieval streets and houses, museums, caves and Roman ruins. The most dynamic part, Pest, boasts the largest Parliament building in Europe, great streets for riverside walks, flea markets, bookstores, antique shops and delicious cafes.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Budapest";
                                                                                                    break;
                                                                                                }
                                                                                                case 20:{
                                                                                                    System.out.println("St. Petersburg, Russia: 3,996,000 visitors\n" +
                                                                                                    "From the priceless treasures of the famous Hermitage Museum to the palaces and works of art of the Russian Museum, this city is truly spoiled for choice.\n" +
                                                                                                    "Then there is the historical corner: Catherine the Great, Peter the Great and the last Russian tsars left their mark on this great city, as did the great Russian writers like Dostoevsky and Pushkin.\n" +
                                                                                                    "Finally, the nightlife of St. Petersburg is legendary and the locals are famous for being much more relaxed than the citizens of the capital Moscow.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="St. Petersburg";
                                                                                                    break;
                                                                                                }
                                                                                                case 21:{
                                                                                                    System.out.println("Lisbon, Portugal: 3,790,500 visitors\n" +
                                                                                                    "Lisbon is a fascinating city, full of history, where the sun shines 290 days a year and where the temperature rarely drops below 15 degrees. Here the tourist can feel safe wandering through the alleys of the center both day and night.\n" +
                                                                                                    "Let yourself be enchanted by the excellent traditional cuisine at the numerous restaurants for all tastes, budgets and needs.\n" +
                                                                                                    "Lisbon is an authentic city, where traditions and ancient history mix with a lively activity of cultural entertainment and hi-tech innovation.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Lisbon";
                                                                                                    break;
                                                                                                }
                                                                                                case 22:{
                                                                                                    System.out.println("");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 23:{
                                                                                                    System.out.println("Brussels, Belgium: 3,074,100 visitors\n" +
                                                                                                    "The charming capital of Belgium is a historic and fashionable city, bureaucratic but at the same time bizarre, extremely multicultural.\n" +
                                                                                                    "Behind all of this is an urban landscape that swings from majestic to eccentric.\n" +
                                                                                                    "You can see the facades of buildings in Art Nouveau and royal buildings of the nineteenth century, which contrast with the medieval core of Brussels where there is the Grand Place, one of the most beautiful squares in the world.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Brussels";
                                                                                                    break;
                                                                                                }
                                                                                                case 24:{
                                                                                                    System.out.println("");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 25:{
                                                                                                    System.out.println("Krakow, Poland: 2,937,200 visitors\n" +
                                                                                                    "If you believe the legends, Krakow was born from the defeat of a dragon. Despite the legend, it is true that a mythological atmosphere reigns in the streets of the city.\n" +
                                                                                                    "Wawel Castle is a major attraction, while towering churches, impressive museums and the vast Rynek Glowny, the largest market square in Europe, are in the historic center.\n" +
                                                                                                    "In the first Jewish quarter, Kazimierz, the still standing synagogues reflect the tragedy of the Second World War, just as its lively squares and alleys symbolize 21st century renewal.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Krakow";
                                                                                                    break;
                                                                                                }
                                                                                            }
                                                                                            if(destinationChoice!=null){
                                                                                              if(destinationChoice.compareToIgnoreCase("y") == 0){
                                                                                                    int id = idTravel;
                                                                                                    do {
                                                                                                        startDayOfMonths = InputControlls.inputAnalyzerInt("startDayOfMonths", 1);
                                                                                                        startValueOfMonth = InputControlls.inputAnalyzerInt("startValueOfMonth", 1);
                                                                                                        startYear = InputControlls.inputAnalyzerInt("startYear", 1);
                                                                                                        dateCorrect = isDataValida(startDayOfMonths, startValueOfMonth, startYear);
                                                                                                        if (dateCorrect) {
                                                                                                            break;
                                                                                                        } else {
                                                                                                            System.out.println("ERROR: invalid date");
                                                                                                        }
                                                                                                    } while (!dateCorrect);
                                                                                                    do {
                                                                                                        endDayOfMonths = InputControlls.inputAnalyzerInt("endDayOfMonths", 1);
                                                                                                        endValueOfMonth = InputControlls.inputAnalyzerInt("endValueOfMonth", 1);
                                                                                                        endYear = InputControlls.inputAnalyzerInt("endYear", 1);
                                                                                                        dateCorrect = isDataValida(endDayOfMonths, endValueOfMonth, endYear);
                                                                                                        if (dateCorrect) {
                                                                                                            break;
                                                                                                        } else {
                                                                                                            System.out.println("ERROR: invalid date");
                                                                                                        }
                                                                                                    } while (!dateCorrect);
                                                                                                    try {
                                                                                                        users[i].travelPlanning(destination, id, startDayOfMonths, startValueOfMonth, startYear, endDayOfMonths, endValueOfMonth, endYear);
                                                                                                        System.out.println("the travel ID is: " + id);
                                                                                                        System.out.println("Successful operation, press any key to continue");
                                                                                                        keyboard.nextLine();
                                                                                                        idTravel++;
                                                                                                    } catch (MaximumReached ex) {
                                                                                                        System.out.println(ex.toString());
                                                                                                    } catch (DateTimeException ex) {
                                                                                                        System.out.println("Error: the date is invalid");
                                                                                                        keyboard.nextLine();
                                                                                                    }
                                                                                                    choiceMenu0111=0;
                                                                                                    choiceMenu011=0;
                                                                                                    travelCompleted=true;
                                                                                                }  
                                                                                            }
                                                                                        }while(choiceMenu0111!=0);
                                                                                        break;
                                                                                    }
                                                                                    case 2: {
                                                                                        do{
                                                                                            choiceMenu0111=mAsia.sceltaMenu(0);
                                                                                            switch(choiceMenu0111){
                                                                                                case 0:{
                                                                                                    break;
                                                                                                }
                                                                                            }
                                                                                        }while(choiceMenu0111!=0);
                                                                                        break;
                                                                                    }
                                                                                    case 3: {
                                                                                        do{
                                                                                            choiceMenu0111=mAmerica.sceltaMenu(0);
                                                                                            switch(choiceMenu0111){
                                                                                                case 0:{
                                                                                                    break;
                                                                                                }
                                                                                            }
                                                                                        }while(choiceMenu0111!=0);
                                                                                        break;
                                                                                    }
                                                                                    case 4: {
                                                                                        do{
                                                                                            choiceMenu0111=mAustralia.sceltaMenu(0);
                                                                                            switch(choiceMenu0111){
                                                                                                case 0:{
                                                                                                    break;
                                                                                                }
                                                                                            }
                                                                                        }while(choiceMenu0111!=0);
                                                                                        break;
                                                                                    }
                                                                                    case 5: {
                                                                                        do{
                                                                                            choiceMenu0111=mAfrica.sceltaMenu(0);
                                                                                            switch(choiceMenu0111){
                                                                                                case 0:{
                                                                                                    break;
                                                                                                }
                                                                                            }
                                                                                        }while(choiceMenu0111!=0);
                                                                                        break;
                                                                                    }
                                                                                    case 6: {
                                                                                        do{
                                                                                            choiceMenu0111=mAntarctica.sceltaMenu(0);
                                                                                            switch(choiceMenu0111){
                                                                                                case 0:{
                                                                                                    break;
                                                                                                }
                                                                                            }
                                                                                        }while(choiceMenu0111!=0);
                                                                                        break;
                                                                                    }
                                                                                }
                                                                            }while(choiceMenu011!=0 || !travelCompleted);
                                                                            break;
                                                                        }
                                                                    }
                                                                } catch (ItemNotFound ex) {
                                                                    System.out.println(ex.toString() + " press any key to continue");
                                                                }
                                                                break;
                                                            }
                                                            case 2: {/*cancel a travel*/
                                                                if (users[i].getNumTravelPresent() == 0) {
                                                                    System.out.println("unable to perform this method, enter a trip first");
                                                                    break;
                                                                }
                                                                try {
                                                                    System.out.println(users[i].showTravelsSortedByEntry());
                                                                } catch (NullPointer ex) {
                                                                    System.out.println(ex.toString());
                                                                } catch (travelsNotFound ex) {
                                                                    System.out.println(ex.toString());
                                                                }
                                                                int id = InputControlls.inputAnalyzerInt("enter the ID of the trip you want to delete", 1);
                                                                try {
                                                                    users[i].cancelATravel(id);
                                                                    System.out.println("Successful operation, press any key to continue");
                                                                    keyboard.nextLine();
                                                                } catch (ItemNotFound ex) {
                                                                    System.out.println(ex.toString());
                                                                } catch (travelsNotFound ex) {
                                                                    System.out.println(ex.toString());
                                                                }
                                                                break;
                                                            }
                                                            case 3: {/*show Travels Sorted By Entry*/
                                                                if (users[i].getNumTravelPresent() == 0) {
                                                                    System.out.println("unable to perform this method, enter a trip first");
                                                                    break;
                                                                }
                                                                try {
                                                                    System.out.println(users[i].showTravelsSortedByEntry());
                                                                    System.out.println("\nSuccessful operation, press any key to continue");
                                                                    keyboard.nextLine();
                                                                } catch (NullPointer ex) {
                                                                    System.out.println(ex.toString());
                                                                } catch (travelsNotFound ex) {
                                                                    System.out.println(ex.toString());
                                                                }
                                                                break;
                                                            }
                                                            case 4: {/*show Travels Sorted By Departure*/
                                                                if (users[i].getNumTravelPresent() == 0) {
                                                                    System.out.println("unable to perform this method, enter a trip first");
                                                                    break;
                                                                }
                                                                try {
                                                                    System.out.println(users[i].showTravelsSortedByDeparture());
                                                                    System.out.println("\nSuccessful operation, press any key to continue");
                                                                    keyboard.nextLine();
                                                                } catch (NullPointer ex) {
                                                                    System.out.println(ex.toString());
                                                                } catch (travelsNotFound ex) {
                                                                    System.out.println(ex.toString());
                                                                }
                                                                break;
                                                            }
                                                            case 5: {/*show destination*/
                                                                if (users[i].getNumTravelPresent() == 0) {
                                                                    System.out.println("unable to perform this method, enter a trip first");
                                                                    break;
                                                                }
                                                                System.out.println(users[i].showDestinations());
                                                                break;
                                                            }
                                                            case 6: {/*postpone Travel*/
                                                                if (users[i].getNumTravelPresent() == 0) {
                                                                    System.out.println("unable to perform this method, enter a trip first");
                                                                    break;
                                                                }
                                                                int id = InputControlls.inputAnalyzerInt("enter the ID of the trip you want to postpone", 1);
                                                                do {
                                                                    startDayOfMonths = InputControlls.inputAnalyzerInt("startDayOfMonths", 1);
                                                                    startValueOfMonth = InputControlls.inputAnalyzerInt("startValueOfMonth", 1);
                                                                    startYear = InputControlls.inputAnalyzerInt("startYear", 1);
                                                                    dateCorrect = isDataValida(startDayOfMonths, startValueOfMonth, startYear);
                                                                    if (dateCorrect) {
                                                                        break;
                                                                    } else {
                                                                        System.out.println("ERROR: invalid date");
                                                                    }
                                                                } while (!dateCorrect);
                                                                do {
                                                                    endDayOfMonths = InputControlls.inputAnalyzerInt("endDayOfMonths: ", 1);
                                                                    endValueOfMonth = InputControlls.inputAnalyzerInt("endValueOfMonth: ", 1);
                                                                    endYear = InputControlls.inputAnalyzerInt("endYear: ", 1);
                                                                    dateCorrect = isDataValida(endDayOfMonths, endValueOfMonth, endYear);
                                                                    if (dateCorrect) {
                                                                        break;
                                                                    } else {
                                                                        System.out.println("ERROR: invalid date");
                                                                    }
                                                                } while (!dateCorrect);
                                                                try {
                                                                    users[i].postponeTravel(id, startDayOfMonths, startValueOfMonth, startYear, endDayOfMonths, endValueOfMonth, endYear);
                                                                    System.out.println("\nSuccessful operation, press any key to continue");
                                                                    keyboard.nextLine();
                                                                } catch (ItemNotFound ex) {
                                                                    System.out.println(ex.toString());
                                                                } catch (travelsNotFound ex) {
                                                                    System.out.println(ex.toString());
                                                                } catch (DateTimeException ex) {
                                                                    System.out.println("Error: the date is invalid");
                                                                    keyboard.nextLine();
                                                                }
                                                                break;
                                                            }
                                                            case 7: {/*delete the account*/
                                                                System.out.println("you are sure to delete this account once deleted you will not be able to go back, enter Y / N:");
                                                                String safety = keyboard.nextLine();
                                                                if (safety.compareToIgnoreCase("n") == 0) {
                                                                    System.out.println("no problem, rest assured I have not deleted your account, be careful next time. ;P");
                                                                    keyboard.nextLine();
                                                                    break;
                                                                }
                                                                System.out.println("enter the password to confirm: ");
                                                                String passwordConfirm;
                                                                int ci = 0;
                                                                do {
                                                                    passwordConfirm = keyboard.nextLine();
                                                                    do {
                                                                        if (passwordConfirm.length() < 3) {
                                                                            System.out.println("ERROR: the password cannot be less than 3 characters re-enter the password: ");
                                                                        }
                                                                    } while (passwordConfirm.length() < 3);
                                                                    if (passwordConfirm.compareTo(users[i].getPassword()) != 0) {
                                                                        ci++;
                                                                        System.out.println("you made a mistake the password does not match, try to re-enter the password.");
                                                                        keyboard.nextLine();
                                                                        if (ci == 3) {
                                                                            System.out.println("you are sure to delete this account once deleted you will not be able to go back, enter Y / N:");
                                                                            safety = keyboard.nextLine();
                                                                            ci = 0;
                                                                            if (safety.compareToIgnoreCase("n") == 0) {
                                                                                System.out.println("no problem, rest assured I have not deleted your account, be careful next time. ;P");
                                                                                keyboard.nextLine();
                                                                                break;
                                                                            }
                                                                        }
                                                                        if (safety.compareToIgnoreCase("y") == 0) {
                                                                            System.out.println("you still have " + (3 - ci) + "attempts, enter the password to confirm: ");
                                                                        }
                                                                    }
                                                                } while (passwordConfirm.compareTo(users[i].getPassword()) != 0);
                                                                for (int j = i; j < numberUsersPresent - 1; j++) {
                                                                    users[j] = users[j + 1];
                                                                }
                                                                users[numberUsersPresent - 1] = null;
                                                                numberUsersPresent--;
                                                                choiceMenu01 = 0;
                                                                System.out.println("\nSuccessful operation, press any key to continue");
                                                                keyboard.nextLine();
                                                                break;
                                                            }
                                                        }
                                                    } catch (InputMismatchException | NumberFormatException e1) {
                                                        System.out.println("incorrect input, press any key to continue");
                                                        keyboard.nextLine();
                                                    }
                                                } while (choiceMenu01 != 0);
                                                break;
                                            } else {
                                                passwordCounter++;
                                                System.out.println("Error: check password may be incorrect, you still have "+(3-passwordCounter)+" attempts , try re-entering the password:" );
                                                password=keyboard.nextLine();
                                            }
                                        }
                                    }
                                }
                            }
                            if(counterNotFound){
                                System.out.println("account not found,press any key to continue");
                                keyboard.nextLine();
                            }
                            break;    
                        } 
                        break;
                    }
                    case 2: {
                        String name = InputControlls.inputAnalyzerStringNominative("name", 3);
                        String surname = InputControlls.inputAnalyzerStringNominative("surname", 1);
                        String email = InputControlls.inputAnalyzerString("email", 2);
                        email = controlEmail(N_MAX_USERS, users, email);
                        String password = InputControlls.inputAnalyzerString("password", 8);
                        int id = idUsers;
                        users[numberUsersPresent] = new User(name, surname, password, id, email);
                        idUsers++;
                        numberUsersPresent++;
                        c2 = true;
                        break;
                    }
                    case 3: {
                        try {
                            Main.salvaClassInFileCSV(nsmeFileTxt, numberUsersPresent, users);
                            System.out.println("ok");
                        } catch (IOException ex) {
                            System.out.println(ex.toString());
                        } catch (FileException ex) {
                            System.out.println(ex.toString());
                            keyboard.nextLine();
                        }
                    }
                    case 4: {
                        FileOutputStream f1;
                        try {
                            f1 = new FileOutputStream(nsmeFileBinary);
                            ObjectOutputStream writer=new ObjectOutputStream(f1);
                            writer.writeInt(idTravel);
                            writer.writeInt(idUsers);
                            writer.writeInt(numberUsersPresent);
                            writer.writeObject(users);
                            writer.flush();
                            writer.close();
                            System.out.println("\nSuccessful operation, press any key to continue");
                            keyboard.nextLine();
                        } catch(FileNotFoundException ex) {
                            System.out.println(ex.toString());
                        } catch(IOException ex) {
                            System.out.println(ex.toString());
                        }
                    }
                }
            } while (userChoice != 0);
        } catch (InputMismatchException | NumberFormatException e1) {
            System.out.println("incorrect input, press any key to continue");
            keyboard.nextLine();
        }
    }
    private static boolean isDataValida(int giorno, int mese, int anno) {
        if(giorno<0 || giorno>31) {
            return false;
        }
        if(mese<0 || mese>12) {
            return false;
        }
        if(anno<0 || anno>9999) {
            return false;
        } else {
            LocalDate today=LocalDate.now();
            LocalDate userDate=LocalDate.of(anno,mese,giorno);
            if(userDate.isBefore(today))
                return false;
            else
                return true;
        }
    }
    public static void salvaClassInFileCSV(String nomeFile, int numberUsersPresent, User users[]) throws IOException, FileException {
        if(numberUsersPresent != 0){
            TextFile f1=new TextFile('W',nomeFile);
            for(int i=0;i<numberUsersPresent;i++) {
                if(users[i]!=null){
                    f1.toFile(users[i].getId()+";"+users[i].getName()+";"+users[i].getSurname()+";"+users[i].getEmail()+";"+users[i].getPassword()+";");
                }
            }
            f1.toFile(numberUsersPresent+";");
            f1.close();
        } else {
            throw new exception.FileException("no user present, press any key to continue");
        }
    }
    private static String controlEmail(int N_MAX_USERS, User users[], String email) {
        for (int i=0;i<N_MAX_USERS;i++) {
            if(users[i]!=null){
                if(email.compareToIgnoreCase(users[i].getEmail())==0) {
                    System.out.println("Error: invalid email, re-enter");
                    email=InputControlls.inputAnalyzerString("email", 2);
                    email=controlEmail(N_MAX_USERS, users, email);
                    return email;
                }
            }
        }
        return email;
    }
}