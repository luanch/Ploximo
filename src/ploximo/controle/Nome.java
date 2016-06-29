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
public class Nome {
    private static HashMap<Integer,String> nomesMasculinos;
    private static HashMap<Integer,String> nomesFemininos;

    
    
    private static void setNomesMasculinos() throws IOException{
        nomesMasculinos = mapaNomesMasculinos();              
    }
    
    private static void setNomesFemininos() throws IOException{
        nomesFemininos = mapaNomesFemininos();
    }
     
    private static HashMap<Integer,String> mapaNomesFemininos() throws IOException{
        ConversorDeTxtParaMatriz conversor = new ConversorDeTxtParaMatriz();
        HashMap<Integer,String> n = conversor.converter("src/ploximo/basesDeDados/nomesDeMulher.txt");
        return n;     
    }
    
    private static HashMap<Integer,String> mapaNomesMasculinos() throws IOException{
        ConversorDeTxtParaMatriz conversor = new ConversorDeTxtParaMatriz();
        HashMap<Integer,String> n = conversor.converter("src/ploximo/basesDeDados/nomesDeHomem.txt");
        return n;     
    }
    
    public static String gerarHomem() throws IOException{
        setNomesMasculinos();
        Randomizador r = new Randomizador();
        int posicao = r.gerarInt(nomesMasculinos.size());
        String nome = nomesMasculinos.get(posicao);
        return nome;
    }
    
    
    public static String gerarMulher() throws IOException{
        setNomesFemininos();
        Randomizador r = new Randomizador();
        int posicao = r.gerarInt(nomesFemininos.size());
        String nome = nomesFemininos.get(posicao);
        return nome;
    }
}