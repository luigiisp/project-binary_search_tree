package abb;

import abb.exceptions.ABBException;

public class Program {

	public static void main(String[] args) {
		ArvoreABB arvore = new ArvoreABB();
		arvore.setRaiz(new No(50));

		arvore.getRaiz().setEsq(35);
		arvore.getRaiz().getEsq().setEsq(25);
		arvore.getRaiz().getEsq().getEsq().setDir(30);

		arvore.getRaiz().setDir(70);
		arvore.getRaiz().getDir().setEsq(65);
		arvore.getRaiz().getDir().setDir(90);
		arvore.getRaiz().getDir().getDir().setEsq(80);
		try {
			arvore.atribuirPosicoesOrdemSimetrica();
			
			String preOrdem = arvore.pre_ordem(arvore.getRaiz());
			System.out.println(preOrdem);
			
			
			System.out.println(arvore.posicao(25));
			System.out.println(arvore.enesimoElemento(4));
			System.out.println(arvore.quantidadeNos());
			System.out.println(arvore.media());
			System.out.println(arvore.mediana());
		}catch(ABBException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
