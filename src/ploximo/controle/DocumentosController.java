/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploximo.controle;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    
    
    Pessoa determinarPesoErrado(JogoTela jogo, Pessoa imigrante) {
        if(rand.gerarBool())
            imigrante.getId().setPeso(alterarPeso(imigrante.getPeso()));
        else
            imigrante.getPerm().setPeso(alterarPeso(imigrante.getPeso()));
        return imigrante;
    }
    
    
    private Identidade determinarErroEmIdentidade(Pessoa imigrante) throws IOException {
        Identidade id = imigrante.getId();
        char sexo = imigrante.getSexo();
        int qualAtributo = rand.gerarInt(3);
        switch (qualAtributo) {
            case 0: //erro é no peso
                id.setPeso(alterarPeso(id.getPeso()));
                System.out.println("erro id peso");
                break;
            case 1: //erro é no nome
                id.setNome(alterarNome(id.getNome(), sexo));
                System.out.println("erro id nome");
                break;
            case 2: //erro é no nascimento
                id.setDataNascimento(Data.gerarDataAleatoria(1930,2000));
                System.out.println("erro id dt nasc");
                break;
            case 3: //erro é na altura
                id.setAltura(alterarAltura(id.getAltura()));
                System.out.println("erro id altura");
                break;
        }   
        return id;
    }

    private Passaporte determinarErroEmPassaporte(Pessoa imigrante) throws IOException {
        Passaporte pass = imigrante.getPass();
        char sexo = imigrante.getSexo();
        int qualAtributo = rand.gerarInt(4);
        switch (qualAtributo) {
            case 0: //pais
                pass.setPais(alterarPais(pass.getPais()));
                System.out.println("Erro pass pais");
                break;
            case 1: //erro é no nome
                pass.setNome(alterarNome(pass.getNome(), sexo));
                System.out.println("erro pass nome");
                break;
            case 2: //validade
                pass.setValidade(Data.gerarDataAleatoria(2013,2015));
                System.out.println("erro pass validade");
                break; 
            case 3: //erro é no nascimento
                pass.setDataNascimento(Data.gerarDataAleatoria(1930,2000));
                System.out.println("erro pass dt nasc");
                break;
            case 4: //erro de codigo
                pass.setCodigo(alterarCodigo(pass.getCodigo()));
                System.out.println("erro pass cod");
                break;
            case 5:
                pass.setSexo(alterarSexo(sexo));
                break;
        }
        return pass;
    }
    
    private Permissao determinarErroEmPermissao(Pessoa imigrante) throws IOException {
        Permissao perm = imigrante.getPerm();
        char sexo = imigrante.getSexo();
        int qualAtributo = rand.gerarInt(5);
        switch (qualAtributo) {
            case 0: //peso
                perm.setPeso(alterarPeso(perm.getPeso()));
                System.out.println("erro perm peso");
                break;
            case 1: //erro é no nome
                perm.setNome(alterarNome(perm.getNome(), sexo));
                System.out.println("erro perm nome");
                break;
            case 2: //pais
                perm.setNacionalidade(alterarPais(perm.getNacionalidade()));
                System.out.println("erro perm nacio");
                break;
            case 3: //altura
                perm.setAltura(alterarAltura(perm.getAltura()));
                System.out.println("erro perm altura");
                break;
            case 4: //validade
                perm.setValidade(Data.gerarDataAleatoria(2013,2015));
                System.out.println("erro perm validade");
                break;
            case 5: // codigo
                perm.setCodPassaporte(alterarCodigo(perm.getCodPassaporte()));
                System.out.println("erro perm codigo pass");
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
        BigDecimal bd = new BigDecimal(peso).setScale(2, RoundingMode.HALF_EVEN);
        peso = bd.doubleValue();
        String stringPesoAlterado = String.valueOf(peso).replace('.', ',')
                                                        +" "+ arrayPeso[1];
        return stringPesoAlterado;
    }

    private String alterarNome(String nomeAntigo, char sexo) throws IOException {
        String nomeNovo;
        while(true){
            if(sexo == 'H')
                nomeNovo = Nome.gerarHomem();
            else
                nomeNovo = Nome.gerarMulher();
            if(!nomeAntigo.equals(nomeNovo)){
                break;
            }
        }
        return nomeNovo;
    }

    private String alterarPais(String paisAntigo) throws IOException {
        String paisNovo;
        while(true){
            paisNovo = Pais.gerar();
            if(!paisAntigo.equals(paisNovo)){
                break;
            }
        }
        return paisNovo;
    }

    private char alterarSexo(char sexo) {
        if(sexo == 'H')
            return 'M';
        return 'H';
    }

    private String alterarCodigo(String codigoAntigo) throws IOException {
        String codigoNovo;
        while(true){
            codigoNovo = Codigo.gerar();
            if(!codigoAntigo.equals(codigoNovo)){
                break;
            }
        }
        return codigoNovo;
    }    
}