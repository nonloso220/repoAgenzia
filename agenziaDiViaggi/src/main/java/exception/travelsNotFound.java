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
public class travelsNotFound extends Exception{
    public travelsNotFound() {
    }
    @Override
    public String toString() {
        String s="ERROR: add trips before performing this method";
        return s;
    }
}
