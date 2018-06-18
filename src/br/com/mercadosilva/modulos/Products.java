package br.com.mercadosilva.modulos;

import br.com.mercadosilva.modulos.persistencia.Persistencia;

import java.io.IOException;
import java.util.LinkedList;

public class Products extends Persistencia implements Comparable<Products> {

	private static final long serialVersionUID = 1L;

	private static LinkedList<Object> productsList = new LinkedList<>();

	private String title;
	private double price;
	private int amount;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public void saveProduct (Products produtos) throws IOException {

		try {

			productsList.add(produtos);
			this.save("db.products", productsList);

			System.out.println("\nProduto inserido com sucesso!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void screenProducts () throws IOException, ClassNotFoundException {
		
		Object o;
		LinkedList<Products> produto;

		try {
			o = this.get("db.products");

			produto = (LinkedList<Products>) o;

			int i = 0;
			int tamanhoLista = produto.size();

			while (i < tamanhoLista) {
				System.out.println("Código: "+(i+1));
				System.out.println("Produto: "+produto.get(i).getTitle());
				System.out.println("Preço: R$ "+produto.get(i).getPrice());
				System.out.println("Quantidade em estoque: "+produto.get(i).getAmount()+"\n");
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public LinkedList<Products> getProducts () throws IOException, ClassNotFoundException {

		Object o;
		LinkedList<Products> produtos = null;

		try {
			o = this.get("db.products");
			produtos = (LinkedList<Products>) o;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return produtos;
	}

	/*
	public int compareTo(Products produto) {

		if (this.getPrice() > produto.getPrice()) {
			return -1;
		} else if (this.getPrice() < produto.getPrice()) {
			return 1;
		}

		return this.getTitle().compareToIgnoreCase(produto.getTitle());
	}

	public int compareTo(Products p1, Products p2) {
		return p1.getTitle().toUpperCase().compareToIgnoreCase(p2.getTitle().toUpperCase());
	}*/

	@Override
	public int compareTo(Products produto) {

		return this.getTitle().toLowerCase().compareToIgnoreCase(produto.getTitle().toLowerCase());
	}
}
