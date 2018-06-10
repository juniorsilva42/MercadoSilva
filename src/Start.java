import java.io.IOException;
import java.util.LinkedList;

import br.com.mercadosilva.modulos.Produtos;
import br.com.mercadosilva.modulos.io.InsertWithInterface;
import br.com.mercadosilva.modulos.util.Helpers;

/*
 *
 * Start é a classe principal que controla todo o processamento do Software.
 * Recuperando dados de entrada e atualização, exibição de "interface" e fomento de dados nas outras classes.
 *
 * @author Ivanicio Jr
*/
public class Start {

    public static void main (String[] args) throws IOException, ClassNotFoundException {

        Produtos produtos = new Produtos();

        produtos.setTitle("Titutlo aqui");
        produtos.setCategoria("Categoria aqui");
        produtos.setPrice(88.20);
        produtos.setAmount(10);

        produtos.saveProduct(produtos);

        produtos.getProducts();
        // new Start();
    }

    public Start () throws IOException, ClassNotFoundException {

        // Aciona o procedimento padrão para exibição das opções com relação à usabilidade do programa
        Helpers.welcome();

        int opcao;
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
