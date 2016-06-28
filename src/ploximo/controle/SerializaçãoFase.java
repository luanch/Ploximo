/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploximo.controle;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Cliente
 */
public class SerializaçãoFase {
    private JogoSalvo fases[];//vetor de pontuação que receberá a decodificação do encoder xml.
    
    public void serializar(JogoSalvo fases[]){   
        try{
            XMLEncoder xmlEncoder= null;
            try {
               xmlEncoder = new XMLEncoder(
               new FileOutputStream("savefile.xml"));
               xmlEncoder.writeObject(fases); 
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
    
    public JogoSalvo[] LerSerialização(){        
        try{
            try(XMLDecoder xmlDecoder = new XMLDecoder(
                new FileInputStream("savefile.xml"))) {
                
                fases = (JogoSalvo[]) xmlDecoder.readObject();
               return fases;
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}