import Aplicacoes.Endereco;
import BuscaApi.Busca;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Criei meu objetos
        Busca busca = new Busca();
        Endereco endereco = new Endereco();
        Scanner leitura = new Scanner(System.in);

        // Criei um ArrayList que recebe valores String
        ArrayList<String> lista = new ArrayList<>();

        // Criei um lastro de repetição que armazena os JSON dentro do Array
        // A repetição termina quando o usuario digita "sair".
        while (true) {
            System.out.println("Qual Cep Gostaria de buscar: (Ou digite sair para encerrar) ");
            String cep = leitura.nextLine();
            endereco.setUrl(cep);

            if (cep.equalsIgnoreCase("sair")) {
                System.out.println(lista);

                // Esse comando gera um arquivo .json, usando a biblioteca FileWriter
                FileWriter escrita = new FileWriter("cep.json");
                escrita.write(String.valueOf(lista));
                escrita.close();
                System.out.println("Programa finalizado com sucesso.");
                break;
            }

            System.out.println(busca.retornoApi(endereco.getUrl()));
            var json = busca.retornoApi(endereco.getUrl());
            lista.add(json);
        }
    }
}