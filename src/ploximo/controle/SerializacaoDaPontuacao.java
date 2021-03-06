/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploximo.controle;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Fernando
 */
public class SerializacaoDaPontuacao {
    
    private Pontuacao score[];//vetor de pontuação que receberá a decodificação do encoder xml.
    
    public ArrayList<Pontuacao> montarVetor(String path) throws FileNotFoundException, IOException {
    
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = buffRead.readLine();
        ArrayList<Pontuacao>  ps = new ArrayList<Pontuacao>();
        
        
        while (true) {
            if (linha != null) {
               Pontuacao pontua = new Pontuacao();
               String[] line;
               line = linha.split(",");
               System.out.println("Ponto = " + line[0] +" e "+ "Valor=" + line[1]);
               
               pontua.setNome(line[0]);
               pontua.setPontos(Integer.parseInt(line[1]));
               ps.add(pontua);
               
               
            }
            else{
                break;
            }
            linha = buffRead.readLine();
        }

        buffRead.close();
        return ps;        
    }
    
    public void serializar(Pontuacao [] pontos){   
        try{
            XMLEncoder xmlEncoder = null;
            try {
               xmlEncoder = new XMLEncoder(
               new FileOutputStream("pontuação.xml"));
               xmlEncoder.writeObject(pontos); 
            }
            finally{
                if(xmlEncoder != null){
                    xmlEncoder.close();
                }
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public String[][] LerSerialização(){     
       String [][] mscores = null;
        try{
            try(XMLDecoder xmlDecoder = new XMLDecoder(
                new FileInputStream("pontuação.xml"))) {
                
                score = (Pontuacao[]) xmlDecoder.readObject();
                Pontuacao aux; 
                 mscores = new String [score.length][2];
                for(int i=0;i < score.length;i++){
                    for(int j=0;j < score.length;j++){
                        if(score[i].getPontos() > score[j].getPontos()){
                            aux = score[j];
                            score[j] = score[i];
                            score[i] = aux;
                        }            
                    }
                }
                /*Agora que a ordenação dos scores acabou, podemos exibí-los*/

                int i =0;
                for(Pontuacao p:score){

                    mscores[i][0] = p.getNome();
                    mscores[i][1] += p.getPontos();
                    System.out.println();
                    i++;
                }
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        return mscores;
    }
}