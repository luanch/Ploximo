/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploximo.Models;

import javax.swing.JLabel;

/**
 *
 * @author Luan
 */
public class Cronometro extends Thread{
 
     private JLabel hr;
    public Cronometro(JLabel hora) {
        this.hr = hora;
    }
    @Override
    public void run() {
        try {
    	   int segundo = 0;
           int hora = 0;
           int minuto = 5;
           while( true ) {
                Thread.sleep(1000);
                segundo--;
                if( segundo < 0){
                    segundo = 59;
                    minuto = minuto-1;
                }
                if( minuto == 0 ){
                    minuto = 59;
                    hora = hora-1;
                }
                String timer =  completaComZero(minuto) + ":" +
                                completaComZero(segundo);
                this.hr.setText(timer);
                this.hr.revalidate();
            }
        } catch (InterruptedException ex) {
           ex.printStackTrace();
        }
    }
    private String completaComZero(Integer i) {
        String retorno = null;
        if( i < 10 ) {
            retorno = "0"+i;
        } else {
            retorno = i.toString();
        }
        return retorno;
    }
}
