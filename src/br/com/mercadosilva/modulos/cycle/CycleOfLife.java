package br.com.mercadosilva.modulos.cycle;

import br.com.mercadosilva.modulos.Products;
import br.com.mercadosilva.modulos.Vendas;
import br.com.mercadosilva.modulos.util.Helpers;

import java.io.IOException;
import java.util.Scanner;

public class CycleOfLife {

    /*
    *
    * Procedimento intermediador para abstrair a criação de estoque de produtos no sistema
    * @return void
    *
    * */
    public static void createStock () throws IOException {

        // Instancia as classes de Produtos e o Scanner para entrada de dados
        Products produtos = new Products();
        Scanner in = new Scanner(System.in);

        // Variáveis auxiliares para armazenar os dados do produto vindo do usuário
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

        // Persiste o objeto de produtos
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

        // Cria uma instância para Produtos e Vendas
        Products produtos = new Products();
        Vendas venda = new Vendas();

        // Exibe todos os produtos
        produtos.screenProducts();

        // Code: Código do produto a se vendido
        // amount: Quantidade a ser vendido
        int code, amount;

        // Controla a multiplicidade do lançamento das vendas.
        int continua = 1;

        do {
            // Abre as possibilidade de operação
            System.out.println("\n1/2 Selecione o produto para lançar uma nova venda");
            System.out.println("Código do produto: ");

            code = Helpers.bufferedOption();

            System.out.println("2/2 Quantidade a ser vendida: ");
            amount = Helpers.bufferedOption();

            // Faz o Lançamento da venda passando como parâmetro o código do produto e a quantidade
            venda.sellProduct(code, amount);

            System.out.println("Deseja registrar mais vendas? (1 - sim; 2 - não)");
            continua = Helpers.bufferedOption();

        } while (continua != 2);
    }

    /*
    *
    * Procedimento para encerrar o programa
    * */
    public static void exitProgram () {
        System.out.println("Saindo...");
        System.exit(0);
    }
}
