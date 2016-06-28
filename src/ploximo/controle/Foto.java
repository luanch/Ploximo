/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploximo.controle;

import java.io.IOException;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Fernando
 */
public class Foto {
    
    private static HashMap<Integer,String> fotos;
  
    
    private static void setFotos() throws IOException{
        fotos = mapaFotos();
    }
    
    private static HashMap<Integer,String> mapaFotos() throws IOException{
        ConversorDeTxtParaMatriz conversor = new ConversorDeTxtParaMatriz();
        HashMap<Integer,String> p = conversor.converter("src/ploximo/basesDeDados/foto.txt");
        return p;
    }
    
    public static String gerar() throws IOException{  
        setFotos();  
        Randomizador r = new Randomizador();
        int posicao = r.gerarInt(fotos.size());
        return fotos.get(posicao);
    }
    
    public void setarImagemNoBotao(JButton botao, String nomeImagem) {
        botao.setIcon(new ImageIcon("src/ploximo/Imagens/caras/" + nomeImagem + ".png"));
        botao.setDisabledIcon(new ImageIcon("src/ploximo/Imagens/caras/" + nomeImagem + ".png"));
    }
    
   
    
    public void retirarImagemNoBotao(JButton botao){
        botao.setIcon(null);
    }
}