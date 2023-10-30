package abb;

import abb.exceptions.ABBException;

public class ArvoreABB {
	private No raiz = new No(-1);
	static int temp = 1;

	public No getRaiz() {
		return raiz;
	}

	public void setRaiz(int valorDaRaiz) {
		this.raiz = new No(valorDaRaiz);
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

	public int somarElementos() {
		return somarElementos(raiz);
	}

	private int somarElementos(No no) {
		if (no == null) {
			return 0;
		}
		return no.getValor() + somarElementos(no.getEsq()) + somarElementos(no.getDir());
	}

	public void atribuirPosicoesOrdemSimetrica() {
		atribuirPosicoesOrdemSimetrica(raiz);
		temp = 1;
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

	public No buscarElemento(int valorDoElemento) throws ABBException {
		if (valorDoElemento < 0) {
			throw new ABBException("Tentativa de inserir valor negativo na árvore.");
		}
		return buscarElemento(raiz, valorDoElemento);
	}

	private No buscarElemento(No no, int valorDoElemento) {
		if (no == null) {
			return null;
		}

		if (valorDoElemento == no.getValor()) {
			return no;
		}
		if (valorDoElemento < no.getValor()) {
			return buscarElemento(no.getEsq(), valorDoElemento);
		}
		return buscarElemento(no.getDir(), valorDoElemento);
	}

	public void inserirElemento(int valorDoElemento) throws ABBException {
		if (valorDoElemento < 0) {
			throw new ABBException("Tentativa de acessar valor negativo na árvore.");
		}
		if (raiz.getValor() == -1) {
			setRaiz(valorDoElemento);
			return;
		}
		inserirElemento(raiz, valorDoElemento);
		atribuirPosicoesOrdemSimetrica();
	}

	private int inserirElemento(No no, int valorDoElemento) {
		if (no == null) {
			return 1;
		}
		if (valorDoElemento < no.getValor()) {
			if (inserirElemento(no.getEsq(), valorDoElemento) == 1) {
				no.setEsq(valorDoElemento);
			}
		}
		if (valorDoElemento > no.getValor()) {
			if (inserirElemento(no.getDir(), valorDoElemento) == 1) {
				no.setDir(valorDoElemento);
			}
		}
		return 0;
	}

	public void removerElemento(int valorDoElemento) throws ABBException {
		if (valorDoElemento < 0) {
			throw new ABBException("Tentativa de acessar valor negativo na árvore.");
		}
		if (removerElemento(raiz, valorDoElemento) == -1) {
			throw new ABBException("Tentativa de remover elemento inexistente");
		}
	}

	private int removerElemento(No no, int valorDoElemento) {

		return 0;
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
		return enesimoElemento(Math.round(quantidadeNos() / 2));
	}

	public int media() {
		return somarElementos() / quantidadeNos();
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
