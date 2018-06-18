package br.com.mercadosilva.modulos.cycle;

import br.com.mercadosilva.modulos.Products;
import br.com.mercadosilva.modulos.Vendas;
import br.com.mercadosilva.modulos.util.Helpers;

import java.io.IOException;
import java.util.Scanner;

public class CycleOfLife {

    public static void createStock () throws IOException {

        Products produtos = new Products();
        Scanner in = new Scanner(System.in);

        String title;
        double price;
        int amount = 0;

        System.out.println("Título do produto: ");
        title = in.nextLine();

        System.out.println("Preço do produto: ");
        price = Double.parseDouble(in.nextLine());

        System.out.println("Quantidade em estoque: ");
        amount = in.nextInt();

        // Persiste o objeto
        produtos.setTitle(title);
        produtos.setPrice(price);
        produtos.setAmount(amount);

        // Persiste o objeto no arquivo
        produtos.saveProduct(produtos);
    }

    public static void launchSale () throws IOException, ClassNotFoundException {

        Products produtos = new Products();
        Vendas venda = new Vendas();

        produtos.screenProducts();

        int code, amount;

        // Abre as possibilidade de operação
        System.out.println("\n1/2 Selecione o produto para lançar uma nova venda");
        System.out.println("Código do produto: ");

        code = Helpers.bufferedOption();
        code = code - 1; // Diminue -1 do código para manipular o índice na lista a partir de zero.

        System.out.println("2/2 Quantidade a ser vendida: ");
        amount = Helpers.bufferedOption();

        // Faz o Lançamento da venda
        venda.sellProduct(code, amount);

        venda.screenSales();
    }

    public static void exitProgram () {
        System.out.println("Saindo...");
        System.exit(0);
    }
}
