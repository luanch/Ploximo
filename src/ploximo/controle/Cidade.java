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
 * @author Fernando
 */
public class Cidade{  
    private static   Map<Integer,String> cidades;
    public static void setCidades() throws IOException {
       cidades = hashMapCidades();
    }
    
    private static HashMap hashMapCidades() throws IOException{
        ConversorDeTxtParaMatriz conversor = new ConversorDeTxtParaMatriz();
        HashMap<Integer, String> c = conversor.converter("src/ploximo/basesDeDados/cidade.txt");
        return c;
        }
    
    public static String gerar() throws IOException{
        setCidades();
        Randomizador r = new Randomizador();
        int posicao = r.gerarInt(cidades.size());
        return cidades.get(posicao);
   }

}
