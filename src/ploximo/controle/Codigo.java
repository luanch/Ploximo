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
public class Codigo {
    
    private static HashMap<Integer, String> codigos;
      public static String gerar() throws IOException{
     setCodigos();      
    Randomizador r = new Randomizador();
   int posicao = r.gerarInt(codigos.size())+1;
   return codigos.get(posicao);
  }
   
       public static void setCodigos() throws IOException {
       codigos = matrizCodigos();
    }
       
  public static HashMap<Integer,String> matrizCodigos() throws IOException{
    HashMap<Integer,String> mc;
    LeitorDeTxt reader = new LeitorDeTxt();
    ConversorDeTxtParaMatriz conversor = new ConversorDeTxtParaMatriz();
  
    mc = conversor.converter("src/ploximo/basesDeDados/codigo.txt");
  
  return mc;
  }     
       
}
