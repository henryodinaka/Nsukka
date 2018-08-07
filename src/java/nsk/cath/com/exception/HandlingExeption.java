/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsk.cath.com.exception;

/**
 *
 * @author LEOGOLD
 */
public class HandlingExeption extends Exception{

    public HandlingExeption() {
    }

    public HandlingExeption(String string) {
        super(string);
    }

    public HandlingExeption(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public HandlingExeption(Throwable thrwbl) {
        super(thrwbl);
    }

    public HandlingExeption(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
    
}
