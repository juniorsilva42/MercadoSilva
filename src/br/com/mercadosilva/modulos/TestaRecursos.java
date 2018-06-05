package br.com.mercadosilva.modulos;

import java.io.IOException;

public class TestaRecursos {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		Produtos produtos = new Produtos();

		produtos.saveProduct("Coca-Cola 2 Litros", "Bebidas", 10, 5.98);
		produtos.saveProduct("Cerveja em lata Brahma", "Bebidas", 50, 2.45);
		produtos.saveProduct("Salgadinho Cheetos", "Guloseimas", 250, 3.80);
		produtos.saveProduct("Nutella", "Guloseimas", 85, 9.90);

		System.out.println("=========================== Produtos no estoque ===============================");
		produtos.getProducts("products");

		System.out.println("-----------------------------------------------------------------------------");
		produtos.sellProduct("b72db6ce695bf03bf475c1e93f8d9880", 5);

		System.out.println("=========================== Produtos vendidos ===============================");
		produtos.getProducts("sales");
	}
}
