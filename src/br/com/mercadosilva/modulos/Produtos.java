package br.com.mercadosilva.modulos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Produtos {

	String title;
	String description;
	double price;
	int amount;
	int code; 

	public static void main (String[] args) {
		
		System.out.println(genCode("Produto 1"));
		System.out.println(genCode("Produto 2"));
		System.out.println(genCode("Produto 3"));
		System.out.println(genCode("Produto 4"));
		System.out.println(genCode("Produto 5"));
		
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getTitulo() {
		return title;
	}

	public void setTitulo(String title) {
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
	
	public static String genCode (String name) {
		
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
	
	public void save (String title, String description, double price) throws IOException {
		
		FileOutputStream file = new FileOutputStream("data/products.dat");
		ObjectOutputStream out = new ObjectOutputStream(file);
		
		try {	
			
			this.title = title; 
			this.description = description;
			this.price = price;
			this.code = genCode();
			
			out.writeObject(this);
			
			out.flush();
			out.close();
			file.flush();
			file.close();
		} catch (FileNotFoundException error) {
			System.out.println("[System log]:\n "+error);
		}
	}
}
