/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploximo.controle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 *
 * @author Fernando
 */
public class Data {
    public static GregorianCalendar gerarDataAleatoria(int anoMin, int anoMax){
        Randomizador r = new Randomizador();

        int m;
        m = r.gerarInt(11)+1; // o random de int gera valore entre 0 e o limite, por isso adiconar 1 ao mês
        int d;
        if(m == 2){
            d = r.gerarInt(27)+1;
        }
        else if (m == 4|m == 6|m == 9|m == 11){
            d = r.gerarInt(29)+1;
        }
        else{
            d = r.gerarInt(30)+1;
        }
        int y = r.gerarInt(anoMax-anoMin)+anoMin;
        GregorianCalendar c = (GregorianCalendar) GregorianCalendar.getInstance();
        c.set(GregorianCalendar.DAY_OF_MONTH, d);
        c.set(GregorianCalendar.MONTH,m);
        c.set(GregorianCalendar.YEAR,y); 
        return c;
    }
    
    public static String gerarDataAtual () {
    
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        GregorianCalendar gc = (GregorianCalendar)GregorianCalendar.getInstance();
        return dateFormat.format(gc.getTime());
    }

}
