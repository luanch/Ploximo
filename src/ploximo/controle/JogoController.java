/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploximo.controle;

import java.awt.Cursor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import ploximo.Models.Identidade;
import ploximo.Models.Passaporte;
import ploximo.Models.Permissao;
import ploximo.Models.Pessoa;
import ploximo.Views.FimDoDia;
import ploximo.Views.JogoTela;
import ploximo.controle.Pontuacao;

/**
 *
 * @author Luan
 */
public class JogoController {
    private static final int TAMANHO_BARRA_SUPERIOR = 30;
    Pessoa imigrante;
    DocumentosController dc = new DocumentosController();
    JButton botao1;
    JButton botao2;
    String tipo1;
    String tipo2;
    
    /**
     * Inicia as telas com tamanho do fundo. O fundo foi passado por 
     * parametro porque não conseguimos acessá-lo daqui
     * @param tela
     * @param fundo
     */
    
    
    public void iniciarMeusComponentes(JFrame tela, JLabel fundo){
        tela.setLocation(0, 0);
        tela.setSize(fundo.getWidth()+10, fundo.getHeight()+TAMANHO_BARRA_SUPERIOR);
    }
    
    /*
    * função chamada quando o usuário clica em negar no jogotela. retorna true
    * quando usuario acertou o erro e false quando errou tudo
    */
    
     public String insertNome(){
        String nome = JOptionPane.showInputDialog("INSIRA SEU NOME: ");
        return nome;   
    }
    
    public void nomearScore(Pontuacao pontuacao, String nome){
      pontuacao.setNome(nome);
      System.out.println("Seu nome, " + nome + ", foi inserido nos recordes com sucesso.");
    }
    
    public void calcularPontuação(JogoTela jogo, Pontuacao pontuacao){
    
        int pontuação = 0;
        
        if(verificarCorretude(jogo)){
          pontuação ++;
          pontuacao.setPontos(pontuacao.getPontos()+pontuação);
        }
        else{
          pontuação --;
          pontuacao.setPontos(pontuacao.getPontos()+pontuação);
        }
        System.out.println("Pontos adicionados");
    }
    
    public boolean verificarCorretude (JogoTela jogo) {
    if( (botao1.getText() != null) && (botao2.getText() != null) ){   
        if (!tipo1.equals("validade") || !tipo2.equals("validade")) {
            if (tipo1.equals(tipo2)) {
                if (!botao1.getText().equals(botao2.getText())) {
                    return true;
                }
            }
        }
        else {
            if (tipo2.equals("validade") && tipo1.equals("data atual")) {
                if (tipo2.contains("2013") || tipo2.contains("2014") 
                            || tipo2.contains("2015")) {
                    return true;
                }
            }
            else 
                if (tipo1.equals("validade") && tipo2.equals("data atual")) {
                    if (tipo1.contains("2013") || tipo1.contains("2014") 
                            || tipo1.contains("2015")) {
                            return true;
                    }
                }
        }
    }
    else 
        if( ((botao1.getText() != null) && (botao2.getText() == null)) || ((botao1.getText() == null) && (botao2.getText() != null)) ){
          return true;    
        }
            return false;
    }
    
    

