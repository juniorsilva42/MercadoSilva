package br.com.mercadosilva.modulos;

import java.io.IOException;
import java.util.LinkedList;

public class TestaRecursos {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		Produtos produtos1 = new Produtos();
		Produtos produtos2 = new Produtos();

		produtos1.save("Titulo do produto 2", "Descrição do produto 2", 179.90);
		produtos1.save("Titulo do produto 3", "Descrição do produto 3", 179.90);
		produtos1.save("Titulo do produto 4", "Descrição do produto 4", 179.90);
		
		produtos2.save("Titulo do produto 4", "Descrição do produto 4", 179.90);
		produtos2.save("Titulo do produto 5", "Descrição do produto 5", 179.90);
		produtos2.save("Titulo do produto 6", "Descrição do produto 6", 179.90);
		
		produtos1.get();
		produtos2.get();
	} 
}
