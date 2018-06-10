package br.com.mercadosilva.modulos;

import br.com.mercadosilva.modulos.persistencia.Persistencia;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Produtos extends Persistencia {

	private static final long serialVersionUID = 1L;

	Persistencia persistencia;
	private static LinkedList<Object> lista;

	private String title, categoria;
	private double price;
	private int amount;

	public void saveProduct (Produtos produtos) throws IOException {

		try {
			lista = new LinkedList<>();
			lista.add(produtos);
			produtos.save("products", lista);

			System.out.println("Produto inserido com sucesso!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getProducts () throws IOException, ClassNotFoundException {
		
		Object o;
		LinkedList<Produtos> produto;

		try {
			o = persistencia.get("products");

			produto = (LinkedList<Produtos>) o;

			int i = 0, tamanhoLista = produto.size();


			System.out.println(produto.get(0).getTitle());


			/*
			while (i < tamanhoLista) {
				System.out.println("Produto: "+produto.get(i).getTitle());
				System.out.println("Categoria: "+produto.get(i).getCategoria());
				System.out.println("Preço: R$ "+produto.get(i).getPrice());
				System.out.println("Quantidade em estoque: "+produto.get(i).getAmount()+"\n");

				i++;
			}*/
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
