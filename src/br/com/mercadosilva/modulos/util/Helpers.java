package br.com.mercadosilva.modulos.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Helpers implements HelpersInterface {

    public static int genCode () {

        Random random = new Random();
        int code = 0;

        for (int i = 0; i < 5; i++)
            code = random.nextInt();

        return Math.abs(code);

    }

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
        System.out.println("1. Cadastrar um produto\n2. Lançar uma venda\n3. Ver o estoque\n4. Relatório de Vendas\n5. Sair do Sistema");
        System.out.println("=====================================================================\n");
    }

}
