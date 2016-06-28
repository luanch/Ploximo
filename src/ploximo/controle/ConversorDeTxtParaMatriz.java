/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploximo.controle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Fernando
 */
public class ConversorDeTxtParaMatriz { //converte o texto em matriz
    
     public HashMap<Integer,String> converter(String path) throws IOException {
         HashMap<Integer,String> mapa;
         try (BufferedReader buffRead = new BufferedReader(new FileReader(path))) {
            mapa = new HashMap<>();
            int i = 0;
            String proximaLinha = buffRead.readLine();
            while (true) {
               if (proximaLinha != null) {
                      mapa.put(i, proximaLinha);
                      i++;
               } else {
                   break;
               }
               proximaLinha = buffRead.readLine();
            }
         }
        return mapa;
    }
     
}
