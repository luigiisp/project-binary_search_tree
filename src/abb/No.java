package abb;

public class No {
	private int valor;
	private No pai;
	private No esq;
	private No dir;
	private int posicao;

	public No(int valor) {
		this.valor = valor;
		this.pai = null;
		this.dir = null;
		this.esq = null;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public No getEsq() {
		return esq;
	}

	public void setEsq(int valor) {
		No noEsq = new No(valor);
		this.esq = noEsq;
	}

	public No getDir() {
		return dir;
	}

	public void setDir(int valor) {
		No noDir = new No(valor);
		this.dir = noDir;
	}
	public No getPai() {
		return pai;
	}

	public void setPai(int valor) {
		No noPai = new No(valor);
		this.pai = noPai;
	}

}