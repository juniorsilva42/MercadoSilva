package br.com.mercadosilva.modulos;

import br.com.mercadosilva.modulos.persistencia.Persistencia;
import br.com.mercadosilva.modulos.util.QuickSort;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.LinkedList;

public class Vendas extends Persistencia implements Comparable<Vendas> {

    private LinkedList<Vendas> listaVendas = new LinkedList<>();

    /*
    * Atributos:
    *
    * title: titulo do produto
    * price: preço unitário
    * totalPrice: preço total da venda
    * soldAmount: quantidade antiga do estoque
    * AvailableQuantity: quantidade disponível após uma venda
    * code: código único do produto para manipulação única
    *
    * */
    private String title;
    private double price, totalPrice;
    private int SoldAmount, AvailableQuantity;
    private int code;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getSoldAmount() {
        return SoldAmount;
    }

    public void setSoldAmount(int soldAmount) {
        SoldAmount = soldAmount;
    }

    public int getAvailableQuantity() {
        return AvailableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        AvailableQuantity = availableQuantity;
    }

    /*
     *
     * Obtem uma lista de vendas
     *
     * @return LinkedList<Products>
     *
     * */
    public LinkedList<Vendas> getSales () throws ClassNotFoundException {

        Object o;
        LinkedList<Vendas> vendas = null;

        try {
            // Se o arquivo de vendas não é vazio, ele obtem os objetos do mesmo, caso inverso, retorna o valor padrão nulo
            if (!this.isEmpty("db.sales")) {
                o = this.get("db.sales");
                vendas = (LinkedList<Vendas>) o;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return vendas;
    }

    /*
     *
     * Procedimento para vender um produto
     *
     * @arguments int code, int amount
     * @return void
     *
     * */
    public void sellProduct (int code, int amount) throws ClassNotFoundException, IOException {

        // Cria uma instância para Produtos e Vendas
        Products produto = new Products();
        Vendas vendas = new Vendas();

        // Obtem a lista de produtos
        LinkedList<Products> produtosLista = produto.getProducts();

        int i = 0;
        int tamanhoLista = produtosLista.size();
        int currentIndex = 0;

        boolean found = false;

        // Percorre a lista de produtos verificando qual produto deseja vender pelo seu código
        while (i < tamanhoLista) {
            if (produtosLista.get(i).getCode() == code) {
                // Se encontrou, found passa a ser true e armazena o indice atual onde foi encontrado
                found = true;
                currentIndex = i;
                break;
            }
            i++;
        }

        /*
         *
         * Se o produto na qual o código passado for encontrado,
         * executa as operações necessárias sobre ele, adiciona na lista de produtosVendidos e
         * persiste no arquivo de vendas.
         *
        */
        if (found) {

            // Se o estoque do produto é diferente de zero ou a quantidade que eu quero vender é menor que a quantidade
            // que eu tenho disponível, pode vender..
            if (!Products.checkIfThereAreProducts(currentIndex, amount)) {
                /*
                 *
                 * Calcula a quantidade e o preço total dos produtos vendidos
                 *
                 */
                int newAmount = produtosLista.get(currentIndex).getAmount() - amount;
                double precoTotal = produtosLista.get(currentIndex).getPrice() * amount;

                // Persiste a venda no objeto
                vendas.setTitle(produtosLista.get(currentIndex).getTitle());
                vendas.setPrice(produtosLista.get(currentIndex).getPrice());
                vendas.setTotalPrice(precoTotal);
                vendas.setSoldAmount(amount);
                vendas.setAvailableQuantity(newAmount);

                // Se o arquivo dos dados das vendas não existir ou for vazio
                if (!this.isExists("db.sales") || this.isEmpty("db.sales")) {
                    // Cria uma lista de vendas e adiciona a nova venda
                    // Em seguida, persiste no arquivo
                    listaVendas.add(vendas);
                    this.save("db.sales", listaVendas);
                    System.out.println("\nVenda efetuada com sucesso!\n");
                } else {
                    /*
                     *
                     * Caso o arquivo exista, indexo todos os dados da venda temporariamente na memória;
                     * Em seguida, adiciona na mesma lista anvoa venda, e persiste TODA a lista novamente no arquivo,
                     * com as vendas antigas e a nova venda.
                     */
                    listaVendas = vendas.getSales();
                    listaVendas.add(vendas);
                    this.save("db.sales", listaVendas);
                    System.out.println("\nVenda efetuada com sucesso!\n");
                }

                /*
                 *
                 * Cria uma lista auxiliar que vai receber todos os produtos para atualizar a nova quantidade
                 * do produto que foi vendido
                 */
                LinkedList<LinkedList<Products>> lista = new LinkedList<>();

                // Se o arquivo existe
                if (isExists("db.products")) {
                    // adiciona os produtos na lista auxliar
                    lista.add(produtosLista);

                    // obtem o produto que estou vendendo e seta A NOVA quantidade disponível dada pela quantidade antiga - quantidade vendida
                    produtosLista.get(currentIndex).setAmount(newAmount);

                    // persiste a lista de todos os produtos no arquivo novamente
                    this.save("db.products", produtosLista);
                    lista.clear();
                }
            } else
                System.out.println("\nImpossível efetuar a venda.\nProduto indisponível ou a quantidade que queres vender é maior que o disponível no estoque.\n");
        } else
            System.out.println("Produto indisponível ou incorreto.\nTente novamente.");
    }

    /*
     *
     * Procedimento para exibir as vendas da maior para menor (baseada no seu total)
     *
     * @return void
     *
     * */
    public void screenSales () throws ClassNotFoundException, IOException {

        // Se o arquivo de vendas for vazio...
        if (this.isEmpty("db.sales")) {
            System.out.println("\nAinda não há vendas registradas neste livro-caixa.\n");
        } else {

            // Cria a instância para Vendas
            Vendas vendasInstancia = new Vendas();
            DecimalFormat decimal = new DecimalFormat("0.00");

            // Obtem todas as vendas
            LinkedList<Vendas> vendasLista = vendasInstancia.getSales();
            int size = vendasLista.size(); // calcula e armazena na memória, uma unica vez, o tamanho da lista

            // Aloca um vetor auxiliar para ordená-los
            Vendas[] aux = new Vendas[size];

            // Adiciona cada item da lista no array auxiliar dos produtos
            for (int i = 0; i < size; i++)
                aux[i] = vendasLista.get(i);

            // Ordena o vetor auxiliar
            QuickSort.sort(aux);

            // Itera e retorna os produtos ordenados em ordem alfabética
            int i = 0;
            for (Vendas venda: aux) {
                System.out.println("Produto: "+aux[i].getTitle());
                System.out.println("Preço total da venda: R$ "+decimal.format(aux[i].getTotalPrice()));
                System.out.println("Preço unitário: R$ "+decimal.format(aux[i].getPrice()));
                System.out.println("Quantidade vendida: "+aux[i].getSoldAmount());
                System.out.println("Disponível ainda no estoque: "+aux[i].getAvailableQuantity());
                System.out.println("------------------------------------------");
                i++;
            }
        }
    }

    /*
     *
     * Esboça o retorno dos produtos pelo maior valor total
     */
    @Override
    public int compareTo (Vendas venda) {

        if (this.getTotalPrice() > venda.getTotalPrice())
            return -1;
        else if (this.getTotalPrice() < venda.getTotalPrice())
            return 1;

        return this.getTitle().compareToIgnoreCase(String.valueOf(venda.getTotalPrice()));
    }
}
