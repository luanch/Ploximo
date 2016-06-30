/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploximo.Models;

import java.util.GregorianCalendar;
import ploximo.Views.JogoTela;
import ploximo.controle.Randomizador;


/**
 *
 * @author Bianca
 */
public class Pessoa {
    Randomizador rand = new Randomizador();

    private final String nome;
    private final GregorianCalendar nascimento;
    private final char sexo;
    private final String nacionalidade;
    private final String peso;
    private final String altura;
    private final String foto;
    private final String motivo;
    private final String duracao;
    private boolean terrorista;
    private boolean deveEntrar;

    private Identidade id;
    private Passaporte pass;
    private Permissao perm;
    private String codigo;

    public Pessoa(String nome, GregorianCalendar data, char sexo, 
            String nacionalidade, String peso, String altura, String foto,
            String motivo, String duracao, boolean terrorista,
            boolean deveEntrar, String codigo) {
        this.nome = nome;
        this.nascimento = data;
        this.sexo = sexo;
        this.nacionalidade = nacionalidade;
        this.peso = peso;
        this.altura = altura;
        this.foto = foto;
        this.motivo = motivo;
        this.duracao = duracao;
        this.terrorista = terrorista;
        this.deveEntrar = deveEntrar;
        this.codigo = codigo;
       
    }
    
    
    
    public void deletar() throws Throwable{
        this.finalize();
    }

    public boolean getTerrorista() {
        return terrorista;
    }

    public boolean imigranteDeveEntrar() {
        return deveEntrar;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getMotivo() {
        return motivo;
    }
    
    public String getDuracao() {
        return duracao;
    }

    public Identidade getId() {
        return id;
    }

    public Passaporte getPass() {
        return pass;
    }

    public Permissao getPerm() {
        return perm;
    }

    public GregorianCalendar getNascimento() {
        return nascimento;
    }

    public char getSexo() {
        return sexo;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public String getPeso() {
        return peso;
    }

    public String getAltura() {
        return altura;
    }

    public String getFoto() {
        return foto;
    }

    public void setId(Identidade id) {
        this.id = id;
    }

    public void setPass(Passaporte pass) {
        this.pass = pass;
    }

    public void setPerm(Permissao perm) {
        this.perm = perm;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
     
    public String getDataFormatada(GregorianCalendar data){
        StringBuilder dataFormatada = new StringBuilder();
        dataFormatada.append(data.get(GregorianCalendar.DAY_OF_MONTH));
        dataFormatada.append("/");
        dataFormatada.append(data.get(GregorianCalendar.MONTH));
        dataFormatada.append("/");
        dataFormatada.append(data.get(GregorianCalendar.YEAR));
        return dataFormatada.toString();
    } 
}
