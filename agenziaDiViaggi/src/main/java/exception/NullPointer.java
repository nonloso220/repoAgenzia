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
    public NullPointer() {
    }
    @Override
    public String toString() {
        String s="the NullPointerException may have occurred ";
        return s;
    }
}