    public void chamarImigrante(JogoTela jogo) {
        if (imigrante == null) {
             try {
                imigrante = jogo.getPessoaController().gerar(); 

                if (!imigrante.imigranteDeveEntrar()) {
                    
                    imigrante = dc.determinarDocumentoErrado(jogo, imigrante);
                }
                adicionarInformacoesId(jogo);
                adicionarInformacoesPerm(jogo);
                adicionarInformacoesPass(jogo);
               
                jogo.getPesoBotao().setText(imigrante.getPeso());

                Foto foto = new Foto();
                foto.setarImagemNoBotao(jogo.getPessoaBotao(), imigrante.getFoto()); 
            } catch (IOException ex) {
                Logger.getLogger(JogoTela.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }
    
    private void adicionarInformacoesId(JogoTela jogo) {
        Identidade id = imigrante.getId();
        jogo.getIdAlt().setText(id.getAltura());
        jogo.getIdNome().setText(id.getNome());
        jogo.getIdNasc().setText(imigrante.getDataFormatada(id.getDataNascimento()));
        jogo.getIdPeso().setText(id.getPeso());
        Foto foto = new Foto();
        foto.setarImagemNoBotao(jogo.getIdFoto(), id.getFoto()); 
    }
    
    private void adicionarInformacoesPerm(JogoTela jogo) {
        Permissao perm = imigrante.getPerm();
        jogo.getPermAlt().setText(perm.getAltura());
        jogo.getPermDuracao().setText(perm.getDuracao());
        jogo.getPermMotivo().setText(perm.getMotivo());
        jogo.getPermNacio().setText(perm.getNacionalidade());
        jogo.getPermNome().setText(perm.getNome());
        jogo.getPermPassCod().setText(perm.getCodPassaporte());
        jogo.getPermPeso().setText(perm.getPeso());
        jogo.getPermVal().setText(imigrante.getDataFormatada(perm.getValidade()));              
    }
    
    private void adicionarInformacoesPass(JogoTela jogo){
        Passaporte passaporte = imigrante.getPass();
        jogo.getPassCid().setText(passaporte.getPais());
        jogo.getPassCod().setText(passaporte.getCodigo());
        jogo.getPassNasc().setText(imigrante.getDataFormatada(imigrante.getNascimento()));
        jogo.getPassNome().setText(passaporte.getNome());
        jogo.getPassSexo().setText(Character.toString(passaporte.getSexo()));
        jogo.getPassVal().setText(imigrante.getDataFormatada(passaporte.getValidade()));
        Foto foto = new Foto();
        foto.setarImagemNoBotao(jogo.getPassFoto(), passaporte.getFoto()); 
    }

    /**
     * Método para configurar os botões das telas como invisíveis
     * e colocando hand_cursor quando mouse passar sobre
     * @param botoes
     */
    public void configurarBotoes(ArrayList<JButton> botoes){
       for(JButton botao: botoes){
           botao.setOpaque(false);
           botao.setContentAreaFilled(false);
           botao.setBorderPainted(false);
           botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
       }
    }
    
    /**
     * Troca de tela, escondendo a tela em que se estava e mostrando a
     * tela nova
     * @param telaAntiga
     * @param telaNova
     */
    public void trocarDeTela(JFrame telaAntiga, JFrame telaNova){
        toggleTela(telaAntiga);
        toggleTela(telaNova);
     }
    
    /**
     *  Fecha a tela 
     * @param tela
     */
    public void fecharTela(JFrame tela){
        tela.dispose();
    } 
    
    /**
     * Mostra/esconde tela
     * @param tela
     */
    public void toggleTela(JFrame tela){
        tela.setVisible(!tela.isVisible());
    }
    
    /**
     * Mostra/esconde painel
     * @param painel
     */
    public void togglePainel(JLayeredPane painel){
        painel.setVisible(!painel.isVisible());
    }
    
    public void limitarCliques(JButton botao, String tipo){
        
        if(!botao.isContentAreaFilled()){
            if((botao1 == null)){
                botao.setContentAreaFilled(true);
                botao1 = botao;
                tipo1 = tipo;
                
            }
            else if(botao2 == null){
                botao.setContentAreaFilled(true);
                botao2 = botao;
                tipo2 = tipo;
            }
        }
        else {
            botao.setContentAreaFilled(false);
            if((botao2 == botao)){
                botao2 = null;
                tipo2 = null;
            }     
            else 
                botao1 = null;
                tipo1 = null;
                
        }
            
        
    }

    public void aprovarImigrante(JogoTela jogo) throws Throwable {
        imigrante = jogo.getImigrante();
        if (imigrante != null) {
            imigrante.deletar();
            jogo.getPesoBotao().setText("");     
            //Foto foto = new Foto();
            //foto.retirarImagemNoBotao(jogo.getPessoaBotao());     
        } 
    }

    public void negarImigrante(JogoTela jogo) throws Throwable {
        imigrante = jogo.getImigrante();
        if (imigrante != null) {
            imigrante.deletar();
            jogo.getPesoBotao().setText("");     
            //Foto foto = new Foto();
            //foto.retirarImagemNoBotao(jogo.getPessoaBotao());     
        }   
    }

    public JButton getBotao1() {
        return botao1;
    }

    public JButton getBotao2() {
        return botao2;
    }

    public void ataqueTerrista(JogoTela jogo,Pontuacao ponto) {
        JLabel ataqueLabel = jogo.getAtaqueLabel();
        ataqueLabel.setVisible(true);
        ataqueLabel.setIcon(new ImageIcon("src/ploximo/Imagens/ataque.gif"));
        JButton ploximo = jogo.getPloximoBotao();
        ploximo.setEnabled(false);
        terminarDia(jogo, 5000, ponto);
    } 
    
    public void terminarDia(JogoTela jogo, int tempoAntesDeTerminar, Pontuacao pontos){
            new java.util.Timer().schedule( 
            new java.util.TimerTask() {
                @Override
                public void run() {
                    jogo.getAtaqueLabel().setVisible(false);
                    trocarDeTela(jogo, new FimDoDia(pontos));
                    jogo.getPloximoBotao().setEnabled(true);
                }
            }, 
            tempoAntesDeTerminar
        );    
    }
}