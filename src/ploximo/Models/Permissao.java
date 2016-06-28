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
public class Permissao extends Documento {
    private String codPassaporte;
    private String duracao;
    private GregorianCalendar validade;
    private String motivo;
    private String nacionalidade;
    private String altura;
    private String peso;

    public Permissao(String nome,String codPassaporte, String duracao, GregorianCalendar validade, String altura, String peso, String motivo, String nacionalidade) {
        super.setNome(nome);
        this.codPassaporte = codPassaporte;
        this.duracao = duracao;
        this.validade = validade;
        this.altura = altura;
        this.motivo = motivo;
        this.peso = peso;
        this.nacionalidade = nacionalidade;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getCodPassaporte() {
        return codPassaporte;
    }

    public String getDuracao() {
        return duracao;
    }

    public GregorianCalendar getValidade() {
        return validade;
    }

    public void setCodPassaporte(String codPassaporte) {
        this.codPassaporte = codPassaporte;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public void setValidade(GregorianCalendar validade) {
        this.validade = validade;
    }
    
    
}
