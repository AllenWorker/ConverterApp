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
public class Converter {
    public double firstEle;
    public double secondEle;
    public boolean stat;
    
    
    public Converter(double firstEle, double secondEle, boolean stat) 
    {
        this.firstEle = firstEle;
        this.secondEle = secondEle;
        this.stat = stat;
    }
    
    
    public void dayTimeConvert()
    {
         if (stat) //minute to day
        {
            firstEle = secondEle / 60 / 24;
        }
        else
        {
            secondEle = firstEle * 24 * 60;
        }
    }
    
    public void temperatureConvert()
    {
        if (stat) //fahrenheit to celcius
        {
            firstEle = (secondEle - 32.0)/1.8;
        }
        else
        {
            secondEle = 1.8*firstEle + 32.0;
        }

    }
    
    public void weightConvert()
    {
        if (stat) //pound to kilogram
        {
            firstEle = secondEle * 0.45359237;
        }
        else
        {
            secondEle = firstEle / 0.45359237;
        }

    }
    
    public void lengthConvert()
    {
        if (stat) //inch to centimetre
        {
            firstEle = secondEle * 2.54;
        }
        else
        {
            secondEle = firstEle / 2.54;
        }

    }
    
    public void bytesConvert()
    {
        if (stat) //inch to centimetre
        {
            firstEle = secondEle * 1000;
        }
        else
        {
            secondEle = firstEle / 1000;
        }
    }
    
}
