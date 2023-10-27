package abb;

public class ArvoreABB {
	private No raiz;

	public No getRaiz() {
		return raiz;
	}

	public void setRaiz(No raiz) {
		this.raiz = raiz;
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
