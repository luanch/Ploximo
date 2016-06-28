/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploximo.controle;

import java.io.IOException;
import java.util.GregorianCalendar;
import ploximo.Models.Identidade;
import ploximo.Models.Pessoa;

/**
 *
 * @author Cliente
 */
public class IdentidadeController {
    
    public Identidade gerar(Pessoa pessoa) throws IOException{

    Randomizador rand = new Randomizador();
       
    String foto = pessoa.getFoto();
    String nome = pessoa.getNome();

    GregorianCalendar nascimento = pessoa.getNascimento();
    
    String altura = pessoa.getAltura();
   
    String peso = pessoa.getPeso();
   
    Identidade id = new Identidade(nome,nascimento,altura,peso,foto);
    
    return id;
    }
    
}
