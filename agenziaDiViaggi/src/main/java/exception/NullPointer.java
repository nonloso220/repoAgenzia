/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author luca gabossi
 */
public class NullPointer extends Exception{
    private int line;
    public NullPointer(int line) {
        this.line=line;
    }
    public int getLine(){
        return this.line;
    }
    @Override
    public String toString() {
        String s=" >>>>>>in that line:"+this.getLine()+"!!!<<<<<< the NullPointerException may have occurred \ncheck the line: >>>>>>"+this.getLine()+"!!!!!<<<<<<";
        return s;
    }
}
