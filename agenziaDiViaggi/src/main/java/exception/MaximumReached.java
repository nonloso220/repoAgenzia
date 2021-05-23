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
public class MaximumReached extends Exception{
    private int maximum;
    public MaximumReached(int maximum) {
        this.maximum = maximum;
    }
    public int getMaximum() {
        return maximum;
    }
    @Override
    public String toString() {
        String s="array is already fully occupied, if you want to proceed empty it, the maximum is: "+this.getMaximum();
        return s;
    }
}
