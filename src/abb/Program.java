package abb;

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
		
		arvore.atribuirPosicoesOrdemSimetrica();
		
		String preOrdem = arvore.pre_ordem(arvore.getRaiz());
		System.out.println(preOrdem);
		
		System.out.println(arvore.enesimoElemento(1));

	}

}
