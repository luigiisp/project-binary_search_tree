package abb;

public class ArvoreABB {
	private No raiz;
	static int temp = 1;

	public No getRaiz() {
		return raiz;
	}

	public void setRaiz(No raiz) {
		this.raiz = raiz;
	}

	public void atribuirPosicoesOrdemSimetrica() {
		atribuirPosicoesOrdemSimetrica(raiz);
	}

	public void atribuirPosicoesOrdemSimetrica(No no) {
		if (no == null) {
			return;
		}
		atribuirPosicoesOrdemSimetrica(no.getEsq());

		no.setPosicao(temp);
		temp++;

		atribuirPosicoesOrdemSimetrica(no.getDir());

		return;
	}

	public int enesimoElemento(int posicaoDesejada) {
		return enesimoElemento(raiz, posicaoDesejada);
	}

	private int enesimoElemento(No no, int posicaoDesejada) {
		if (no == null) {
			return -1; 
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

	public String pre_ordem(No no) {
		if (no == null) {
			return "";
		}

		String string = no.getValor() + " ";
		string += pre_ordem(no.getEsq());
		string += pre_ordem(no.getDir());

		return string;
	}
}
