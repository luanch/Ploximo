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

/**
 *
 * @author Luan
 */
public class JogoController {
    private static final int TAMANHO_BARRA_SUPERIOR = 30;
    Pessoa imigrante;
    Randomizador r = new Randomizador();
    DocumentosController dc = new DocumentosController();
    JButton botao1;
    JButton botao2;
    String tipo1;
    String tipo2;
    ArrayList<JButton> botoesClicaveis = new ArrayList<>();
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
    

   
     public String insertNome(){
        String nome = JOptionPane.showInputDialog("INSIRA SEU NOME: ");
        return nome;   
    }
    
    public void nomearScore(Pontuacao pontuacao, String nome){
      pontuacao.setNome(nome);
    }
    
    public void calcularPontuacao(JogoTela jogo, Pontuacao pontuacao){
        JLabel pontuacaoLabel = jogo.getPontuacaoLabel();
        
        String[] pontuacaoArray = pontuacaoLabel.getText().split(" ");
        if(jogo.clicouEmAprovar()){
            if(imigrante.deveEntrar()){
                pontuacao.setPontos(pontuacao.getPontos()+10);
                pontuacaoLabel.setText(pontuacaoArray[0]+ " "+ pontuacao.getPontos());
            }
            else{
                if(imigrante.isTerrorista()){
                    pontuacao.setPontos(pontuacao.getPontos()-100);
                    pontuacaoLabel.setText(pontuacaoArray[0]+ " "+ pontuacao.getPontos());
                }
                else{
                    pontuacao.setPontos(pontuacao.getPontos()-10);
                    pontuacaoLabel.setText(pontuacaoArray[0]+ " "+ pontuacao.getPontos());
                }
            }
        }
        else{
            if(verificarCorretude(jogo)){
                pontuacao.setPontos(pontuacao.getPontos()+10);
                pontuacaoLabel.setText(pontuacaoArray[0]+ " "+ pontuacao.getPontos());
            }
            else{
                if(imigrante.isTerrorista()){
                    pontuacao.setPontos(pontuacao.getPontos()-100);
                    pontuacaoLabel.setText(pontuacaoArray[0]+ " "+ pontuacao.getPontos());
                }
                else{
                    pontuacao.setPontos(pontuacao.getPontos()-10);
                    pontuacaoLabel.setText(pontuacaoArray[0]+ " "+ pontuacao.getPontos());
                }
            }
        }
    }
    
    public boolean verificarCorretude (JogoTela jogo) {
        if (tipo1 == null) {
            System.out.println("Imigrante deve Entrar?"+ imigrante.deveEntrar());
            return !imigrante.deveEntrar();
        }  
        if( (botao1 != null) && (botao2 != null) ){   
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
                else {
                    if (tipo1.equals("validade") && tipo2.equals("data atual")) {
                        if (tipo1.contains("2013") || tipo1.contains("2014") 
                                || tipo1.contains("2015")) {
                                return true;
                        }
                    }
                    else if(tipo1.equals("peso") && tipo2.equals("peso")){
                        if(imigrante.deveEntrar()){
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public void chamarImigrante(JogoTela jogo) {
        if (imigrante == null) {
             try {
                imigrante = jogo.getPessoaController().gerar(); 

                if (!imigrante.deveEntrar()) {
                    imigrante = dc.determinarDocumentoErrado(jogo, imigrante);
                }
                else{
                    if(r.gerarBool() && r.gerarBool()){ // 25% de chance
                        imigrante = dc.determinarPesoErrado(jogo, imigrante);
                    }
                }
                adicionarInformacoesId(jogo);
                adicionarInformacoesPerm(jogo);
                adicionarInformacoesPass(jogo);
               
                jogo.getPesoBotao().setText(imigrante.getPeso());
                botoesClicaveis.add(jogo.getPesoBotao());
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
        
        botoesClicaveis.add(jogo.getIdAlt());     
        botoesClicaveis.add(jogo.getIdNome());
        botoesClicaveis.add(jogo.getIdNasc());
        botoesClicaveis.add(jogo.getIdPeso());
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
        
        botoesClicaveis.add(jogo.getPermAlt());     
        botoesClicaveis.add(jogo.getPermDuracao());
        botoesClicaveis.add(jogo.getPermMotivo());
        botoesClicaveis.add(jogo.getPermNacio());
        botoesClicaveis.add(jogo.getPermNome());     
        botoesClicaveis.add(jogo.getPermPassCod());
        botoesClicaveis.add(jogo.getPermPeso());
        botoesClicaveis.add(jogo.getPermVal());      
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
        
        botoesClicaveis.add(jogo.getPassCid());      
        botoesClicaveis.add(jogo.getPassCod());      
        botoesClicaveis.add(jogo.getPassNasc());      
        botoesClicaveis.add(jogo.getPassNome());      
        botoesClicaveis.add(jogo.getPassSexo());      
        botoesClicaveis.add(jogo.getPassVal());      
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
            else {
                botao1 = null;
                tipo1 = null;
            }
        }
            
        
    }

    public void aprovarImigrante(JogoTela jogo) throws Throwable {
        imigrante = jogo.getImigrante();
        if (imigrante != null) {
            zerarInformacoesImigrante();
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
            zerarInformacoesImigrante();
            jogo.getPesoBotao().setText("");     
            //Foto foto = new Foto();
            //foto.retirarImagemNoBotao(jogo.getPessoaBotao());     
        }   
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

    public void pesoCorreto(JogoTela jogo) {
        if(tipo1 != null && tipo2 != null){
            if(tipo1.equals("peso") && tipo2.equals("peso")){
                if(imigrante.deveEntrar()){
                    JOptionPane.showMessageDialog(jogo, "Nada de Errado encontrado ao escanneá-lo!!");
                }   
                else if(r.gerarBool()){
                    JOptionPane.showMessageDialog(jogo, "Hmm.. Acho que temos uma arma escondida!!");
                }
                else JOptionPane.showMessageDialog(jogo, "Opa, ele não pode entrar com essas drogas!!");

            }
        }
    }
    
    public void fazerAtaqueTerrorista (JogoTela jogo){
        jogo.getAtaqueLabel().setVisible(true);
                   jogo.getAtaqueLabel().setIcon(
                                new ImageIcon("src/ploximo/Imagens/ataque.gif"));
                   jogo.getPloximoBotao().setEnabled(false);
                     new java.util.Timer().schedule( 
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                jogo.getAtaqueLabel().setVisible(false);

                                /*se acontecer um ataque terrorista,
                                o jogador é punido com menos dois pontos*/
                                nomearScore(jogo.getPontos(),insertNome());
                                System.out.println("Pontos:" + jogo.getPontos().getPontos());

                                //jogoController.trocarDeTela(jogo, new FimDoDia(pontos));
                                jogo.getPloximoBotao().setEnabled(true);
                            }
                        }, 5000 );
                    ataqueTerrista(jogo,jogo.getPontos());
    }

    private void zerarInformacoesImigrante() {
        for(JButton botao: botoesClicaveis){
            botao.setSelected(false);
        }
        botao1 = null;
        botao2 = null;
        tipo1 = null;
        tipo2 = null;
    }
}