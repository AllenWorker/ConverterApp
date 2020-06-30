/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

/**
 *
 * @author allen
 */
public class History {
    private String fromValue;
    private String  fromUnitOfMeasure;
    private String  toValue;
    private String  toUnitOfMeasure;
    
    public History (String fromValue, String fromUnitOfMeasure, String toValue, String toUnitOfMeasure)
    {
        this.fromValue = fromValue;
        this.fromUnitOfMeasure = fromUnitOfMeasure;
        this.toValue = toValue;
        this.toUnitOfMeasure = toUnitOfMeasure;
    }
    
    public String toString()
    {
        return fromValue + " " + fromUnitOfMeasure + "\t" + toValue + " " + toUnitOfMeasure;
    }
}
