package abb.exceptions;

public class ABBException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public ABBException(String mensagem) {
		super(mensagem);
	}
}
