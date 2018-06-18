package br.com.mercadosilva.modulos;

import br.com.mercadosilva.modulos.persistencia.Persistencia;

import java.io.IOException;
import java.util.LinkedList;

public class Produtos extends Persistencia implements Comparable<Produtos> {

	private static final long serialVersionUID = 1L;

	private static LinkedList<Object> lista = new LinkedList<>();

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

	public void saveProduct (Produtos produtos) throws IOException {

		try {

			lista.add(produtos);
			this.save("products", lista);

			System.out.println("\nProduto inserido com sucesso!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void screenProducts () throws IOException, ClassNotFoundException {
		
		Object o;
		LinkedList<Produtos> produto;

		try {
			o = this.get("products");

			produto = (LinkedList<Produtos>) o;

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

	public LinkedList<Produtos> getProducts () throws IOException, ClassNotFoundException {

		Object o;
		LinkedList<Produtos> produtos = null;

		try {
			o = this.get("products");
			produtos = (LinkedList<Produtos>) o;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return produtos;
	}

	/*
	public int compareTo(Produtos produto) {

		if (this.getPrice() > produto.getPrice()) {
			return -1;
		} else if (this.getPrice() < produto.getPrice()) {
			return 1;
		}

		return this.getTitle().compareToIgnoreCase(produto.getTitle());
	}

	public int compareTo(Produtos p1, Produtos p2) {
		return p1.getTitle().toUpperCase().compareToIgnoreCase(p2.getTitle().toUpperCase());
	}*/

	@Override
	public int compareTo(Produtos produto) {

		return this.getTitle().toLowerCase().compareToIgnoreCase(produto.getTitle().toLowerCase());
	}
}
