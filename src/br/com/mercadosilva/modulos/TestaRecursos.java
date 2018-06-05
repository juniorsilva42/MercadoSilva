package br.com.mercadosilva.modulos;

import java.io.IOException;
import java.util.LinkedList;

public class TestaRecursos {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		Produtos produtos = new Produtos();

		produtos.saveProduct("Titulo do produto", "Descrição do produto", 179.90);
		produtos.saveProduct("Titulo do produto 2", "Descrição do produto", 179.90);
		produtos.saveProduct("Titulo do produto 3", "Descrição do produto 3", 85.90);

		produtos.getProducts();
	} 
}
