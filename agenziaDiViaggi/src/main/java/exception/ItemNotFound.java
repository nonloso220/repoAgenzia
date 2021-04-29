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
public class ItemNotFound extends Exception{
    private int line;
    private String item;
    public ItemNotFound(int line,String item) {
        this.line=line;
        this.item=item;
    }

    public ItemNotFound(int i, int idTravel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public int getLine(){
        return this.line;
    }
    public String getItem() {
        return this.item;
    }
    @Override
    public String toString() {
        String s=" >>>>>>in that line:"+this.getLine()+"!!!<<<<<< the "+this.getItem()+" not found may have occurred \ncheck the line: >>>>>>"+this.getLine()+"!!!!!<<<<<<";
        return s;
    }
}
