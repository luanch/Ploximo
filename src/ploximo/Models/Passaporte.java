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
public class Passaporte extends Documento {
    private GregorianCalendar dataNascimento;
    private char sexo;
    private String pais;
    private GregorianCalendar validade;
    private String foto;
    private String codigo;

    public Passaporte(String nome,GregorianCalendar dataNascimento, char sexo, String pais, GregorianCalendar validade, String foto, String codigo) {
        super.setNome(nome);
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.pais = pais;
        this.validade = validade;
        this.foto = foto;
        this.codigo = codigo;
    }
    
    public GregorianCalendar getDataNascimento() {
        return dataNascimento;
    }

    public char getSexo() {
        return sexo;
    }

    public String getPais() {
        return pais;
    }

    public GregorianCalendar getValidade() {
        return validade;
    }

    public String getFoto() {
        return foto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setDataNascimento(GregorianCalendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
    public void setValidade(GregorianCalendar validade) {
        this.validade = validade;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }  
}
