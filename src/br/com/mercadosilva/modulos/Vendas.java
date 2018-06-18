package br.com.mercadosilva.modulos;

import br.com.mercadosilva.modulos.persistencia.Persistencia;

import java.io.IOException;
import java.util.LinkedList;

public class Vendas extends Persistencia implements Comparable<Vendas> {

    private LinkedList<Products> listaVendas = new LinkedList<>();

    /*
     *
     * Obtem uma lista de produtos
     * @return LinkedList<Products>
     *
     * */
    public LinkedList<Products> getSales () throws IOException, ClassNotFoundException {

        Object o;
        LinkedList<Products> vendas = null;

        try {
            o = this.get("sales-db");
            vendas = (LinkedList<Products>) o;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return vendas;
    }

    public void sellProduct (int code, int amount) throws ClassNotFoundException, IOException {

        Products produto = new Products();
        LinkedList<Products> produtosLista = produto.getProducts();

        int i = 0;
        int tamanhoLista = produtosLista.size();
        int currentIndex = 0;

        boolean found = false;

        code -= 1;

        while (i < tamanhoLista) {
            if (code == i) {
                found = true;
                currentIndex = i;
                break;
            }
            i++;
        }

        /*
        *
        * Todo: gerar um código único para cada produto, o esquema de selecionar por indice é inviável devido a ordenação em ordem alfabetica.
        *
        * */

        System.out.println(
                produtosLista.get(currentIndex).getTitle()
        );

        /*
         *
         * Se o produto na qual o código passado for encontrado,
         * executa as operações necessárias sobre ele, adiciona na lista de produtosVendidos e
         * persiste no arquivo de vendas.
         *

        if (found) {
            /*
             *
             * Calcula a quantidade e o preço total dos produtos vendidos
             *


            int newAmount = produtosLista.get(currentIndex).getAmount() - amount;
            double precoTotal = produtosLista.get(currentIndex).getPrice() * amount;

            produto.setTitle(produtosLista.get(currentIndex).getTitle());
            produto.setPrice(precoTotal);
            produto.setAmount(newAmount);

            if (!this.isExists("sales-db")) {
                listaVendas.add(produto);
                this.save("sales-db", listaVendas);
                System.out.println("\nVenda efetuada.\n");
            } else {

                Vendas vendas = new Vendas();
                listaVendas = vendas.getSales();
                listaVendas.add(produto);
                this.save("sales-db", listaVendas);
                System.out.println("\nVenda efetuada.\n");
            }


        } else {
            System.out.println("Produto indisponível ou incorreto.\nTente novamente.");
        }**/
    }

    public void screenSales () throws IOException, ClassNotFoundException {

        LinkedList<Products> vendas;
        Object o;

        try {

            o = this.get("sales-db");

            vendas = (LinkedList<Products>) o;

            int i = 0;
            int tamanhoLista = vendas.size();

            while (i < tamanhoLista) {
                System.out.println("Código: "+vendas.get(i));
                System.out.println("Produto: "+vendas.get(i).getTitle());
                System.out.println("Preço: R$ "+vendas.get(i).getPrice());
                System.out.println("Quantidade disponível em estoque: "+vendas.get(i).getAmount()+"\n");

                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(Vendas venda) {

        return 0;
    }
}
