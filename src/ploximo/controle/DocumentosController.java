/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploximo.controle;

import java.io.IOException;
import ploximo.Models.Identidade;
import ploximo.Models.Passaporte;
import ploximo.Models.Permissao;
import ploximo.Models.Pessoa;
import ploximo.Views.JogoTela;

/**
 *
 * @author Luan
 */
public class DocumentosController {
    Randomizador rand = new Randomizador();
    
    public Pessoa determinarDocumentoErrado(JogoTela jogo, Pessoa imigrante) throws IOException {
        int qualDocumento = rand.gerarInt(2); // 1 é identidade, 2 é passaporte, 3 permissão
        switch (qualDocumento) {
            case 0:
                imigrante.setPerm(determinarErroEmPermissao(imigrante));
                break; 
            case 1:
                imigrante.setId(determinarErroEmIdentidade(imigrante));
                break;
            case 2:   
                imigrante.setPass(determinarErroEmPassaporte(imigrante));
                break;
        }
        return imigrante;
    }
    
    private Identidade determinarErroEmIdentidade(Pessoa imigrante) throws IOException {
        Identidade id = imigrante.getId();
        int qualAtributo = rand.gerarInt(3);
        switch (qualAtributo) {
            case 0: //erro é no peso
                id.setPeso(alterarPeso(id.getPeso()));
                break;
            case 1: //erro é no nome
                id.setNome(alterarNome(id.getNome()));
                break;
            case 2: //erro é no nascimento
                id.setDataNascimento(Data.gerarDataAleatoria(1930,2000));
                break;
            case 3: //erro é na altura
                id.setAltura(alterarAltura(id.getAltura()));
                break;
        }   
        return id;
    }

    private Passaporte determinarErroEmPassaporte(Pessoa imigrante) throws IOException {
        Passaporte pass = imigrante.getPass();
        int qualAtributo = rand.gerarInt(3);
        switch (qualAtributo) {
            case 0: //pais
                pass.setPais(alterarPais(pass.getPais()));
                break;
            case 1: //erro é no nome
                pass.setNome(alterarNome(pass.getNome()));
                break;
            case 2: //validade
                pass.setValidade(Data.gerarDataAleatoria(2013,2015));
                break; 
            case 3: //erro é no nascimento
                pass.setDataNascimento(Data.gerarDataAleatoria(1930,2000));
                break;
        }
        return pass;
    }
    
    private Permissao determinarErroEmPermissao(Pessoa imigrante) throws IOException {
        Permissao perm = imigrante.getPerm();
        int qualAtributo = rand.gerarInt(4);
        switch (qualAtributo) {
            case 0: //peso
                perm.setPeso(alterarPeso(perm.getPeso()));
                break;
            case 1: //erro é no nome
                perm.setNome(alterarNome(perm.getNome()));
                break;
            case 2: //pais
                perm.setNacionalidade(alterarPais(perm.getNacionalidade()));
                break;
            case 3: //altura
                perm.setAltura(alterarAltura(perm.getAltura()));
                break;
            case 4: //validade
                perm.setValidade(Data.gerarDataAleatoria(2013,2015));
                break;
        }
        return perm;
    }

    private String alterarAltura(String stringAltura) {
        String[] arrayAltura = stringAltura.split(" ");
        double altura = Double.valueOf(arrayAltura[0].replace(',', '.'));
        altura -= (altura*0.1);
        String stringAlturaAlterada = String.valueOf(altura).replace('.', ',') + arrayAltura[1];
        return stringAlturaAlterada;
    }

    private String alterarPeso(String stringPeso) {
        String[] arrayPeso = stringPeso.split(" ");
        double peso = Double.valueOf(arrayPeso[0].replace(',', '.'));
        peso -= (peso*0.1);
        String stringPesoAlterado = String.valueOf(peso).replace('.', ',') + arrayPeso[1];
        return stringPesoAlterado;
    }

    private String alterarNome(String nomeAntigo) throws IOException {
        String nomeNovo;
        while(true){
            nomeNovo = Nome.gerar();
            if(!nomeAntigo.equals(nomeNovo)){
                break;
            }
        }
        return nomeNovo;
    }

    private String alterarPais(String paisAntigo) throws IOException {
        String paisNovo;
        while(true){
            paisNovo = Nome.gerar();
            if(!paisAntigo.equals(paisNovo)){
                break;
            }
        }
        return paisNovo;
    }

    private char alterarSexo(char sexo) {
        if(sexo == 'M')
            return 'F';
        return 'M';
    }
}