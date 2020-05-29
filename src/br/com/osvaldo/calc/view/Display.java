package br.com.osvaldo.calc.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import models.Memoria;
import models.MemoriaObservador;

@SuppressWarnings("serial")
public class Display extends JPanel implements MemoriaObservador{
	
	@SuppressWarnings("unused")
	private final JLabel label;
	
	public Display(int l, int h) {
		
		//comando responsável por adicionar observador para receber a notificação
		Memoria.getInstancia().adicionarObservador(this);
		
		setPreferredSize(new Dimension(l,h));
		setBackground(new Color(46,49,50));
		label = new JLabel(Memoria.getInstancia().getTexto());
		label.setForeground(Color.white); //editando cor da fonte
		label.setFont(new Font("courier", Font.PLAIN, 30)); //editando fonte
		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 25)); //alinhando no campo
		add(label);
	}

	@Override
	public void valorAlterado(String novoValor) {
		label.setText(novoValor);
		
	}

}
