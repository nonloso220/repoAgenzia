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
    private String item;
    public ItemNotFound(String item) {
        this.item=item;
    }
    public String getItem() {
        return this.item;
    }
    @Override
    public String toString() {
        String s=this.getItem()+" not found may have occurred ";
        return s;
    }
}
