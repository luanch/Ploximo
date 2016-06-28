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
public class Duracao {  
    private static   Map<Integer,String> duracoes;
    public static void setDuracoes() throws IOException {
       duracoes = hashMapDuracoes();
    }
    
    private static HashMap hashMapDuracoes() throws IOException{
        ConversorDeTxtParaMatriz conversor = new ConversorDeTxtParaMatriz();
        HashMap<Integer, String> c = conversor.converter("src/ploximo/basesDeDados/duracoes.txt");
        return c;
        }
    
    public static String gerar() throws IOException{
        setDuracoes();
        Randomizador r = new Randomizador();
        int posicao = r.gerarInt(duracoes.size());
        return duracoes.get(posicao);
   }

}
