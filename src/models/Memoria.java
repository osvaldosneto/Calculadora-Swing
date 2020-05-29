package models;

import java.util.ArrayList;
import java.util.List;

public class Memoria {
	
	private enum TipoComando {
		ZERAR, NUMERO, DIV, MULT, SUB, SOMA, IGUAL, VIRGULA;
	}
	private static final Memoria instancia = new Memoria();
	
	private String texto = "";
	private String buffer = "";
	private boolean substituir = false;
	private TipoComando ultimaOperacao = null;
	
	private final List<MemoriaObservador> observadores = new ArrayList<>();;
	
	private Memoria() {		
	}
	
	public static Memoria getInstancia() {
		return instancia;
	}
	
	public String getTexto() {
		return texto.isEmpty() ? "0" : texto;
	}
	
	public void adicionarObservador(MemoriaObservador observador) {
		observadores.add(observador);
	}
	
	public void processarComando(String valor) {
		TipoComando tipo = detectarTipoComando(valor);
		if(tipo == null) {
			return;
		}else if (tipo == tipo.ZERAR){
			texto = "";
			buffer = "";
			substituir = false;
			ultimaOperacao = null;
		} else if(tipo == tipo.NUMERO || tipo == tipo.VIRGULA) {
			if(!substituir) {
				texto += valor;
			} else {
				texto = valor;
			}
			substituir = false;
		} else {
			substituir = true;
			texto = obterResultadoOperacao();
			buffer = texto;
			ultimaOperacao = tipo;
		}
		observadores.forEach(o -> o.valorAlterado(this.getTexto()));
	}

	private String obterResultadoOperacao() {
		if(ultimaOperacao == null || ultimaOperacao == TipoComando.IGUAL) {
			return texto;
		}
		
		double resultado = 0;
		double nBuffer = Double.parseDouble(buffer.replace(",", "."));
		double nTexto = Double.parseDouble(texto.replace(",", "."));
		
		if(ultimaOperacao == TipoComando.SOMA) {
			resultado = nBuffer + nTexto;
		}else if(ultimaOperacao == TipoComando.SUB) {
			resultado = nBuffer - nTexto;
		}else if(ultimaOperacao == TipoComando.DIV) {
			resultado = nBuffer / nTexto;
		}else if(ultimaOperacao == TipoComando.MULT) {
			resultado = nBuffer * nTexto;
		}
		
		String resultadoString = Double.toString(resultado).replace(".", ",");	
		boolean inteiro = resultadoString.endsWith(",0");
		
		return inteiro ? resultadoString.replace(",0", "") : resultadoString;
	}

	private TipoComando detectarTipoComando(String txt) {
		if(texto.isEmpty() && txt == "0") {
			return null;
		}
		try {
			Integer.parseInt(txt);
			return TipoComando.NUMERO;
		} catch (NumberFormatException e) {
			if(txt.equals("AC")) {
				return TipoComando.ZERAR;
			} else if(txt.equals("/")) {
				return TipoComando.DIV;
			}else if(txt.equals("*")) {
				return TipoComando.MULT;
			}else if(txt.equals("+")) {
				return TipoComando.SOMA;
			}else if(txt.equals("-")) {
				return TipoComando.SUB;
			}else if(txt.equals("=")) {
				return TipoComando.IGUAL;
			}else if(txt.equals(",") && !texto.contains(",")) {
				return TipoComando.VIRGULA;
			} 
			return null;
		}
	}
}