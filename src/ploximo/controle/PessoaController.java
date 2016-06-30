/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploximo.controle;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.GregorianCalendar;
import ploximo.Models.Identidade;
import ploximo.Models.Passaporte;
import ploximo.Models.Permissao;
import ploximo.Models.Pessoa;

/**
 *
 * @author Fernando
 */
public class PessoaController {
    Randomizador r = new Randomizador();
    boolean terrorista;
    boolean deveEntrar;
    String codigo;
    
    public Pessoa gerar() throws IOException{
        String foto = Foto.gerar();
        String nacionalidade = Nacionalidade.gerar();
        GregorianCalendar nascimento = Data.gerarDataAleatoria(1940, 2000);
        
        String peso = gerarPesoFormatado();
        String altura = gerarAlturaFormatada(peso);
        char sexo = gerarSexo(r, foto);
        String motivo = Motivo.gerar();
        String duracao = Duracao.gerar();
        terrorista = calcularTerrorista(r);
        deveEntrar = calculaDeveEntrar(r);
        codigo = Codigo.gerar();
        String nome;
        if(sexo == 'M'){
            nome = Nome.gerarHomem();
        }
        else
            nome = Nome.gerarMulher();

        Pessoa pessoa = new Pessoa(nome,nascimento, sexo, nacionalidade,
                                    peso, altura, foto, motivo, duracao, terrorista, deveEntrar, codigo);
        PassaporteController pC = new PassaporteController();
        Passaporte pass = pC.gerar(pessoa);
        pessoa.setPass(pass);
        IdentidadeController iC = new IdentidadeController();
        Identidade id = iC.gerar(pessoa);
        pessoa.setId(id);
        PermissaoController permC = new PermissaoController();
        Permissao perm = permC.gerar(pessoa);
        pessoa.setPerm(perm);

        return pessoa;
    }

    public boolean isTerrorista() {
        return terrorista;
    }

    public boolean deveEntrar() {
        return deveEntrar;
    }
    
    private boolean calculaDeveEntrar(Randomizador r){
        return r.gerarBool();
    }
    
    private boolean calcularTerrorista(Randomizador r) { // 12,5% (1/8) de chance de ser terrorista 
        boolean deveSerTerrorista = true;
        for(int i=0; i<3;i++){
            if(r.gerarBool()){
                deveSerTerrorista = false; // Entrei aqui, logo vai dar false
                break; // não preciso de outra iteração
            }
        }
        return deveSerTerrorista;
    }

    private char gerarSexo(Randomizador rand, String foto) {
        if(foto.contains("H")){
            return 'H';
        }
        return 'M';
    }

    private String gerarPesoFormatado() {
        return Double.toString(r.gerarInt(50)+ 60 + r.gerarDouble()).replace('.', ',').concat(" kg");
    } 

    private String gerarAlturaFormatada(String stringPeso) {
        String[] arrayPeso = stringPeso.split(" ");
        double peso = Double.valueOf(arrayPeso[0].replace(',', '.'));
        double porcentPeso = ((peso-10)%100)/100;
        return Double.toString(formatarDouble(1 + ((double)r.gerarInt(20))/100+porcentPeso)).replace('.', ',').concat(" m");
    }

    private double formatarDouble(double d) {
        BigDecimal bd = new BigDecimal(d).setScale(2, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }
}