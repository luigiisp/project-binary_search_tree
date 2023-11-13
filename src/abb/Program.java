package abb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import abb.exceptions.ABBException;

public class Program {

	public static void main(String[] args) {
		BufferedReader entradaABB = null;
		BufferedReader comandosABB = null;
		ArvoreABB arvoreABB = new ArvoreABB();
		try {
			entradaABB = new BufferedReader(new FileReader(args[0]));
			String line = entradaABB.readLine();
			String[] valoresArvore = line.split(" ");
			for (String valor : valoresArvore) {
				arvoreABB.inserirElemento(Integer.parseInt(valor));
			}
			entradaABB.close();

			comandosABB = new BufferedReader(new FileReader(args[1]));
			line = comandosABB.readLine();
			while (line != null) {
				checarFuncao(line, arvoreABB);
				line = comandosABB.readLine();
			}
			comandosABB.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ABBException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void checarFuncao(String line, ArvoreABB arvoreABB) {
		if (line.equals("MEDIANA")) {
			System.out.println("Mediana = " + arvoreABB.mediana());
		} else if (line.equals("CHEIA")) {
			System.out.println(arvoreABB.ehCheia() == true ? "A árvore é cheia" : "A árvore não é cheia");
		} else if (line.equals("COMPLETA")) {
			System.out.println(arvoreABB.ehCompleta() == true ? "A árvore é completa" : "A árvore não é completa");
		} else if (line.equals("PREORDEM")) {
			System.out.println(arvoreABB.pre_ordem(arvoreABB.getRaiz()));
		} else {
			String[] temp = line.split(" ");
			if (temp[0].equals("ENESIMO")) {
				System.out
						.println("Elemento " + temp[1] + " = " + arvoreABB.enesimoElemento(Integer.parseInt(temp[1])));
			}
			if (temp[0].equals("POSICAO")) {
				System.out
						.println("Posição do valor " + temp[1] + " = " + arvoreABB.posicao(Integer.parseInt(temp[1])));
			}
			if (temp[0].equals("MEDIA")) {
				System.out.println("Média de " + temp[1] + " = " + arvoreABB.media(Integer.parseInt(temp[1])));
			}
			if (temp[0].equals("IMPRIMA")) {
				arvoreABB.imprimeArvore(Integer.parseInt(temp[1]));
			}
			if (temp[0].equals("INSIRA")) {
				int auxiliar = arvoreABB.inserirElemento(Integer.parseInt(temp[1]));
				if (auxiliar == 2) {
					System.out.println(temp[1] + " já está na árvore, não pode ser inserido");
				} else {
					System.out.println(temp[1] + " adicionado");
				}
			}
			if (temp[0].equals("BUSCAR")) {
				System.out
						.println(arvoreABB.buscarElemento(Integer.parseInt(temp[1])) == null ? "elemento não encontrado"
								: "chave encontrada");
			}
			if (temp[0].equals("REMOVA")) {
				int auxiliar = arvoreABB.removerElemento(Integer.parseInt(temp[1]));
				if (auxiliar == -1) {
					System.out.println(temp[1] + " não está na árvore, não pode ser removido");
				} else {
					System.out.println(temp[1] + " removido");
				}
			}
		}
	}
}
