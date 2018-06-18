import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

import br.com.mercadosilva.modulos.Products;
import br.com.mercadosilva.modulos.cycle.CycleOfLife;
import br.com.mercadosilva.modulos.util.Helpers;
import br.com.mercadosilva.modulos.util.QuickSort;

/*
 *
 * Start é a classe principal que controla todo o processamento do Software.
 * Recuperando dados de entrada e atualização, exibição de "interface" e fomento de dados nas outras classes.
 *
 * @author Ivanicio Jr
 *
*/
public class Start {

    public static void main (String[] args) throws IOException, ClassNotFoundException {

        new Start();
    }

    public Start () throws IOException, ClassNotFoundException {

        // Aciona o procedimento padrão para exibição das opções com relação à usabilidade do programa
        Helpers.welcome();

        int opcao;

        /*
         *
         * Executa este laço enquanto a opção for diferente de 4: sair do sistema.
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

                    // Invoca o primeiro estado do ciclo de vida do programa: adicionar um produto ao sistema
                    CycleOfLife.createStock();
                    break;
                case 2:

                    System.out.println("__________________________________________");
                    System.out.println("\nLANÇAR UMA VENDA");
                    System.out.println("__________________________________________\n");

                    // Invoca o segundo estado do ciclo de vida do programa: lançar uma venda
                    CycleOfLife.launchSale();
                    break;

                case 3:
                    System.out.print("\n------------------------------------------");
                    System.out.println("\nPRODUTOS NO ESTOQUE | A - Z");
                    System.out.println("------------------------------------------");

                    Products products = new Products();
                    products.screenProducts();

                    break;
                case 4:
                    // Invoca o último estado do ciclo de vida do programa: encerrar a execução
                    CycleOfLife.exitProgram();
                    break;

                default:
                    System.out.println("\nOpção inválida!");
            }

        } while (opcao != 4);
    }
}
