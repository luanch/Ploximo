/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploximo.controle;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 *
 * @author Fernando
 */
public class Randomizador { 
    Random gerador = new Random();
    public int gerarInt(int limite){
        int resultado = gerador.nextInt(limite); //de 0 até o limite inclusive
        return resultado;
    }
    public boolean gerarBool(){
        boolean resultado = gerador.nextBoolean();//0 ou 1
        return resultado;
    }
    public double gerarDouble(){
        double resultado = gerador.nextDouble();//entre 0 e 1
        BigDecimal bd = new BigDecimal(resultado).setScale(2, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }
}
