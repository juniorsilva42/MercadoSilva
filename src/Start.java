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

                    // Cria a instância para Produtos
                    Products produtos = new Products();

                    // Obtem todos os produtos
                    LinkedList<Products> listaProdutos = produtos.getProducts();
                    int size = listaProdutos.size();

                    // Aloca um vetor auxiliar para ordená-los
                    Products[] aux = new Products[size];

                    // Adiciona cada item da lista no array auxiliar dos produtos
                    for (int i = 0; i < size; i++)
                        aux[i] = listaProdutos.get(i);

                    // Ordena o vetor auxiliar
                    QuickSort.sort(aux);

                    // Itera e retorna os produtos ordenados em ordem alfabética
                    int i = 0;
                    for (Products p: aux) {
                        System.out.println("Produto: "+aux[i].getTitle());
                        System.out.println("Preço: R$ "+aux[i].getPrice());
                        System.out.println("Quantidade em estoque: "+aux[i].getAmount()+"\n");
                        i++;
                    }

                    // "Zera" as duas listas da memória no fim da execução desse ciclo de vida
                    // A principio, zera o array auxiliar, ulterior, zera a LinkedList
                    Arrays.fill(aux, null);
                    listaProdutos.clear();
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
