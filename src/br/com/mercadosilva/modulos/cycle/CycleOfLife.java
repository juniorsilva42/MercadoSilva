package br.com.mercadosilva.modulos.cycle;

import br.com.mercadosilva.modulos.Produtos;

import java.io.IOException;
import java.util.Scanner;

public class CycleOfLife {

    public static void createStock () throws IOException {
        Scanner in = new Scanner(System.in);
        Produtos produtos = new Produtos();

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



        produtos.setTitle(title);
        produtos.setCategoria(categoria);
        produtos.setPrice(price);
        produtos.setAmount(amount);

        produtos.saveProduct(produtos);
    }

    public static void exitProgram () {
        System.out.println("Saindo...");
        System.exit(0);
    }
}
