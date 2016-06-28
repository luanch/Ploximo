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
public class Nacionalidade {
  
    private static HashMap<Integer,String> nacionalidades;
    
    private static void setNacionalidades() throws IOException{
        nacionalidades = mapaNacionalidades();
    }
    
    private static HashMap<Integer,String> mapaNacionalidades() throws IOException{
        ConversorDeTxtParaMatriz conversor = new ConversorDeTxtParaMatriz();
        
        HashMap<Integer,String> n = conversor.converter("src/ploximo/basesDeDados/nacionalidade.txt");
        return n;
    }
            
    public static String gerar() throws IOException{
        setNacionalidades();
        Randomizador r = new Randomizador();
        int posicao = r.gerarInt(nacionalidades.size());
        String nacionalidade = nacionalidades.get(posicao);

        return nacionalidade;
    }
}
