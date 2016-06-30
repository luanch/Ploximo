/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploximo.Models;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import ploximo.Views.JogoTela;
import ploximo.controle.JogoController;
import ploximo.controle.Pontuacao;

/**
 *
 * @author Luan
 */
public class Cronometro extends Thread{
    private final JogoTela jt;
    private final JogoController jogoController = new JogoController();
    private final JLabel hr;
    private final Pontuacao pt;
    public Cronometro(JogoTela jogoTela, Pontuacao pt) {
        this.jt = jogoTela;
        this.hr = jogoTela.getCronometroLabel();
        this.pt = pt;
    }
    @Override
    public void run() {
        
        try {
    	   int segundo = 0;
           int minuto = 5;
           while(true) {
                Thread.sleep(1000);
                segundo--;
                if( segundo < 0){
                    segundo = 59;
                    if(minuto != 0)
                        minuto = minuto-1;
                    else
                        break;
                }
                String timer =  completaComZero(minuto) + ":" +
                                completaComZero(segundo);
                this.hr.setText(timer);
                this.hr.revalidate();
            }
        } catch (InterruptedException ex) {
           ex.printStackTrace();
        }
        pt.setNome(JOptionPane.showInputDialog("Insira seu nome:"));
        
        jogoController.terminarDia(jt, 0,pt);
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
