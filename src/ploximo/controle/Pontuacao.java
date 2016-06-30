/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploximo.controle;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author Fernando
 */
public class Pontuacao implements Serializable{
    
    private String nome;    
    private int pontos;

    public Pontuacao() {

    }

    public Pontuacao(String nome, int pontos) {
        this.nome = nome;
        this.pontos = pontos;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
    
    public void armazenar() throws FileNotFoundException, IOException{
       BufferedWriter p = new BufferedWriter(new FileWriter("score.txt",true));
            String ponts =  getNome() + "," + getPontos() ;
            p.write(ponts);
            p.newLine();
            p.close();
    }
}
