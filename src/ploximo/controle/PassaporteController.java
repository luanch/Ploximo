/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploximo.controle;

import java.io.IOException;
import java.util.GregorianCalendar;
import ploximo.Models.Passaporte;
import ploximo.Models.Pessoa;

/**
 *
 * @author Cliente
 */
public class PassaporteController {
    public Passaporte gerar(Pessoa pessoa) throws IOException{
        
    Randomizador r = new Randomizador();
 
    String foto, pais, nome, sNome;
    GregorianCalendar nascimento,validade;
    char sexo;
    foto = pessoa.getFoto();
    pais = pessoa.getNacionalidade();
    nome = pessoa.getNome();
    nascimento = pessoa.getNascimento();
    validade = Data.gerarDataAleatoria(2017, 2020);
    String cod = Codigo.gerar();
    /*
    foto = Foto.gerar();
    pais = Pais.gerar();
        
    nome= Nome.gerar();
    sNome= Sobrenome.gerar();
    nascimento = Data.gerar();
    validade = Data.gerar();
    String cod = Codigo.gera();
    
    */
    boolean sorte;
    sorte = r.gerarBool();
    if(sorte){sexo = 'M';}else{sexo = 'F';}
    
    Passaporte pass = new Passaporte(nome,nascimento,sexo,pais,validade,foto,cod);
    
    return pass;
        
    }
}