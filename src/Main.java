import br.com.mercadosilva.modulos.Produtos;
import br.com.mercadosilva.modulos.io.InsertWithInterface;
import br.com.mercadosilva.modulos.util.Helpers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main (String[] args) throws IOException, ClassNotFoundException {

        System.out.println("=====================================================================");
        System.out.println("SUPERMERCADO SILVA");
        System.out.println("=====================================================================");
        System.out.println("O que deseja fazer?\n");
        System.out.println("1. Cadastrar um produto\n2. Lançar uma venda\n3. Ver o estoque\n4. Sair do sistema");
        System.out.println("=====================================================================\n");

        int opcao = 0;
        /*
         *
         * Executa este laço enquanto a ooção for diferente de 4: sair do sistema.
         *
         * */
        do {

            System.out.println("Digite uma opção correspondente acima: ");
            opcao = Helpers.bufferedOption();

            switch (opcao) {
                case 1:
                    System.out.print("\n------------------------------------------");
                    System.out.println("\nCADASTRAR UM PRODUTO");
                    System.out.println("------------------------------------------");

                    InsertWithInterface.createStockOfProducts();
                    break;
                case 2:

                    System.out.print("\n------------------------------------------");
                    System.out.println("\nLANÇAR UMA VENDA");
                    System.out.println("------------------------------------------");

                    /**

                    Exibe uma versão "lite" dos produtos
                    uma lista númerica abrindo a possibilidade de selecioná-los por um n° correspondente
                    Produtos.getProducts(1);

                    Scanner in = new Scanner(System.in);
                    int opt = 0, quantidade;

                    System.out.println("Selecione o produto: (digite a opção numérica correspondente)");
                    opt = in.nextInt();
                    opt -= 1;

                   // LinkedList<Produtos> p = Produtos.getProducts(true);

                   // System.out.println("Quantidade de "+p.get(opt).getTitle());
                    quantidade = in.nextInt();
                    */
                    break;

                case 3:
                    System.out.print("\n------------------------------------------");
                    System.out.println("\nPRODUTOS NO ESTOQUE");
                    System.out.println("------------------------------------------");

                    Produtos produtos = new Produtos();
                    produtos.getProducts();
                    break;

                case 4:
                    System.out.println("\nSaindo...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("\nOpção inválida!");
            }

        } while (opcao != 4);
    }
}
