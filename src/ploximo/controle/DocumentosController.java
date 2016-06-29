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
                System.out.println("perm");
                imigrante.setPerm(determinarErroEmPermissao(imigrante));
                break; 
            case 1:
                System.out.println("id");
                imigrante.setId(determinarErroEmIdentidade(imigrante));
                break;
            case 2:   
                System.out.println("pass");
                imigrante.setPass(determinarErroEmPassaporte(imigrante));
                break;
        }
        return imigrante;
    }
    
    private Identidade determinarErroEmIdentidade(Pessoa imigrante) throws IOException {
        System.out.println("Dentro de id "+ imigrante);
        Identidade id = imigrante.getId();
        int qualAtributo = rand.gerarInt(3);
        switch (qualAtributo) {
            case 0: //erro é no peso
                System.out.println("ALOO peso");
                id.setPeso(alterarPeso(id.getPeso()));
                System.out.println("ALOO peso passei");
                break;
            case 1: //erro é no nome
                System.out.println("ALOO nome");
                id.setNome(alterarNome(id.getNome()));
                System.out.println("ALOO nome passei");
                break;
            case 2: //erro é no nascimento
                System.out.println("ALOO nasc");
                id.setDataNascimento(Data.gerarDataAleatoria(1930,2000));
                System.out.println("ALOO nasc passei");
                break;
            case 3: //erro é na altura
                
                System.out.println("ALOO altura");

                id.setAltura(alterarAltura(id.getAltura()));
                System.out.println("ALOO altura passei");

                break;
        }   
        return id;
    }

    private Passaporte determinarErroEmPassaporte(Pessoa imigrante) throws IOException {
        System.out.println("Dentro de pass "+ imigrante);
        Passaporte pass = imigrante.getPass();
        int qualAtributo = rand.gerarInt(4);
        switch (qualAtributo) {
            case 0: //pais
                System.out.println("ALOO pais");
                pass.setPais(alterarPais(pass.getPais()));
                System.out.println("ALOO pais passei");

                break;
            case 1: //erro é no nome
                System.out.println("ALOO nome");
                pass.setNome(alterarNome(pass.getNome()));
                System.out.println("ALOO nome passei");
                break;
            case 2: //validade
                System.out.println("ALOO valid");

                pass.setValidade(Data.gerarDataAleatoria(2013,2015));
                System.out.println("ALOO valid passei");

                break; 
            case 3: //erro é no nascimento
                System.out.println("ALOO nasc");

                pass.setDataNascimento(Data.gerarDataAleatoria(1930,2000));
                System.out.println("ALOO nasc passei");

                break;
            case 4: //sexo
                System.out.println("ALOO sexo");
                pass.setSexo(alterarSexo(pass.getSexo()));
                System.out.println("ALOO sexo passei");

                break;
        }
        return pass;
    }
    
    private Permissao determinarErroEmPermissao(Pessoa imigrante) throws IOException {
        System.out.println("Dentro de permissao "+ imigrante);
        Permissao perm = imigrante.getPerm();
        int qualAtributo = rand.gerarInt(4);
        switch (qualAtributo) {
            case 0: //peso
                System.out.println("ALOO peso");

                perm.setPeso(alterarPeso(perm.getPeso()));
                System.out.println("ALOO peso passei");
                
                break;
            case 1: //erro é no nome
                System.out.println("ALOO nome");

                perm.setNome(alterarNome(perm.getNome()));
                System.out.println("ALOO nome passei");
                
                break;
            case 2: //pais
                
                System.out.println("ALOO nacio");

                perm.setNacionalidade(alterarPais(perm.getNacionalidade()));
                System.out.println("ALOO nacio passei");
                break;
            case 3: //altura
                System.out.println("ALOO altura");

                perm.setAltura(alterarAltura(perm.getAltura()));
                System.out.println("ALOO altura passei");
                
                break;
            case 4: //validade
                System.out.println("ALOO valid");

                perm.setValidade(Data.gerarDataAleatoria(2013,2015));
                System.out.println("ALOO valid passei");
 
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