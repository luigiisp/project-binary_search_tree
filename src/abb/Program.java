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
			entradaABB = new BufferedReader(new FileReader("D:\\Desktop\\projetoEDB\\ProjetoArvoreABB\\entradaABB.txt"));
			String line = entradaABB.readLine();
			String[] valoresArvore = line.split(" ");
			for(String valor : valoresArvore) {
				arvoreABB.inserirElemento(Integer.parseInt(valor));
			}
			entradaABB.close();
			
			
			comandosABB = new BufferedReader(new FileReader("D:\\Desktop\\projetoEDB\\ProjetoArvoreABB\\comandosABB.txt"));
			line = comandosABB.readLine();
			while(line != null) {
				//checar função
				line = comandosABB.readLine();
			}
			comandosABB.close();
			arvoreABB.imprimeArvore(1);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ABBException e) {
			System.out.println(e.getMessage());
		}
	}
}
