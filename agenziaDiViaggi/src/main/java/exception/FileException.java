package exception;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luca gabossi
 */
public class FileException extends Exception {
    private String massage;
    public FileException(String m) {
        this.massage=m;
    }

    FileException() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public String toString() {
        return massage;
    }
}
