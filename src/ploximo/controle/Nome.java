/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploximo.controle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Fernando
 */
public class Nome {
    private static HashMap<Integer,String> nomes;
    
    private static void setNomes() throws IOException{
        nomes = mapaNomes();              
    }
    
    private static HashMap<Integer,String> mapaNomes() throws IOException{
        ConversorDeTxtParaMatriz conversor = new ConversorDeTxtParaMatriz();
        HashMap<Integer,String> n = conversor.converter("src/ploximo/basesDeDados/nome.txt");
        return n;     
    }
    
    public static String gerar() throws IOException{
        setNomes();
        Randomizador r = new Randomizador();
        int posicao = r.gerarInt(nomes.size());
        String nome = nomes.get(posicao);
        return nome;
    }
}
