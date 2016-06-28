/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploximo.Models;

import java.util.GregorianCalendar;

/**
 *
 * @author Bianca
 */
public class Identidade extends Documento {
    private GregorianCalendar dataNascimento;
    private String altura;
    private String peso;
    private String foto;

    public Identidade(String nome,GregorianCalendar dataNascimento, String altura, String peso, String foto) {
        super.setNome(nome);
        this.dataNascimento = dataNascimento;
        this.altura = altura;
        this.peso = peso;
        this.foto = foto;
    }

    public GregorianCalendar getDataNascimento() {
        return dataNascimento;
    }

    public String getAltura() {
        return altura;
    }

    public String getPeso() {
        return peso;
    }

    public String getFoto() {
        return foto;
    }

    public void setDataNascimento(GregorianCalendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    
    
}
