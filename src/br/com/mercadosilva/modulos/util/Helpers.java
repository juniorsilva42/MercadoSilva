package br.com.mercadosilva.modulos.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Helpers implements HelpersInterface {

    public static int bufferedOption () throws IOException {
        BufferedReader in = null;
        int opcao = -1;

        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            opcao = Integer.parseInt(in.readLine());

        } catch (IOException e) {
            System.out.println ("I/O error");
            e.printStackTrace();
        }

        return opcao;
    }

    public static void welcome () {
        System.out.println("=====================================================================");
        System.out.println("SUPERMERCADO SILVA");
        System.out.println("=====================================================================");
        System.out.println("O que deseja fazer?\n");
        System.out.println("1. Cadastrar um produto\n2. Lançar uma venda\n3. Ver o estoque\n4. Sair do sistema");
        System.out.println("=====================================================================\n");
    }
}
