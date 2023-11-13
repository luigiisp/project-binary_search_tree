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

	public void setRaiz(No no) {
		this.raiz = no;
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

	public double somarElementos() {
		return somarElementos(raiz);
	}

	private double somarElementos(No no) {
		if (no == null) {
			return 0;
		}
		return no.getValor() + somarElementos(no.getEsq()) + somarElementos(no.getDir());
	}

	private No getMaiorElementoDaArvore(No raizDaArvore) throws ABBException {
		if (raizDaArvore == null) {
			throw new ABBException("Tentativa de acessar árvore inexistente.");
		}

		while (raizDaArvore.getDir() != null) {
			raizDaArvore = raizDaArvore.getDir();
		}
		return raizDaArvore;
	}

	public void atribuirAlturaPosOrdem() {
		atribuirAlturaPosOrdem(raiz);
	}

	private void atribuirAlturaPosOrdem(No no) {
		if (no.getEsq() != null) {
			atribuirAlturaPosOrdem(no.getEsq());
		}
		if (no.getDir() != null) {
			atribuirAlturaPosOrdem(no.getDir());
		}
		atribuirAltura(no);
	}

	private void atribuirAltura(No no) {
		int alt1, alt2;
		if (no.getEsq() == null) {
			alt1 = 0;
		} else {
			alt1 = no.getEsq().getAltura();
		}
		if (no.getDir() == null) {
			alt2 = 0;
		} else {
			alt2 = no.getDir().getAltura();
		}
		if (alt1 > alt2) {
			no.setAltura(alt1 + 1);
		} else {
			no.setAltura(alt2 + 1);
		}
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

	public int inserirElemento(int valorDoElemento) throws ABBException {
		if (valorDoElemento < 0) {
			throw new ABBException("Tentativa de acessar valor negativo na árvore.");
		}
		if (raiz.getValor() == -1) {
			setRaiz(valorDoElemento);
			return -1;
		}

		int retornado = inserirElemento(raiz, valorDoElemento);
		atribuirPosicoesOrdemSimetrica();
		atribuirAlturaPosOrdem();
		return retornado;
	}

	private int inserirElemento(No no, int valorDoElemento) {
		if (no == null) {
			return 1;
		}
		if (valorDoElemento < no.getValor()) {
			int retornado = inserirElemento(no.getEsq(), valorDoElemento);
			if (retornado == 1) {
				no.setEsq(valorDoElemento);
				no.getEsq().setPai(no);
			} else if (retornado == 2) {
				return 2;
			}
		}
		if (valorDoElemento > no.getValor()) {
			int retornado = inserirElemento(no.getDir(), valorDoElemento);
			if (retornado == 1) {
				no.setDir(valorDoElemento);
				no.getDir().setPai(no);
			} else if (retornado == 2) {
				return 2;
			}
		}
		if (valorDoElemento == no.getValor()) {
			return 2;
		}
		return 0;
	}

	public int removerElemento(int valorDoElemento) throws ABBException {
		if (valorDoElemento < 0) {
			throw new ABBException("Tentativa de acessar valor negativo na árvore.");
		}
		if (removerElemento(raiz, valorDoElemento) == -1) {
			return -1;
		}
		removerElemento(raiz, valorDoElemento);
		atribuirPosicoesOrdemSimetrica();
		atribuirAlturaPosOrdem();
		return 0;
	}

	private int removerElemento(No no, int valorDoElemento) throws ABBException {

		if (no == null) {
			return -1;
		}
		if (valorDoElemento == no.getValor()) {

			No noPai = no.getPai();

			if (no.getEsq() == null && no.getDir() == null) {

				if (noPai == null) {
					this.setRaiz(null);
				} else {
					if (noPai.getEsq() == no) {
						noPai.setEsq(null);
					} else {
						noPai.setDir(null);
					}
				}
			} else if (no.getEsq() == null || no.getDir() == null) {

				No noFilho = (no.getEsq() != null) ? no.getEsq() : no.getDir();

				if (noPai == null) {
					this.setRaiz(noFilho);
				} else {
					if (no == noPai.getEsq()) {
						noPai.setEsq(noFilho);
					} else {
						noPai.setDir(noFilho);
					}
				}

			} else {

				No noSubstituto = getMaiorElementoDaArvore(no.getEsq());
				no.setValor(noSubstituto.getValor());
				removerElemento(no.getEsq(), noSubstituto.getValor());

			}

			return 1;
		}
		if (valorDoElemento < no.getValor()) {
			int retornado = removerElemento(no.getEsq(), valorDoElemento);
			if (retornado == 0) {
				return 0;
			} else if (retornado == -1) {
				return -1;
			}
		} else {
			int retornado = removerElemento(no.getDir(), valorDoElemento);
			if (retornado == 0) {
				return 0;
			} else if (retornado == -1) {
				return -1;
			}
		}
		return 0;
	}

	public int enesimoElemento(int n) throws ABBException {
		if (n <= 0) {
			throw new ABBException("Tentativa de acessar elemento com indice não positivo.");
		}
		int valorDoElemento = enesimoElemento(raiz, n);
		if (valorDoElemento == -1) {
			throw new ABBException("Tentativa de acessar elemento inexistente.");
		}
		return valorDoElemento;
	}

	private int enesimoElemento(No no, int n) {
		if (no == null) {
			return -1;
		}

		if (n == no.getPosicao()) {
			return no.getValor();
		}
		if (n < no.getPosicao()) {
			return enesimoElemento(no.getEsq(), n);
		}
		return enesimoElemento(no.getDir(), n);
	}

	public int posicao(int valorDoElemento) throws ABBException {
		if (valorDoElemento < 0) {
			throw new ABBException("Tentativa de acessar elemento com valor negativo");
		}
		int posicaoDoElemento = posicao(raiz, valorDoElemento);
		if (posicaoDoElemento == -1) {
			throw new ABBException("Tentativa de acessar elemento inexistente.");
		}
		return posicaoDoElemento;
	}

	private int posicao(No no, int valorDoElemento) {
		if (no == null) {
			return -1;
		}

		if (valorDoElemento == no.getValor()) {
			return no.getPosicao();
		}
		if (valorDoElemento < no.getValor()) {
			return posicao(no.getEsq(), valorDoElemento);
		}
		return posicao(no.getDir(), valorDoElemento);
	}

	public int mediana() {
		if (quantidadeNos() <= 0) {
			throw new ABBException("A árvore não possui nós.");
		}
		return enesimoElemento((int) Math.ceil((double) quantidadeNos() / 2));
	}

	public double media(int x) throws ABBException {
		if (quantidadeNos() <= 0) {
			throw new ABBException("A árvore não possui nós.");
		}
		return somarElementos(buscarElemento(x)) / quantidadeNos(buscarElemento(x));
	}

	public boolean ehCompleta() {
		if (raiz == null) {
			return true;
		}
		return ehCompleta(raiz);
	}

	private boolean ehCompleta(No no) {
		if (no.getEsq() != null && no.getDir() != null) {
			return ehCompleta(no.getEsq()) && ehCompleta(no.getDir());
		} else {
			if (no.getAltura() == 1 || no.getAltura() == 2) {
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean ehCheia() {
		if (raiz == null) {
			return true;
		}
		return ehCheia(raiz);
	}

	private boolean ehCheia(No no) {
		if (no.getEsq() != null && no.getDir() != null) {
			return ehCheia(no.getEsq()) && ehCheia(no.getDir());
		} else {
			if (no.getAltura() == 1) {
				return true;
			} else {
				return false;
			}
		}
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

	public void imprimeArvore(int s) throws ABBException {
		if (s == 1) {
			imprimeArvoreFormato1(raiz, 0, raiz.getAltura());
			return;
		} else {
			imprimeArvoreFormato2(raiz, 0, raiz.getAltura());
			System.out.println();
			return;
		}
	}

	private void imprimeArvoreFormato1(No no, int nivel, int alturaDaRaiz) throws ABBException {
		if (no == null) {
			throw new ABBException("Nó passado como argumento é nulo");
		}
		for (int i = 0; i < nivel; i++) {
			System.out.printf("      ");
		}
		System.out.printf("%d", no.getValor());
		for (int i = 0; i < alturaDaRaiz - (nivel); i++) {
			System.out.printf("------");
		}
		System.out.printf("\n");
		nivel++;
		if (no.getEsq() != null) {
			imprimeArvoreFormato1(no.getEsq(), nivel, alturaDaRaiz);
		}
		if (no.getDir() != null) {
			imprimeArvoreFormato1(no.getDir(), nivel, alturaDaRaiz);
		}
	}

	private void imprimeArvoreFormato2(No no, int nivel, int alturaDaRaiz) throws ABBException {
		if (no == null) {
			throw new ABBException("Nó passado como argumento é nulo");
		}
		System.out.printf("(");
		System.out.printf("%d", no.getValor());
		nivel++;
		if (no.getEsq() != null) {
			imprimeArvoreFormato2(no.getEsq(), nivel, alturaDaRaiz);
		}
		if (no.getDir() != null) {
			imprimeArvoreFormato2(no.getDir(), nivel, alturaDaRaiz);
		}
		System.out.printf(")");
	}
}
