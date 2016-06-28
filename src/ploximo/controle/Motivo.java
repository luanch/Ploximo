/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploximo.controle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Bianca
 */
public class Motivo {  
    private static   Map<Integer,String> motivos;
    public static void setMotivos() throws IOException {
       motivos = hashMapMotivos();
    }
    
    private static HashMap hashMapMotivos() throws IOException{
        ConversorDeTxtParaMatriz conversor = new ConversorDeTxtParaMatriz();
        HashMap<Integer, String> c = conversor.converter("src/ploximo/basesDeDados/motivos.txt");
        return c;
        }
    
    public static String gerar() throws IOException{
        setMotivos();
        Randomizador r = new Randomizador();
        int posicao = r.gerarInt(motivos.size());
        return motivos.get(posicao);
   }

}
