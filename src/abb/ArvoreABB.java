package abb;

import abb.exceptions.ABBException;

public class ArvoreABB {
	private No raiz;
	static int temp = 1;

	public No getRaiz() {
		return raiz;
	}

	public void setRaiz(No raiz) {
		this.raiz = raiz;
	}

	public int quantidadeNos() {
		return quantidadeNos(raiz);
	}

	private int quantidadeNos(No no) {
		if (no == null) {
			return 0;
		}
		return 1 + quantidadeNos(no.getEsq()) + quantidadeNos(no.getDir());
	}

	public int soma() {
		return soma(raiz);
	}

	private int soma(No no) {
		if (no == null) {
			return 0;
		}
		return no.getValor() + soma(no.getEsq()) + soma(no.getDir());
	}

	public void atribuirPosicoesOrdemSimetrica() {
		atribuirPosicoesOrdemSimetrica(raiz);
	}

	public void atribuirPosicoesOrdemSimetrica(No no) {
		if (no == null) {
			throw new ABBException("Nó passado como argumento é nulo!");
		}
		if (no.getEsq() != null) {
			atribuirPosicoesOrdemSimetrica(no.getEsq());
		}
		no.setPosicao(temp);
		temp++;
		if (no.getDir() != null) {
			atribuirPosicoesOrdemSimetrica(no.getDir());
		}
		return;
	}

	public int enesimoElemento(int posicaoDesejada) {
		return enesimoElemento(raiz, posicaoDesejada);
	}

	private int enesimoElemento(No no, int posicaoDesejada) {
		if (no == null) {
			throw new ABBException("Posição desejada não encontrada!");
		}

		if (posicaoDesejada == no.getPosicao()) {
			return no.getValor();
		}

		if (posicaoDesejada < no.getPosicao()) {
			return enesimoElemento(no.getEsq(), posicaoDesejada);
		} else {
			return enesimoElemento(no.getDir(), posicaoDesejada);
		}
	}

	public int posicao(int x) {
		return posicao(raiz, x);
	}

	private int posicao(No no, int valorBuscado) {
		if (no == null) {
			throw new ABBException("Valor desejado não encontrado");
		}

		if (valorBuscado == no.getValor()) {
			return no.getPosicao();
		}

		if (valorBuscado < no.getValor()) {
			return posicao(no.getEsq(), valorBuscado);
		} else {
			return posicao(no.getDir(), valorBuscado);
		}
	}
	
	public int mediana() {
		return enesimoElemento(Math.round(quantidadeNos()/2));
	}
	
	public int media() {
		return soma() / quantidadeNos();
	}

	public String pre_ordem(No no) {
		if (no == null) {
			throw new ABBException("Nó passado como argumento é nulo");
		}

		String string = no.getValor() + " ";
		if (no.getEsq() != null) {
			string += pre_ordem(no.getEsq());
		}
		if (no.getDir() != null) {
			string += pre_ordem(no.getDir());
		}
		return string;
	}
}
