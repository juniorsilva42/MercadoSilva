package br.com.mercadosilva.modulos;

import br.com.mercadosilva.modulos.br.com.mercadosilva.persistencia.Persistencia;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

public class Produtos extends Persistencia
{

	private static final long serialVersionUID = 1L;
	private static LinkedList<Object> lista = new LinkedList<Object>();
	private static Persistencia persistencia = new Persistencia();
	
	private String title;
    private String categoria;
	private double price;
	private int amount;
	private String code;
	
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
	
	public void saveProduct (String title, String categoria, int amount, double price) throws IOException {

		Produtos produtos = new Produtos();

		produtos.setTitle(title);
		produtos.setCategoria(categoria);
        produtos.setAmount(amount);
        produtos.setPrice(price);
		produtos.setCode(this.genCode(title));

		lista.add(produtos);
		// Persiste o objeto no arquivo
		produtos.save("products", lista);
	}
	
	public void getProducts () throws IOException, ClassNotFoundException {
		
		Object o;

		try {
			o = persistencia.get("products");

			LinkedList<Produtos> produto = (LinkedList<Produtos>) o;
			int i = 0;
			int tamanhoLista = produto.size();

			while (i < tamanhoLista) {
				System.out.println("Titulo: "+produto.get(i).getTitle());
				System.out.println("Categoria: "+produto.get(i).getCategoria());
				System.out.println("Preço: R$ "+produto.get(i).getPrice());
                System.out.println("Quantidade em estoque: "+produto.get(i).getAmount());
				System.out.println("Código: "+produto.get(i).getCode()+"\n");
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
