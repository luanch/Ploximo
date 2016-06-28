/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploximo.controle;

import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Fernando
 */
public class Pais {
    private static HashMap<Integer,String> países;
    
    public static void setPaises() throws IOException{
        países = mapaPaíses();
    }
    public static HashMap<Integer,String> mapaPaíses() throws IOException{
        LeitorDeTxt reader = new LeitorDeTxt();
        ConversorDeTxtParaMatriz conversor = new ConversorDeTxtParaMatriz();    
        HashMap<Integer,String> p = conversor.converter("src/ploximo/basesDeDados/pais.txt");
        return p;  
    }
    
    public static String gerar() throws IOException{
        setPaises();
        Randomizador r = new Randomizador();
        int posicao = r.gerarInt(países.size());
        String pais = países.get(posicao);
        return pais;
    }
}
