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
    
    public void LerSerialização(){        
        try{
            try(XMLDecoder xmlDecoder = new XMLDecoder(
                new FileInputStream("pontuação.xml"))) {
                
                score = (Pontuacao[]) xmlDecoder.readObject();
                Pontuacao aux; 
                for(int i=0;i<score.length-1;i++){
                    for(int j=1;j<score.length;j++){
                        if(score[i].getPontos() < score[j].getPontos()){
                            aux = score[i];
                            score[i] = score[j];
                            score[j] = aux;
                        }            
                    }
                }
                /*Agora que a ordenação dos scores acabou, podemos exibí-los*/
                System.out.println("High Score:");
                for(Pontuacao p:score){
                    System.out.print("Nome:" + p.getNome());
                    System.out.print(" Pontos:" + p.getPontos());
                    System.out.println();
                }
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}