/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploximo.controle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 *
 * @author Fernando
 */
public class LeitorDeTxt { //captura o txt pré configurado
    
    
     public void leitor(String path) throws IOException {
         try (BufferedReader buffRead = new BufferedReader(new FileReader(path))) {
             String linha = "";
             while (true) {
                 if (linha != null) {
                 } else {
                     break;
                 }
                 linha = buffRead.readLine();
             }
         }
    }
    
     
     public int contalinhas(String path) throws IOException{ //conta as linhas do txt para construir a matriz
     
        File arquivoLeitura = new File(path);
        LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(arquivoLeitura));
        linhaLeitura.skip(arquivoLeitura.length());
        int qtdLinha = linhaLeitura.getLineNumber()+1;
        return qtdLinha; // por alguma razão mistica ele esquece de contar a ultima linha do txt
     }
     
     
      public int variaveis(String path) throws FileNotFoundException, IOException{//conta qtas colunas a matriz teria
    BufferedReader buffRead = new BufferedReader(new FileReader(path));
    int var ;
    String linha = buffRead.readLine();
    String [] line ;
    line = linha.split(";");
    var = line.length;
    return var;    
    }
}
