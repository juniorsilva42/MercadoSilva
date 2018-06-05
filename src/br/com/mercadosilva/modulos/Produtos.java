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
	private static LinkedList<Object> lista = new LinkedList<>();
	private static LinkedList<Produtos> produtosVendidos = new LinkedList<>();
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

        /*
         *
         * Adiciona à lista e persiste o objeto no arquivo "products".
         * "products" é responsável por armazenar TODOS os produtos em estoque
         *
         * */
		lista.add(produtos);
		produtos.save("products", lista);
	}
	
	public void getProducts (String file) throws IOException, ClassNotFoundException {
		
		Object o;

		try {
			o = persistencia.get(file);

			LinkedList<Produtos> produto = (LinkedList<Produtos>) o;
			int i = 0;
			int tamanhoLista = produto.size();

			while (i < tamanhoLista) {
				if (produtosVendidos.size() != 0 && produto.get(i).getCode().equals(produtosVendidos.get(i).getCode())) {
                    // foi vendido
                    System.out.println("Produto: "+produto.get(i).getTitle());
                    System.out.println("Categoria: "+produto.get(i).getCategoria());
                    System.out.println("Subtotal: R$ "+produto.get(i).getPrice());
                    System.out.println("Quantidade atual em estoque: "+produto.get(i).getAmount());
                    System.out.println("Código: "+produto.get(i).getCode()+"\n");
                } else {
                    System.out.println("Produto: "+produto.get(i).getTitle());
                    System.out.println("Categoria: "+produto.get(i).getCategoria());
                    System.out.println("Preço: R$ "+produto.get(i).getPrice());
                    System.out.println("Quantidade em estoque: "+produto.get(i).getAmount());
                    System.out.println("Código: "+produto.get(i).getCode()+"\n");
                }
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    public void sellProduct (String uniqueCode, int amount) throws ClassNotFoundException, IOException {

        Object o;

        try {
            o = persistencia.get("products");

            LinkedList<Produtos> produto = (LinkedList<Produtos>) o;
            int i = 0;
            int tamanhoLista = produto.size();

            boolean encontrado = false;
            int indiceAtual = 0;

            while (i < tamanhoLista) {
                if (uniqueCode.equals(produto.get(i).getCode())) {
                    encontrado = true;
                    indiceAtual = i;
                    break;
                }
                i++;
            }

            /*
            *
            * Calcula a quantidade e o preço total dos produtos vendidos
            *
            * */
            int newAmount = produto.get(indiceAtual).getAmount() - amount;
            double precoTotal = produto.get(indiceAtual).getPrice() * amount;

            /*
            *
            * Se o produto na qual o código passado for encontrado,
            * executa as operações necessárias sobre ele, adiciona na lista de produtosVendidos e
            * persiste no arquivo de vendas.
            *
            * */
            if (encontrado) {
                Produtos produtos = new Produtos();

                produtos.setTitle(produto.get(indiceAtual).getTitle());
                produtos.setCategoria(produto.get(indiceAtual).getCategoria());
                produtos.setAmount(newAmount);
                produtos.setPrice(precoTotal);
                produtos.setCode(produto.get(indiceAtual).getCode());

                /*
                *
                * Adiciona à lista e persiste o objeto no arquivo
                *
                * */
                produtosVendidos.add(produtos);
                produtos.save("sales", produtosVendidos);
            } else {
                System.out.println("Produto inexistente no estoque.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
