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
                                                                            do{
                                                                                choiceMenu011 = mDestinescion.sceltaMenu(0);
                                                                                switch(choiceMenu011){
                                                                                    case 0: {/*come back*/
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
                                                                                                    System.out.println("Londra, Regno Unito: 20.715.900 visitatori\nLa capitale dell'Inghilterra si presenta come un'immensa metropoli ed offre davvero tanto al visitatore, per questo è al primo posto di questa classifica. Il British Museum, ad ingresso gratuito, è un luogo affascinante dove sono conservati numerosi reperti archeologici, fra cui la famosa Stele di Rosetta. Westminster Abbey è la storica cattedrale londinese, ma chi ama le passeggiate nel verde apprezzerà senza dubbio Hyde Park .\n" +
"Immancabile è una visita a Buckingham Palace, magari durante il cambio della guardia. A Piccadilly Circus potrete dedicarvi allo shopping, così come presso i grandi magazzini Harrods e a Camden Town.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 2:{
                                                                                                    System.out.println("Parigi, Francia: 16.863.500 visitatori\nQuante proposte di matrimonio o dichiarazioni d'amore saranno state declamate alla luce della Tour Eiffel?\n" +
"Il meglio della storia e della cultura europee si condensa in questa città, chi la visita può godere di luoghi di interesse incredibili come il Museo del Louvre ma anche decidere di perdersi lungo quartieri affascinanti come Montmartre o il Marais. Il massimo del romanticismo la città lo offre lungo la Senna, con alcuni scorci davvero pittoreschi, i suoi giardini curati e i tantissimi bistrot all'aperto.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 3:{
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 4:{
                                                                                                    
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 5:{
                                                                                                    System.out.println("Roma, Italia: 9.703.200 visitatori\nChe dire di Roma, che non è ancora stato detto? Un museo a cielo aperto, l'hanno definita, e come smentire questa affermazione? Quella che è universalmente considerata una delle città più belle del mondo - se non addirittura la più bella - è la città eterna, Caput Mundi, la città de \"La dolce vita\"… I turisti vengono per il Colosseo, Piazza Navona, il Pantheon, la Fontana di Trevi, i Musei Vaticani, la scalinata di Piazza di Spagna, la Cappella Sistina… e se ne vanno con il suo cielo azzurro ed i tramonti lungo il Tevere nel cuore.");
                                                                                                    
                                                                                                   destinationChoice=InputControlls.inputAnalyzerStringNominative("do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 6:{
                                                                                                    System.out.println("Praga, Repubblica Ceca: 9.038.900 visitatori\nIl Municipio della Città Vecchia con il famoso orologio astronomico di Praga, le stradine tortuose del quartiere ebraico, già conosciute da chi ha letto i romanzi di Franz Kafka, immersi nella leggenda del Golem, i caffè che solo a guardarli invitano ad entrare e sedersi, le boutique, le crociere sulla Moldava… Queste sono solo alcune delle attrattive che rendono praga una delle città più visitate al mondo.\n" +
"Il Ponte Carlo è un maestoso ponte gotico e la chiesa di San Nicola a Mala Strana è la più bella chiesa barocca di Praga.\n" +
"La città si presenta come mutevole, romantica e di successo, antica e moderna, ma soprattutto cosmopolita in tutto e per tutto.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 7:{
                                                                                                     System.out.println("Amsterdam, Paesi Bassi: 8.476.600 visitatori\nCon i suoi canali ed i musei di fama mondiale, Amsterdam accoglie milioni di visitatori in ogni stagione. Il centro storico la rende una delle città più romantiche e belle d'Europa: le crociere lungo i suoi canali sono un modo alternativo, ammaliante e diverso per visitare la città. Amsterdam è anche una città di tolleranza di diversità, oltre a godere di tutti i vantaggi di una grande centro: cultura ricca, vivace vita notturna, ristoranti internazionali, trasporti pubblici efficienti.\n" +
"Oltre a questo, è anche relativamente tranquilla e, in gran parte grazie ai suoi ampi canali, ha pochissimo traffico stradale. In compenso, provate l'emozione di percorrerla in bicicletta, unendovi al flusso delle centinaia di cittadini che la utilizzano ogni giorno!");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 8:{
                                                                                                    System.out.println("Barcellona, Spagna: 6.726.000 visitatori\nBarcellona possiede una ricca eredità, sia culturalmente che a livello storico. Barcellona ha così tante attrazioni che scegliere quali citare è una impresa non semplice. Il Barrio Gótico (il quartiere gotico) e le Ramblas sono i luoghi ideali per passeggiare e godersi le bancarelle e gli artisti di strada. La Cattedrale della Sagrada Familia, visibile da praticamente ogni punto della città, è uno dei più rappresentativi monumenti di Barcellona.\n" +
"Nessuna visita a Barcellona può essere considerata completa se non si provano i piatti locali in qualche ristorantino del centro o presso il grande mercato.");
                                                                                                    System.out.println("");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 9:{
                                                                                                    System.out.println("Milano, Italia: 6.513.000 visitatori\nSede della Borsa in Italia, potenza industriale ed arbitro accettato a livello internazionale della moda e del design, Milano è una metropoli ribollente.\n" +
"La grande cattedrale gotica, il Duomo, si trova al centro di questa che un tempo fu una capitale imperiale romana, ed esprime l'amore per la bellezza e la forza che guida ancora la città al giorno d'oggi. I suoi punti di forza sono le collezioni d'arte antica e moderna, la possibilità di shopping senza pari, uno dei più grandi complessi fieristici d'Europa, una frizzante vita notturna, il prestigio del Teatro alla Scala, il segno del genio di Leonardo da Vinci, una dipendenza quasi religiosa dal calcio e infinite possibilità di mangiare il meglio della cucina italiana e lombarda.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 10:{
                                                                                                    System.out.println("Vienna, Austria: 6.303.800 visitatori\nÈ una delle grandi capitali europee, ed è stata per secoli la terra dei governanti asburgici dell'Impero austro-ungarico. Ad oggi i ricordi di quel periodo sono conservati con cura dagli amanti della tradizione viennese. Le passate glorie artistiche rivivono grazie al patrimonio culturale che hanno lasciato personalità geniali come Mozart, Beethoven, Schubert, Strauss e Gustav Klimt. I visitatori di oggi scoprono una città con una grazia speciale e un carattere architettonico molto uniforme, che la distingue dalle altre capitali.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 11:{
                                                                                                    System.out.println("Berlino, Germania: 5.770.900 visitatori\nBerlino è una destinazione giovane, imprevedibile, stravagante. È una città moderna, ricca di locali e divertimenti, dove poter apprezzare il vario street food, la moda, l'arte, il design e la musica. Tutto ciò rende Berlino una vera e propria capitale culturale. Il simbolo della città è la Porta di Brandemburgo, che rappresenta la vecchia divisione delle due Germanie e la successiva riunione.\n" +
"Nonostante rimanga ben poco di originale, sono presenti numerosi riferimenti alla Seconda Guerra Mondiale e alla successiva divisione. Emblematici sono i resti del famoso Muro, il Reichstad, il Checkpoint Charlie e il Memoriale dell'Olocausto.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 12:{
                                                                                                    System.out.println("Madrid, Spagna: 5.512.600 visitatori\nMadrid è una città ricca di passione, quella che trasmetteranno ai visitatori i suoi cittadini. È una metropoli moderna e cosmopolita ma, nel contempo, è presente un grande patrimonio culturale e artistico.\n" +
"La cultura e l'arte, infatti, svolgono un primario ruolo in questa città, presso la quale sono presenti ben 73 musei, tra cui il Museo del Prado, una delle più importanti pinacoteche del mondo, e il Centro Nacional de Arte Reina Sofía che vanta anche importanti opere di Picasso, Joan Miró e Salvador Dalí.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 13:{
                                                                                                    System.out.println("Venezia, Italia: 5.406.800 visitatori\nVenezia è una delle città italiane presenti in questa Top 25 che viene letteralmente invasa dai turisti in ogni stagione. D'altra parte è una città unica nel suo genere! La sua particolare conformazione, dove le strade non sono costituite da asfalto ma da canali d'acqua, resta impressa nella memoria e nel cuore di ogni visitatore.\n" +
"La bellezza della moltitudine di monumenti ed opere d'arte rapisce il turista, così come Piazza San Marco con la sua celebre Basilica, cuore pulsante della città.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 14:{
                                                                                                    System.out.println("Mosca, Russia: 5.404.500 visitatori\nSi pensa che le origini di questa città risalgano a più di otto secoli e mezzo fa, i primi riferimenti a Mosca sono infatti del 1147. Fu fondata dal principe di Suzdal Yuri Dolgorukiy sulla curva ampia del fiume Moscova. Dalle sue umili origini (era inizialmente un villaggio di cacciatori) è cresciuta costantemente fino a diventare una città fortezza, per poi diventare il centro nevralgico di tutta la grande nazione russa.\n" +
"La sua bella architettura vanta veri e propri simboli come il Cremlino, la Piazza Rossa e la Cattedrale di San Basilio. Mosca è una delle più grandi città del mondo, è cresciuta rapidamente negli ultimi decenni e, di conseguenza, comprende quartieri ricchi che circondano un centro storico relativamente compatto farcito dall'innegabile fascino dell'architettura antica.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 15:{
                                                                                                    System.out.println("Dublino, Irlanda: 5.185.000 visitatori\nDublino è una città a misura d'uomo, che accoglie numerosi visitatori - specialmente giovani - ogni anno. È una città abbastanza piccola ed è adatta anche ad un weekend oppure un soggiorno di pochi giorni. Si trova alla foce del fiume Liffey ed è ricordata anche per aver dato i natali a geni letterari come Oscar Wilde, Jonathan Swift, James Joyce e Samuel Beckett. Fondata da alcune popolazioni vichinghe più di dieci secoli fa, è una città ricca di storia e cultura, ma anche divertimenti!\n" +
"È sicuramente nota per l'altissima concentrazione di pub e club, fatto che rende praticamente impossibile uscire senza sapere cosa fare, specialmente la sera!");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 16:{
                                                                                                    System.out.println("");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 17:{
                                                                                                    System.out.println("Firenze, Italia: 5.015.400 visitatori\nEcco la prima delle città italiane in questa Top 25. Che dire di Firenze? Anche visitandola più volte non si riuscirebbe mai a vedere proprio tutto. La \"culla del Rinascimento\" è una delle principali attrattive per i turisti stranieri che intendono visitare il nostro Bel Paese.\n" +
"Firenze è magnetica, romantica e indaffarata. Il centro, percorribile a piedi, conquista i propri ospiti con i suoi palazzi, con i caffè e i ristoranti dove gustare il miglior cibo italiano. La città è inoltre una meta perfetta per lo shopping di lusso.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 18:{
                                                                                                    System.out.println("Monaco di Baviera, Germania: 4.036.700 visitatori\nMonaco di baviera ha mille volti, da quello dei ricchi benestanti a quello dei caffè all'aperto in stile mediterraneo, fino a quello della birra che scorre a fiumi e trabocca da enormi boccali. Un viaggio presso questa città è molto popolare, le statistiche dimostrano, infatti, che sempre più visitatori la scelgono come meta, soprattutto durante il periodo della famosa Oktoberfest.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 19:{
                                                                                                    System.out.println("Budapest, Ungheria: 4.004.400 visitatori\nSpesso indicata come la \"piccola Parigi\", Budapest è famosa per i monumenti che raccontano la sua storia millenaria. La capitale dell'Ungheria si divide in due zone - Buda e Pest - che si estendono lungo le due rive del Danubio e rappresentano due differenti aspetti della città.\n" +
"Buda e il suo storico Castello sono composti da strade medievali e case, musei, grotte e rovine romane. La parte più dinamica, Pest, vanta il più grande edificio del Parlamento in Europa, ottime vie dove effettuare passeggiate lungo il fiume, mercati delle pulci, librerie, negozi di antiquariato e deliziosi caffè.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 20:{
                                                                                                    System.out.println("San Pietroburgo, Russia: 3.996.000 visitatori\nDai tesori inestimabili del famoso Museo dell'Ermitage ai palazzi ed alle opere d'arte del Museo Russo, in questa città c'è davvero l'imbarazzo della scelta.\n" +
"Poi c'è l'angolo storico: Caterina la Grande, Pietro il Grande e gli ultimi zar russi hanno lasciato il loro segno su questa grande città, così come i grandi scrittori russi come Dostoevskij e Puskin. Infine, la vita notturna di San Pietroburgo è leggendaria e la gente del posto è famosa per essere molto più rilassata rispetto ai cittadini della capitale Mosca.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 21:{
                                                                                                    System.out.println("Lisbona, Portogallo: 3.790.500 visitatori\nLisbona è una città affascinante, ricca di storia, dove il sole splende 290 giorni all'anno e dove la temperatura scende raramente sotto 15 gradi. Qui il turista può sentirsi sicuro vagando per i vicoli del centro sia di giorno che di notte.\n" +
"Lasciatevi ammaliare dalla ottima cucina tradizionale presso i numerosi ristoranti per tutti i gusti, budget ed esigenze. Lisbona è una città autentica, dove le tradizioni e la storia antica si mescolano con una vivace attività di intrattenimento culturale ed innovazione hi-tech.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 22:{
                                                                                                    System.out.println("");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 23:{
                                                                                                    System.out.println("Bruxelles, Belgio: 3.074.100 visitatori\nL'affascinante capitale del Belgio è una città storica e alla moda, burocratica ma nello stesso tempo bizzarra, estremamente multiculturale.\n" +
"A sfondo di tutto questo vi è un paesaggio urbano che oscilla dal maestoso all'eccentrico. Si possono notare facciate di palazzi in Art Nouveau e regali palazzi del XIX secolo, che contrastano con il nucleo medievale di Bruxelles dove c'è la Grand Place, una delle più belle piazze del mondo.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 24:{
                                                                                                    System.out.println("");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                                case 25:{
                                                                                                    System.out.println("Cracovia, Polonia: 2.937.200 visitatori\nSe credete alle leggende, Cracovia è nata dalla sconfitta di un drago. Nonostante la leggenda, è vero che nelle strade della città regna un'atmosfera mitologica.\n" +
"Il Castello del Wawel è un importante attrattiva, mentre presso il centro storico sono presenti chiese svettanti, imponenti musei e la vasta Rynek Glowny, la più grande piazza del mercato di tutta Europa.\n" +
"Nel primo quartiere ebraico, Kazimierz, le sinagoghe ancora in piedi riflettono la tragedia della Seconda Guerra Mondiale, proprio come le sue piazze e vicoli animati simboleggiano il rinnovamento XXI secolo.");
                                                                                                    destinationChoice=InputControlls.inputAnalyzerStringNominative("do you want to choose this destination for your trip ???, enter Y / N", 0);
                                                                                                    if(destinationChoice.compareToIgnoreCase("y") == 0)
                                                                                                        destination="";
                                                                                                    break;
                                                                                                }
                                                                                            }
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
                                                                            }while(choiceMenu011!=0);
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