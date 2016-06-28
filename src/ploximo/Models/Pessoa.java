/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploximo.Models;

import java.util.GregorianCalendar;
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

    public Pessoa(String nome, GregorianCalendar data, char sexo, 
            String nacionalidade, String peso, String altura, String foto, String motivo, String duracao) {
        this.nome = nome;
        this.nascimento = data;
        this.sexo = sexo;
        this.nacionalidade = nacionalidade;
        this.peso = peso;
        this.altura = altura;
        this.foto = foto;
        this.motivo = motivo;
        this.duracao = duracao;
        ehTerrorista();
        deveEntrar();
       
    }
    
    private void deveEntrar(){
        this.deveEntrar = this.rand.gerarBool();
    }
    
    private void ehTerrorista() { // 12,5% (1/8) de chance de ser terrorista 
        boolean deveSerTerrorista = true;
        for(int i=0; i<3;i++){
            if(this.rand.gerarBool()){
                deveSerTerrorista = false; // Entrei aqui, logo vai dar false
                break; // não preciso de outra iteração
            }
        }
        this.terrorista = deveSerTerrorista;
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
