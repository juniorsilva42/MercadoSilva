package br.com.mercadosilva.modulos.io;

import br.com.mercadosilva.modulos.Produtos;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class InsertWithInterface {

    public static void createStockOfProducts () throws IOException {
        Scanner in = new Scanner(System.in);

        String title, categoria;
        double price;
        int amount = 0;

        System.out.println("Título do produto: ");
        title = in.nextLine();

        System.out.println("Categoria do produto: ");
        categoria = in.nextLine();

        System.out.println("Preço do produto: ");
        price = Double.parseDouble(in.nextLine());

        System.out.println("Quantidade em estoque: ");
        amount = in.nextInt();

        Produtos produtos = new Produtos();

        produtos.setTitle(title);
        produtos.setCategoria(categoria);
        produtos.setPrice(price);
        produtos.setAmount(amount);

        produtos.saveProduct(produtos);
    }
}
