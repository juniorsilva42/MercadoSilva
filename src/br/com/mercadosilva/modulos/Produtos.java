package br.com.mercadosilva.modulos;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.LinkedList;

public class Produtos extends Persistencia {

	private static final long serialVersionUID = 1L;
	private static LinkedList<Object> lista = new LinkedList<Object>();
	private static Persistencia persistencia = new Persistencia();
	
	String title;
	String description;
	double price;
	int amount;
	String code; 
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	
	public String genCode (String name) {
		
		String md5 = null;
		if (name == null) 
			return null;
		
		try {
			MessageDigest mg = MessageDigest.getInstance("MD5");
			mg.update(name.getBytes(), 0, name.length());
			md5 = new BigInteger(1, mg.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return md5;
	}
	
	public void saveProduct (String title, String description, double price) throws IOException {

		Produtos produtos = new Produtos();

		produtos.setTitle(title);
		produtos.setDescription(description);
		produtos.setPrice(price);
		produtos.setCode(this.genCode(title));

		lista.add(produtos);
		persistencia.save("products", lista);
	}
	
	public void getProducts () throws IOException, ClassNotFoundException {
		
		Object o;
		StringBuilder s = new StringBuilder();

		try {
			o = persistencia.get("products");

			LinkedList<Produtos> produto = (LinkedList<Produtos>) o;
			int tamanhoLista = produto.size();

			for (int i = 0; i < tamanhoLista; i++) {
				System.out.println("Titulo: "+produto.get(i).getTitle());
				System.out.println("Descrição: "+produto.get(i).getDescription());
				System.out.println("Preço: "+produto.get(i).getPrice());
				System.out.println("Código: "+produto.get(i).getCode()+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
