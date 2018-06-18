package br.com.mercadosilva.modulos;

import br.com.mercadosilva.modulos.persistencia.Persistencia;

import java.io.IOException;
import java.util.LinkedList;

public class Vendas extends Persistencia implements Comparable<Vendas> {

    private LinkedList<Products> vendas = new LinkedList<>();

    private double totalPrice;

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void sellProduct (int code, int amount) throws ClassNotFoundException, IOException {

        Products produto = new Products();
        LinkedList<Products> produtosLista = produto.getProducts();

        int i = 0;
        int tamanhoLista = produtosLista.size();
        int currentIndex = 0;

        boolean found = false;

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
         * Se o produto na qual o código passado for encontrado,
         * executa as operações necessárias sobre ele, adiciona na lista de produtosVendidos e
         * persiste no arquivo de vendas.
         *
         **/
        if (found) {
            /*System.out.println("Título: " + produtosLista.get(currentIndex).getTitle());
            System.out.println("Preço: R$ " + produtosLista.get(currentIndex).getPrice());
            System.out.println("Quantidade em estoque: " + produtosLista.get(currentIndex).getAmount());
            System.out.println("_______________________________________________________________________________\n");
*/
            /*
             *
             * Calcula a quantidade e o preço total dos produtos vendidos
             *
             */
            int newAmount = produtosLista.get(currentIndex).getAmount() - amount;
            double precoTotal = produtosLista.get(currentIndex).getPrice() * amount;

            produto.setTitle(produtosLista.get(currentIndex).getTitle());
            produto.setPrice(precoTotal);
            produto.setAmount(newAmount);

            vendas.add(produto);
            this.save("sales", vendas);

        } else {
            System.out.println("Produto indisponível ou incorreto.\nTente novamente.");
        }
    }

    public void screenSales () throws IOException, ClassNotFoundException {

        LinkedList<Products> vendas;
        Object o;

        try {

            o = this.get("sales");

            vendas = (LinkedList<Products>) o;

            int i = 0;
            int tamanhoLista = vendas.size();

            while (i < tamanhoLista) {
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
