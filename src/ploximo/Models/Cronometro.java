/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploximo.Models;

/**
 *
 * @author Luan
 */
public class Cronometro extends Thread{
    public Cronometro() {    
    }  
  
    public String iniciar() {  
       try {  
            int segundo = 0;  
            int minuto = 5;  
            while( true ) {  
                Thread.sleep(1000);      
                segundo--;  
                if( segundo < 0 ){  
                    segundo = 59;  
                    minuto = minuto-1;  
                }  
                String timer = completaComZero(minuto) + ":" +  
                               completaComZero(segundo);  
                return timer;
            }  
        } catch (InterruptedException ex) {  
            System.out.println("Erro no Cronômetro");
            return "";
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
