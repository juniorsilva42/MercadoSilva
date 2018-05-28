package br.com.mercadosilva.modulos;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import br.com.mercadosilva.modulos.Persistencia;

public class Produtos extends Persistencia {

	/**
	 * 
	 */
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
		
		this.title = title; 
		this.description = description;
		this.price = price;
		this.code = genCode(this.getTitle()+this.description);
		
		persistencia.save("products", this);
	}
	
	public void getProducts () throws ClassNotFoundException, IOException {
		
		Object o = persistencia.get("products");
		
		if (o instanceof Produtos) {
			Produtos produtos = (Produtos) o;
			
			System.out.printf("%s\n%s\n%f\n%d\n%s", produtos.getTitle(), produtos.getDescription(), 
					produtos.getPrice(), produtos.getAmount(), produtos.getCode());
		}
		
	}
}
