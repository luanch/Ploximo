/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploximo.controle;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Luan
 */
public class FormatadorDeDatas {
    
    public static String getDataFormatada(GregorianCalendar gcData){
        StringBuilder dataBuilder = new StringBuilder();
        dataBuilder.append(gcData.get(Calendar.DAY_OF_MONTH));
        dataBuilder.append('.');
        dataBuilder.append(gcData.get(Calendar.MONTH));
        dataBuilder.append('.');
        dataBuilder.append(gcData.get(Calendar.YEAR));
        String data = dataBuilder.toString();
        return data;
    }
}