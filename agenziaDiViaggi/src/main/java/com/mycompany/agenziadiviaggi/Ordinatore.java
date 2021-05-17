/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenziadiviaggi;

/**
 *
 * @author luca
 */
public class Ordinatore {
    public static void scambia(User[] v,int posizione1,int posizione2)
    {
        User c;
        c=v[posizione1];
        v[posizione1]=v[posizione2];
        v[posizione2]=c;
    }
    public static void scambia(Travel[] v,int posizione1,int posizione2)
    {
        Travel c;
        c=v[posizione1];
        v[posizione1]=v[posizione2];
        v[posizione2]=c;
    }
    public static void scambia(int[] v,int posizione1,int posizione2)
    {
        int c;
        c=v[posizione1];
        v[posizione1]=v[posizione2];
        v[posizione2]=c;
    }
    public static void scambia(String[] v,int posizione1,int posizione2)
    {
        String c;
        c=v[posizione1];
        v[posizione1]=v[posizione2];
        v[posizione2]=c;
    }
    public static User[] selectionSortAlphabeticUsers(int n,User users[]){
        User[] ordinato=new User[n];
        for(int i=0;i<n;i++)
            ordinato[i]=users[i];
        for(int i=0;i<ordinato.length-1;i++)
        {
            for(int j=i+1;j<ordinato.length;j++)
            {
                if(ordinato[j].getName().compareToIgnoreCase(ordinato[i].getName())<0){
                    if(ordinato[j].getSurname().compareToIgnoreCase(ordinato[i].getSurname())<0)
                        scambia(ordinato,i,j);
                }   
            }
        }
        return ordinato; 
    }
    public static Travel[] selectionSortStartTravelCrescente(Travel[] a)
    {
        Travel[] ordinato=new Travel[a.length];
        for(int i=0;i<a.length;i++)
            ordinato[i]=a[i];
        for(int i=0;i<ordinato.length-1;i++)
        {
            for(int j=i+1;j<ordinato.length;j++)
            {
                try{
                    if(ordinato[j].getStartTravel().compareTo(ordinato[i].getStartTravel())<0)
                        scambia(ordinato,i,j);
                }catch(NullPointerException ex){
                    return ordinato;
                }
            }
        }
        return ordinato; 
    }
    /*public static Libro[] selectionSortAlfabeticoAutoreTitolo(Libro[] a)
    {
        Libro[] ordinato=new Libro[a.length];
        for(int i=0;i<a.length;i++)
            ordinato[i]=a[i];
        for(int i=0;i<ordinato.length-1;i++)
        {
            for(int j=i+1;j<ordinato.length;j++)
            {
                if(ordinato[j].getAutore().compareToIgnoreCase(ordinato[i].getAutore())<0 || (ordinato[j].getAutore().compareToIgnoreCase(ordinato[i].getAutore())==0 && ordinato[j].getTitolo().compareToIgnoreCase(ordinato[i].getTitolo())<0))
                    scambia(ordinato,i,j);
            }
        }
        return ordinato; 
    }*/
    //ordina in modo crescente in base allalfabeto un array di stringa
    public static String[] selectionSortCrescente(String[] a)
    {
        String[] ordinato=new String[a.length];
        for(int i=0;i<a.length;i++)
            ordinato[i]=a[i];
        for(int i=0;i<ordinato.length-1;i++)
        {
            for(int j=i+1;j<ordinato.length;j++)
            {
                if(ordinato[j].compareToIgnoreCase(ordinato[i])<0)
                    scambia(ordinato,i,j);
            }
        }
        return ordinato; 
    }
    //ordina in modo decrescente in base allalfabeto un array di stringa
    public static String[] selectionSortDecrescente(String[] a)
    {
        String[] ordinato=new String[a.length];
        for(int i=0;i<a.length;i++)
            ordinato[i]=a[i];
        for(int i=0;i<ordinato.length-1;i++)
        {
            for(int j=i+1;j<ordinato.length;j++)
            {
                if(ordinato[j].compareToIgnoreCase(ordinato[i])>0)
                    scambia(ordinato,i,j);
            }
        }
        return ordinato; 
    }
    //ordina in modo crescente in base hai numeri reali un array di interi
    public static int[] selectionSortCrescente(int[] a)
    {
        int[] ordinato=new int[a.length];
        for(int i=0;i<a.length;i++)
            ordinato[i]=a[i];
        for(int i=0;i<ordinato.length-1;i++)
        {
            for(int j=i+1;j<ordinato.length;j++)
            {
                if(ordinato[j]<ordinato[i])
                    scambia(ordinato,i,j);
            }
        }
        return ordinato; 
    }
    //ordina in modo decrescente in base allalfabeto un array di interi
    public static int[] selectionSortDecrescente(int[] a)
    {
        int[] ordinato=new int[a.length];
        for(int i=0;i<a.length;i++)
            ordinato[i]=a[i];
        for(int i=0;i<ordinato.length-1;i++)
        {
            for(int j=i+1;j<ordinato.length;j++)
            {
                if(ordinato[j]>ordinato[i])
                    scambia(ordinato,i,j);
            }
        }
        return ordinato; 
    }
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    //tecnica bubble 
    public static int[] bubbleSortCrescente(int[] a)
    {
        int[] ordinato=new int[a.length];
        boolean scambioAvvenuto=true;
        for(int i=0;i<a.length;i++)
            ordinato[i]=a[i];
        do
        {
            scambioAvvenuto=false;
            for(int i=0;i<ordinato.length-1;i++)
            {
                if(ordinato[i]>ordinato[i+1])
                {
                    scambioAvvenuto=true;
                    scambia(ordinato, i, i+1);
                }
                    
            }
        }while(scambioAvvenuto);
        return ordinato; 
    }
    public static int[] bubbleSortDecrescente(int[] a)
    {
        int[] ordinato=new int[a.length];
        boolean scambioAvvenuto=true;
        for(int i=0;i<a.length;i++)
            ordinato[i]=a[i];
        do
        {
            scambioAvvenuto=false;
            for(int i=0;i<ordinato.length-1;i++)
            {
                if(ordinato[i]<ordinato[i+1])
                {
                    scambioAvvenuto=true;
                    scambia(ordinato, i, i+1);
                }     
            }
        }while(scambioAvvenuto);
        return ordinato; 
    }
    public static String[] bubbleSortCrescente(String[] a)
    {
        String[] ordinato=new String[a.length];
        boolean scambioAvvenuto=true;
        for(int i=0;i<a.length;i++)
            ordinato[i]=a[i];
        do
        {
            scambioAvvenuto=false;
            for(int i=0;i<ordinato.length-1;i++)
            {
                if(ordinato[i].compareToIgnoreCase(ordinato[i+1])<0)
                {
                    scambioAvvenuto=true;
                    scambia(ordinato, i, i+1);
                }
            }
        }while(scambioAvvenuto);
        return ordinato; 
    }
    public static String[] bubbleSortDecrescente(String[] a)
    {
        String[] ordinato=new String[a.length];
        boolean scambioAvvenuto=true;
        for(int i=0;i<a.length;i++)
            ordinato[i]=a[i];
        do
        {
            scambioAvvenuto=false;
            for(int i=0;i<ordinato.length-1;i++)
            {
                if(ordinato[i].compareToIgnoreCase(ordinato[i+1])>0)
                {
                    scambioAvvenuto=true;
                    scambia(ordinato, i, i+1);
                }
            }
        }while(scambioAvvenuto);
        return ordinato; 
    }
}
