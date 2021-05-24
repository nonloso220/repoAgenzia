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
        String[] asia=new String[26];
        asia[0]="come back";
        asia[1]="Bali";
        asia[2]="Jeju Island";
        asia[3]="Phuket";
        asia[4]="Kyoto";
        asia[5]="Sa Pa";
        asia[6]="Tokyo";
        asia[7]="Hanoi";
        asia[8]="Hong Kong";
        asia[9]="Bangkok";
        asia[10]="Osaka";
        asia[11]="Angkor Wat e Siem Reap";
        asia[12]="Singapore";
        asia[13]="Nagarkot";
        asia[14]="Seul";
        asia[15]="Luang Prabang";
        asia[16]="Okinawa";
        asia[17]="Maldives";
        asia[18]="Koh Rong Island";
        asia[19]="Boracay";
        asia[20]="Chiang Rai";
        asia[21]="Cebu e Bohol";
        asia[22]="Penang";
        asia[23]="Varanasi";
        asia[24]="Kuala Lumpur";
        asia[25]="Kerala";	
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
                                                                                            String destination = null;
                                                                                            String destinationChoice = null;
                                                                                            switch(choiceMenu0111){
                                                                                                case 0:{
                                                                                                    break;
                                                                                                }
                                                                                                case 1:{
                                                                                                    System.out.println("Bali, Indonesia\nBali is perhaps the closest place to paradise. It really lacks nothing: beaches, surfing, diving, yoga, temples in the hills, picturesque rice fields, traditional villages and mouth-watering gastronomy. It is truly a place to recharge your batteries.\n" +
                                                                                                    "\n" +
                                                                                                    "Venture outside the tourist areas, stay in a village with the locals and let yourself be carried away by spirituality.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="bali";
                                                                                                    break;
                                                                                                }
                                                                                                case 2:{
                                                                                                    System.out.println("Jeju Island, South Korea\nDo you think it is impossible to find a tropical island of breathtaking natural beauty in South Korea? Well, Jeju Island is just that.\n There are paradisiacal beaches, rapeseed fields,\nnumerous waterfalls and an entire park overflowing with statues representing the male reproductive organ.\n No, it is not a joke.\n Don't forget to taste the typical hallabong, a very sweet and seedless type of mandarin.\n");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="jeju island";
                                                                                                    break;
                                                                                                }
                                                                                                case 3:{
                                                                                                    System.out.println("Phuket, Thailandia\nGolden beaches and a myriad of islands await you in this corner of paradise.\n Phuket is a very popular destination and there are almost all the tourist stereotypes, but it is still a magnificent place.\n With landscapes such as those of the Phi Phi Islands and the Hong Islands, white sand beaches as far as the eye can see,\n protected areas and national parks, you will certainly not be bored.\n If you are looking for a luxury hotel, you will be spoiled for choice in Phuket.\n");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="phuket";
                                                                                                    break;
                                                                                                }
                                                                                                case 4:{
                                                                                                    System.out.println("Kyoto, Japan\nIf you want a taste of Japan of yesteryear, visit Kyoto. Here you will see splendid temples such as that of Kinkaku-ji, a veritable triumph of gold leaf, but also magnificent Zen gardens, welcoming tea rooms and numerous torii portals to mark the entrance to the temples.\n" +
                                                                                                    "\n" +
                                                                                                    "Kyoto is also the beating heart of traditional craftsmanship and, in the historic district of Gion, you will find specialized shops where you can buy handmade paper, traditional Japanese ceramics and lacquered objects, copper teapots, kimonos, green tea, paper fans, prints on wooden blocks… Alternatively, pop into the Kyoto Handicraft Center and you are sure to find a unique souvenir to take home.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="kyoto";
                                                                                                    break;
                                                                                                }
                                                                                                case 5:{
                                                                                                    System.out.println("Sa Pa, Vietnam\nIn the northern part of Vietnam is Sa Pa, a magnificent valley dotted with villages and lush rice fields. Most travelers come here for trekking. It is in fact possible to book guided tours according to the level of difficulty. Combine trekking with a family stay to have the opportunity to share a meal with the locals and visit their homes.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="sa pa";
                                                                                                    break;
                                                                                                }
                                                                                                case 6:{
                                                                                                    System.out.println("Tokyo, Japan\nFew places on our planet are as fascinating as Tokyo. Probably, the largest city in the world is not exactly what you imagine. Of course, there are some chaotic areas where you need to keep your eyes open, but there are also many places that are much calmer and full of new things to try. Robot bars and restaurants, Shinto shrines, narrow alleys, mega malls, lantern-lit noodle bars, small craft shops, arcades, anime comic shops, Michelin-starred restaurants on every corner… all in the same city.\n" +
                                                                                                    "\n" +
                                                                                                    "If you can only visit one destination in Asia, make it Tokyo.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="tokyo";
                                                                                                    break;
                                                                                                }
                                                                                                case 7:{
                                                                                                    System.out.println("Hanoi, Vietnam\nVietnam's capital is a flurry of scooters, street vendors and locals zigzagging through busy streets carrying freshly picked fruit. The historic center is a riot of distinctly French colonial architecture. Get away from the hustle and bustle of the city by visiting the many temples and watching life flow around you from the shores of Hoan Kiem Lake.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="hanoi";
                                                                                                    break;
                                                                                                }
                                                                                                case 8:{
                                                                                                    System.out.println("Hong Kong\nHong Kong is one of the most important financial centers in East Asia, as well as a port of transition from China to the rest of the world, a global identity clearly visible in its gastronomy. Expect delicious dishes that combine Cantonese, Japanese, French and Sichuan culinary traditions. Whatever you feel like, you will surely find it in Hong Kong.\n" +
                                                                                                    "\n" +
                                                                                                    "Hop on the double-decker trams, stop at every market and taste everything from wonton noodles to \"stinky tofu\".");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="hong kong";
                                                                                                    break;
                                                                                                }
                                                                                                case 9:{
                                                                                                    System.out.println("Bangkok, Thailandia\nYou've probably heard of Bangkok's street food - it's all true. The flavors can be quite intense, in an explosion of sensations for the taste buds: spicy, sour, sweet and salty are never lacking. All the regional Thai dishes are represented on the streets of Bangkok and, with a bit of courage, you can try some of the spiciest curries you have ever tasted. Spend your evenings enjoying the view from the many rooftop bars that overlook Bangkok, visit ancient temples or simply let yourself be captivated by the atmosphere of a historic city transformed into a metropolis that never sleeps.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="bangkok";
                                                                                                    break;
                                                                                                }
                                                                                                case 10:{
                                                                                                    System.out.println("Osaka, Japan\nOsaka residents are said to spend most of their money on food: there is even a Japanese proverb that says “Dress to ruin in Kyoto, eat to ruin in Osaka”. Osaka takes food very seriously and the vibrant Dōtonbori area is the perfect example of this. Here, street food reaches stratospheric levels. People queue late into the night to enjoy takoyaki (small balls of soft batter filled with octopus chunks), okonomiyaki (cabbage pancakes made with wheat flour, eggs and sweet potatoes and served with various toppings) and kushikatsu (skewers breaded and fried).");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="osaka";
                                                                                                    break;
                                                                                                }
                                                                                                case 11:{
                                                                                                    System.out.println("Angkor Wat e Siem Reap, Cambodia\nAngkor Wat is a must-see for most travelers visiting Cambodia, and you certainly can't blame them. Its mammoth size leaves you speechless: it is four times larger than the Vatican. During your visit, make a stop in Siem Reap, popular with backpackers and overflowing with hostels to party in, as well as high-end gastronomy, spas, local tours suitable for all types of travelers, a lively life nightlife and great shopping.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Angkor Wat e Siem Reap";
                                                                                                    break;
                                                                                                }
                                                                                                case 12:{
                                                                                                    System.out.println("Singapore\nMost people stop here in between flights, but Singapore definitely deserves more recognition as a destination in its own right. With an area of just two-thirds that of Hong Kong, the island and city-state of Singapore still manages to find the right balance between the concrete jungle and the green that gives it oxygen. Look up and admire the lush gardens that sit at the top or sides of the skyscrapers. Just outside the city, you will find Singapore's largest green lung: the Singapore Botanic Gardens, a UNESCO-protected heritage.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="singapore";
                                                                                                    break;
                                                                                                }
                                                                                                case 13:{
                                                                                                    System.out.println("Nagarkot, Nepal\nWhen one thinks of Nepal, almost everyone thinks of the peaks of the Himalayas. This majestic mountain range includes some of the highest peaks in the world, and great rivers such as the Ganges and the Indus originate from the Himalayas. Nagarkot is one of the best spots to admire the Himalayas directly from the terrace of your hotel. Located just 32 km from Kathmandu, this village is teeming with hotels lined up along a ridge that allows you to enjoy endless views of eight mountain ranges, including Everest.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="nagarkot";
                                                                                                    break;
                                                                                                }
                                                                                                case 14:{
                                                                                                    System.out.println("Seoul, South Korea\nKingdom of K-pop, chic boutiques and the latest tech trends, Seoul will keep you busy with a never-ending stream of new impressions. Over the past decade, the city has tried to leave its industrial past behind and create more innovative recreation areas, such as the flashy Dongdaemun Design Plaza & Park, the new city hall and the landscaped parks along the Cheong-gye stream and river. Han. Although the city has a decidedly modern atmosphere, you can still find traditional palaces and temples, such as the Bongeunsa temple and the Gyeongbokgung palace.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="seoul";
                                                                                                    break;
                                                                                                }
                                                                                                case 15:{
                                                                                                    System.out.println("Luang Prabang, Laos\nLaos is one of the few countries to still preserve the ancient traditions. With its golden wats, orange-clad monks and traditional wooden mansions set against a backdrop of towering mountains, Luang Prabang is the ideal place to spend some time away from the rest of the world. The practice of Buddhism is still widespread in this area and is considered a real way of life, not just a religion. If you are interested, you can participate in a Tak bat morning ceremony, during which the monks collect alms.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Luang Prabang";
                                                                                                    break;
                                                                                                }
                                                                                                case 16:{
                                                                                                    System.out.println("Okinawa, Japan\nOkinawa is the Japanese version of Hawaii. Even Pikachu is wearing a Hawaiian shirt in the Pokemon shop. Okinawa is a tropical island with beautiful beaches, palm trees, turquoise waters and magnificent coral reefs: the perfect setting for surfing and diving. Another gem is the salt rich in minerals which, according to some, would be the secret of the longevity of the local population. Get your daily dose of this prodigious salt, perhaps in the form of salt ice cream, an Okinawan specialty.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Okinawa";
                                                                                                    break;
                                                                                                }
                                                                                                case 17:{
                                                                                                    System.out.println("Maldive\nHoneymooners have been choosing the Maldives for decades now, and it's certainly not a random choice. The water and the beaches are divine. Difficult to find an equally dreamy place. If you love luxury resorts, the Maldives will not disappoint you. Flawless white sand beaches, turquoise waters and colorful fish… whichever island you choose, you will be able to admire all three of these natural wonders. Each resort is a private island and there are more than 100 to choose from. The hard part will be… choosing!");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Maldive";
                                                                                                    break;
                                                                                                }
                                                                                                case 18:{
                                                                                                    System.out.println("Island of KIsolaoh Rong, Cambodia\nFor a holiday full of sand and sea, try the island of Koh Rong. Seeing the pristine beaches and crystal clear water, you might think you are in the Maldives. The only difference is the lack of bungalows suspended over the water. And much cheaper prices.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Island of KIsolaoh Rong";
                                                                                                    break;
                                                                                                }
                                                                                                case 19:{
                                                                                                    System.out.println("Boracay, Philippines\nDespite its small size, the small island of Boracay has 13 beaches. That of White Sand is undoubtedly the most popular; if you are looking for perfect sand and beach parties that last until the first light of dawn, this is the right place for you.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Boracay";
                                                                                                    break;
                                                                                                }
                                                                                                case 20:{
                                                                                                    System.out.println("Chiang Rai, Thailandia\nDo you want to touch the authentic old Thailand before the arrival of tourism? In the north of the country it is still possible, at least in part. Although Chiang Rai is one of the favorite destinations for tourists, it still exudes an authentic atmosphere. The night market, for example, is also popular with locals, not just tourists. Prices are much lower than in the south of the country: you can enjoy a delicious meal for two for around € 2.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Chiang Rai";
                                                                                                    break;
                                                                                                }
                                                                                                case 21:{
                                                                                                    System.out.println("Cebu e Bohol, Philippines\nIf you are looking for a place to dive, kayak and paddleboard, or if you want to relax on the beach on and off the traditional tourist circuits, Cebu and Bohol are your ideal destinations in the Philippines. Not to mention the opportunity to see one of the most adorable animals on earth: the tarsier, an all eyes and tail animal!");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Cebu e Bohol";
                                                                                                    break;
                                                                                                }
                                                                                                case 22:{
                                                                                                    System.out.println("Penang, Malaysia\nPenang, an undisputed paradise for lovers of good food, is one of the main melting pots of oriental cultures in Asia. Its beating heart is George Town, a UNESCO-protected site overflowing with Chinese shop houses, churches, Indian shrines, Buddhist temples, mosques and impressive British-style colonial architecture. Hire a chauffeur-driven trisciò and discover a slice of this multicultural society.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Penang";
                                                                                                    break;
                                                                                                }
                                                                                                case 23:{
                                                                                                    System.out.println("Varanasi, India\nVaranasi is one of the holiest places for Hindus and the Ganges is a favorite destination for pilgrims who come here to wash away their sins and cremate their loved ones. Visiting Varanasi is a spectacular and intense experience at the same time: the rituals in which life and death intertwine take place in public spaces, so get ready to experience strong emotions and let yourself be overwhelmed with all your senses. If it all sounds too intense, remember that Varanasi is also home to a wonderful community of yoga and veganism practitioners: if you want to nourish your mind, body and spirit, you will fall in love with Varanasi.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Varanasi";
                                                                                                    break;
                                                                                                }
                                                                                                case 24:{
                                                                                                    System.out.println("Kuala Lumpur, Malaysia\nRooftop pools and bars with stunning city views… what more do you want from life? Kuala Lumpur is an ideal destination for a stopover between flights. Spend a couple of days shopping for designer bargains in the many mega-malls and indulge in freshly cooked dishes in traditional kopitiam (coffee shops). In the evening, relax by the pool and enjoy the experience.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Kuala Lumpur";
                                                                                                    break;
                                                                                                }
                                                                                                case 25:{
                                                                                                    System.out.println("Kerala, India\nUnsurprisingly, Kerala has been called \"God's country\". By boat, you can cross enchanted landscapes: lagoons alongside palm groves, protected wildlife oases complete with elephants, exotic birds and even tigers, as well as the magnificent rituals of kathakali temples in which music, dance and extravagant costumes are combined are just a few attractions that give this place something otherworldly. Add the flavors of cardamom, cinnamon, black pepper and ginger and your holiday is complete.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="Kerala";
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
                                                                                    case 3: {
                                                                                        do{
                                                                                            choiceMenu0111=mAmerica.sceltaMenu(0);
                                                                                            String destination = null;
                                                                                            String destinationChoice = null;
                                                                                            switch(choiceMenu0111){
                                                                                                case 0:{
                                                                                                    break;
                                                                                                }
                                                                                                case 1:{
                                                                                                    System.out.println("");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 2:{
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
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
                                                                                    case 4: {
                                                                                        do{
                                                                                            choiceMenu0111=mAustralia.sceltaMenu(0);
                                                                                            String destination = null;
                                                                                            String destinationChoice = null;
                                                                                            switch(choiceMenu0111){
                                                                                                case 0:{
                                                                                                    break;
                                                                                                }
                                                                                                case 1:{
                                                                                                    System.out.println("");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 2:{
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
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
                                                                                    case 5: {
                                                                                        do{
                                                                                            choiceMenu0111=mAfrica.sceltaMenu(0);
                                                                                            String destination = null;
                                                                                            String destinationChoice = null;
                                                                                            switch(choiceMenu0111){
                                                                                                case 0:{
                                                                                                    break;
                                                                                                }
                                                                                                case 1:{
                                                                                                    System.out.println("");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 2:{
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
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
                                                                                    case 6: {
                                                                                        do{
                                                                                            choiceMenu0111=mAntarctica.sceltaMenu(0);
                                                                                            String destination = null;
                                                                                            String destinationChoice = null;
                                                                                            switch(choiceMenu0111){
                                                                                                case 0:{
                                                                                                    break;
                                                                                                }
                                                                                                case 1:{
                                                                                                    System.out.println("");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 2:{
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("[INFO]do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
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