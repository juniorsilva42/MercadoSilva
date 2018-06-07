package br.com.mercadosilva.modulos;

import br.com.mercadosilva.modulos.Produtos;

import java.io.IOException;
import java.util.LinkedList;

public class Vendas extends Produtos {

    private LinkedList<Produtos> produtosVendidos = new LinkedList<>();

    public void sellProduct (LinkedList<Produtos> produtos) throws ClassNotFoundException {

        Object o;

        try {
            o = this.get("products");

            int i = 0, indiceAtual = 0;
            int tamanhoLista = produtos.size();

            boolean encontrado = false;

            System.out.println("Estou vendendo...");
            while (i < tamanhoLista) {
                System.out.println(produtos.get(i).getTitle());
                i++;
            }

            /*
             *
             * Calcula a quantidade e o preço total dos produtos vendidos
             *
            int newAmount = produto.get(indiceAtual).getAmount() - amount;
            double precoTotal = produto.get(indiceAtual).getPrice() * amount;

             * Se o produto na qual o código passado for encontrado,
             * executa as operações necessárias sobre ele, adiciona na lista de produtosVendidos e
             * persiste no arquivo de vendas.
             *
            if (encontrado) {
                Produtos produtos = new Produtos();

                produtos.setTitle(produto.get(indiceAtual).getTitle());
                produtos.setCategoria(produto.get(indiceAtual).getCategoria());
                produtos.setAmount(newAmount);
                produtos.setPrice(precoTotal);
                produtos.setCode(produto.get(indiceAtual).getCode());
                 *
                 * Adiciona à lista e persiste o objeto no arquivo
                 *

                produtosVendidos.add(produtos);
                produtos.save("sales", produtosVendidos);
            } else {
                System.out.println("Produto inexistente no estoque.");
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
