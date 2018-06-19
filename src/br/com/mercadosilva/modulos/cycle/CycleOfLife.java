package br.com.mercadosilva.modulos.cycle;

import br.com.mercadosilva.modulos.Products;
import br.com.mercadosilva.modulos.Vendas;
import br.com.mercadosilva.modulos.util.Helpers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class CycleOfLife {

    /*
    *
    * Procedimento intermediador para abstrair a criação de estoque de produtos no sistema
    * @return void
    *
    * */
    public static void createStock () throws IOException {

        Products produtos = new Products();
        Scanner in = new Scanner(System.in);

        String title;
        double price;
        int amount = 0;
        int code = Helpers.genCode();

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
        produtos.setCode(code);

        // Persiste o objeto no arquivo
        produtos.saveProduct(produtos);
    }

    /*
     *
     * Procedimento intermediador para abstrair o lançamento de uma venda no sistema
     * @return void
     *
     * */
    public static void launchSale () throws IOException, ClassNotFoundException {

        Products produtos = new Products();
        produtos.screenProducts();

        Vendas venda = new Vendas();

        int code, amount;
        int continua = 1;

        do {
            // Abre as possibilidade de operação
            System.out.println("\n1/2 Selecione o produto para lançar uma nova venda");
            System.out.println("Código do produto: ");

            code = Helpers.bufferedOption();

            System.out.println("2/2 Quantidade a ser vendida: ");
            amount = Helpers.bufferedOption();

            // Faz o Lançamento da venda
            venda.sellProduct(code, amount);

            System.out.println("Deseja registrar mais vendas? (1 - sim; 2 - não)");
            continua = Helpers.bufferedOption();

        } while (continua != 2);
    }

    public static void exitProgram () {
        System.out.println("Saindo...");
        System.exit(0);
    }
}
