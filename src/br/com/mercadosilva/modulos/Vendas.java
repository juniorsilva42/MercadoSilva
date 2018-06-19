package br.com.mercadosilva.modulos;

import br.com.mercadosilva.modulos.persistencia.Persistencia;
import br.com.mercadosilva.modulos.util.QuickSort;

import java.io.IOException;
import java.util.LinkedList;

public class Vendas extends Persistencia implements Comparable<Vendas> {

    private String title;
    private double totalPrice;
    private int amount;
    private int code;

    private LinkedList<Vendas> listaVendas = new LinkedList<>();

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    /*
     *
     * Obtem uma lista de produtos
     * @return LinkedList<Products>
     *
     * */
    public LinkedList<Vendas> getSales () throws ClassNotFoundException {

        Object o;
        LinkedList<Vendas> vendas = null;

        try {
            o = this.get("db.sales");
            vendas = (LinkedList<Vendas>) o;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return vendas;
    }

    public void sellProduct (int code, int amount) throws ClassNotFoundException, IOException {

        Products produto = new Products();
        Vendas vendas = new Vendas();

        LinkedList<Products> produtosLista = produto.getProducts();

        int i = 0;
        int tamanhoLista = produtosLista.size();
        int currentIndex = 0;

        boolean found = false;

        while (i < tamanhoLista) {
            if (produtosLista.get(i).getCode() == code) {
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
            /*
             *
             * Calcula a quantidade e o preço total dos produtos vendidos
             *
            */

            int newAmount = produtosLista.get(currentIndex).getAmount() - amount;
            double precoTotal = produtosLista.get(currentIndex).getPrice() * amount;

            vendas.setTitle(produtosLista.get(currentIndex).getTitle());
            vendas.setTotalPrice(precoTotal);
            vendas.setAmount(newAmount);

            if (!this.isExists("db.sales")) {
                listaVendas.add(vendas);
                this.save("db.sales", listaVendas);
                System.out.println("\nVenda efetuada.\n");
            } else {

                listaVendas = vendas.getSales();
                listaVendas.add(vendas);
                this.save("db.sales", listaVendas);
                System.out.println("\nVenda efetuada.\n");
            }
        } else {
            System.out.println("Produto indisponível ou incorreto.\nTente novamente.");
        }

    }

    public void screenSales () throws ClassNotFoundException {

        // Cria a instância para Produtos
        Vendas vendasInstancia = new Vendas();

        // Obtem todos os produtos
        LinkedList<Vendas> vendasLista = vendasInstancia.getSales();
        int size = vendasLista.size();

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
            System.out.println("Preço: R$ "+aux[i].getTotalPrice());
            System.out.println("Quantidade ainda disponível em estoque: "+aux[i].getAmount()+"\n");
            i++;
        }
    }

    @Override
    public int compareTo (Vendas venda) {

        if (this.getTotalPrice() > venda.getTotalPrice())
            return -1;
        else if (this.getTotalPrice() < venda.getTotalPrice())
            return 1;

        return this.getTitle().compareToIgnoreCase(String.valueOf(venda.getTotalPrice()));
    }
}
