package file;


import exception.*;
import java.io.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * *
 * @author luca gabossi
 */
public class TextFile {
    private char mode;
    private BufferedReader reader;
    private BufferedWriter writer;
    public TextFile(char mode,String nomeFile) throws IOException {
        this.mode = 'R';
        if(mode=='w' || mode=='W')
            this.mode='W';
        if(this.mode=='R')
            reader=new BufferedReader(new FileReader(nomeFile)); 
        else
            writer=new BufferedWriter(new FileWriter(nomeFile)); 
    }
    public TextFile(char mode,String nomeFile,boolean append) throws IOException {
        this.mode = 'R';
        if(mode=='w' || mode=='W')
            this.mode='W';
        if(this.mode=='R')
            reader=new BufferedReader(new FileReader(nomeFile)); 
        else
            writer=new BufferedWriter(new FileWriter(nomeFile, append)); 
    }
    public void toFile(String line) throws IOException, FileException{
        if(mode=='R')
            throw new FileException("impossibile scrivere sul file, perché è aperto in lettura");
        writer.write(line);
        writer.newLine();
    }
    public String fromFile() throws FileException, IOException{
        if(mode=='W')
            throw new FileException("impossibile leggere sul file, perché è aperto in scrittura");
        String s=reader.readLine();
        if(s==null)
            throw new FileException("fine deln file raggiunta");
        return s;
    }
    public void close() throws IOException{
        if(mode=='R')
            reader.close();
        else
            writer.close();
    }
}
