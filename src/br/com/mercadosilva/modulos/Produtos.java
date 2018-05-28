package br.com.mercadosilva.modulos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

public class Produtos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static LinkedList<Object> lista = new LinkedList<Object>();
	
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
	
	public void save (String title, String description, double price) throws IOException {
		
		FileOutputStream file = new FileOutputStream("data/products.dat");
		ObjectOutputStream out = new ObjectOutputStream(file);
		
		try {	
			this.title = title; 
			this.description = description;
			this.price = price;
			this.code = genCode(this.getTitle()+this.description);
							
			out.writeObject(this);
			
			out.flush();
			out.close();
			file.flush();
			file.close();
			
		} catch (FileNotFoundException error) {
			System.out.println("[System log]:\n "+error);
		}
	}
	
	public void get () throws IOException, ClassNotFoundException {
		FileInputStream file = new FileInputStream("data/products.dat");
		ObjectInputStream in = new ObjectInputStream(file);
		Object o = in.readObject();
		
		in.close();
		file.close();
		
		if (o instanceof Produtos) {
			Produtos produtos = (Produtos) o;
			
			System.out.printf("%s\n%s\n%f\n%d\n%s", produtos.getTitle(), produtos.getDescription(), 
					produtos.getPrice(), produtos.getAmount(), produtos.getCode());
		}
	}
}
