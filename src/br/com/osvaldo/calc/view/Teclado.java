package br.com.osvaldo.calc.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import models.Memoria;

@SuppressWarnings("serial")
public class Teclado extends JPanel implements ActionListener{
	
	private final Color COR_CINZA_ESCURO = new Color(68,68,68);
	private final Color COR_CINZA_CLARO = new Color(99,99,99);
	private final Color COR_LARANJA = new Color(242,163,60);
	
	public Teclado() {
		//setando teclado
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		//adicionando botões através da função
		setLayout(layout);
		c.weightx = 1; //preenchendo o espaço vazio em x
		c.weighty = 1; //preenchendo o espaço vazio em y
		c.fill = GridBagConstraints.BOTH; //preenchendo espaço de cada botão
		
		//primeira lilha
		c.gridwidth = 3; //ocupando 3 espaços
		adicionarBotao("AC", COR_CINZA_ESCURO, c, 0, 0);
		c.gridwidth = 1; //voltando a ocupar 1 espaço
		adicionarBotao("/", COR_LARANJA, c, 3, 0);
		
		//segunda linha
		adicionarBotao("7", COR_CINZA_CLARO, c, 0, 1);
		adicionarBotao("8", COR_CINZA_CLARO, c, 1, 1);
		adicionarBotao("9", COR_CINZA_CLARO, c, 2, 1);
		adicionarBotao("*", COR_LARANJA, c, 3, 1);
		
		//terceira linha
		adicionarBotao("4", COR_CINZA_CLARO, c, 0, 2);
		adicionarBotao("5", COR_CINZA_CLARO, c, 1, 2);
		adicionarBotao("6", COR_CINZA_CLARO, c, 2, 2);
		adicionarBotao("-", COR_LARANJA, c, 3, 2);
		
		//terceira linha
		adicionarBotao("3", COR_CINZA_CLARO, c, 0, 3);
		adicionarBotao("2", COR_CINZA_CLARO, c, 1, 3);
		adicionarBotao("1", COR_CINZA_CLARO, c, 2, 3);
		adicionarBotao("+", COR_LARANJA, c, 3, 3);
		
		//terceira linha
		c.gridwidth = 2;
		adicionarBotao("0", COR_CINZA_CLARO, c, 0, 4);
		c.gridwidth = 1;
		adicionarBotao(",", COR_CINZA_CLARO, c, 2, 4);
		adicionarBotao("=", COR_LARANJA, c, 3, 4);
	}

	private void adicionarBotao(String texto, Color cor, GridBagConstraints c, int x, int y) {
		c.gridx = x;
		c.gridy = y;
		Botao botao = new Botao(texto, cor);
		botao.addActionListener(this); //adicionando escuta teclado
		add(botao, c);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton botao = (JButton) e.getSource();
		Memoria.getInstancia().processarComando(botao.getText());
		//System.out.println(botao.getText());
	}
}
