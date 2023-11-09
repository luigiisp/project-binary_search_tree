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
			entradaABB = new BufferedReader(
					new FileReader("D:\\Desktop\\projetoEDB\\ProjetoArvoreABB\\entradaABB.txt"));
			String line = entradaABB.readLine();
			String[] valoresArvore = line.split(" ");
			for (String valor : valoresArvore) {
				arvoreABB.inserirElemento(Integer.parseInt(valor));
			}
			entradaABB.close();

			comandosABB = new BufferedReader(
					new FileReader("D:\\Desktop\\projetoEDB\\ProjetoArvoreABB\\comandosABB.txt"));
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
				System.out.println("Posição " + temp[1] + " = " + arvoreABB.posicao(Integer.parseInt(temp[1])));
			}
			if (temp[0].equals("MEDIA")) {
				System.out.println("Média = " + arvoreABB.media(Integer.parseInt(temp[1])));
			}
			if (temp[0].equals("IMPRIMA")) {
				arvoreABB.imprimeArvore(Integer.parseInt(temp[1]));
			}
			if (temp[0].equals("INSIRA")) {
				arvoreABB.inserirElemento(Integer.parseInt(temp[1]));
			}
			if (temp[0].equals("BUSCAR")) {
				System.out
						.println(arvoreABB.buscarElemento(Integer.parseInt(temp[1])) == null ? "elemento não encontrado"
								: "chave encontrada");
			}
			if (temp[0].equals("REMOVA")) {
				arvoreABB.removerElemento(Integer.parseInt(temp[1]));
			}
		}
	}
}
