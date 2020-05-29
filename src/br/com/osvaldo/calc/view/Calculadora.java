package br.com.osvaldo.calc.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Calculadora extends JFrame{
	
	public Calculadora() {
		this.organizarLayout();
		setSize(232,322);
		setDefaultCloseOperation(EXIT_ON_CLOSE); //fechando aplicação ao fechar janela
		setLocationRelativeTo(null); //abrir no centro da tela
		setVisible(true); //setando visivel janela
	}
	
	private void organizarLayout() {
		// regionalizando layout
		this.setLayout(new BorderLayout());
		
		Display display = new Display(233,60);
		this.add(display, BorderLayout.NORTH);
		
		Teclado teclado = new Teclado();
		this.add(teclado, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		new Calculadora();
	}

}