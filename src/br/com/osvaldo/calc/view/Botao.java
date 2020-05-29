package br.com.osvaldo.calc.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Botao extends JButton{
	
	public Botao(String texto, Color cor) {
		setBackground(cor);
		setOpaque(true);
		setText(texto);
		setFont(new Font("courier", Font.PLAIN, 25));
		setForeground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	}
}