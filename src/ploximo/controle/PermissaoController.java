/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploximo.controle;

import java.io.IOException;
import java.util.GregorianCalendar;
import ploximo.Models.Permissao;
import ploximo.Models.Pessoa;

/**
 *
 * @author Cliente
 */
public class PermissaoController {
    
    public Permissao gerar(Pessoa pessoa) throws IOException{
        
    Randomizador rand = new Randomizador();
    
    String nome = pessoa.getNome();
    String cod = Codigo.gerar();
    String duracao = Duracao.gerar();
    String peso = pessoa.getPeso();
    String altura = pessoa.getAltura();
    String motivo = Motivo.gerar();
    String pais = pessoa.getNacionalidade();
            

    GregorianCalendar validade = Data.gerarDataAleatoria(2017, 2020);
   
    Permissao p = new Permissao(nome,cod,duracao,validade, altura, peso, motivo, pais);
    
    return p;    
    }  
}
