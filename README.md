# ☕ Consumindo uma API em Java

## Autor: [Guilherme Guimarães](https://github.com/GuiLhermeoliveiraf/)
**[![GitHub](https://img.shields.io/badge/-GitHub-181717?style=flat&logo=github&logoColor=white)](https://github.com/GuiLhermeoliveiraf/)**  
**[![LinkedIn](https://img.shields.io/badge/-LinkedIn-0A66C2?style=flat&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/guilhermeoliiveira/)**

---

## 📌 O que é uma API?

API é uma **interface de comunicação entre sistemas**. Ela permite que dois sistemas diferentes conversem entre si de forma padronizada.

> Pense em uma API como um garçom em um restaurante:

- Você (cliente) faz um pedido (**requisição**)
- O garçom (API) leva o pedido até a cozinha (**sistema**)
- Depois, ele traz a comida de volta (**resposta**) para você

<br>

---

## 🎯 Qual a função de uma API?

A função principal de uma API é:

> Permitir a comunicação entre diferentes sistemas, oferecendo um meio padronizado para acessar funcionalidades ou dados de um software, serviço ou plataforma.

### Em outras palavras:
A API atua como um intermediário entre:

- O seu sistema/aplicativo (**cliente**)
- E um outro sistema ou serviço (**servidor**)

### ⚙️ O que a API faz, na prática?

- **Recebe requisições com parâmetros** (exemplo: um CEP)
- **Processa ou consulta dados** no sistema original (ex: banco de dados)
- **Retorna uma resposta estruturada**, geralmente em **formato JSON**

<br>

---

## 📌 Exemplo – Consulta de CEP com API ViaCEP

Seguindo o desafio final do curso, desenvolvi uma API que realiza a consulta de **CEPs informados pelo usuário** utilizando o serviço externo [ViaCEP](https://viacep.com.br).  
A aplicação retorna um arquivo JSON contendo informações como **bairro**, **localidade**, **estado**, etc.

<br>

---

#  Desafio

### 🏗️ Gerador de URL válida

Criei uma classe para ajudar na construção de uma URL que será usada para fazer a consulta à API.

```java
package Aplicacoes;

// Criei uma classe para me ajudar a criar uma URL para fazer a consulta à API
public class Endereco {
    private String url;

    // Método que recebe um valor STRING e retorna a URL no formato correto como STRING
    public void setUrl(String cep){
        String retorno = "";
        retorno = "https://viacep.com.br/ws/" + cep.replace("-", "") + "/json/";
        this.url = retorno;
    }

    // Método que retorna o valor da URL
    public String getUrl() {
        return url;
    }
}
```

### 📝 Explicação:

- O método `setUrl` **remove o hífen** do CEP (`cep.replace("-", "")`)
- Monta a URL no formato correto da API ViaCEP
- Armazena essa URL na variável interna `url`

<br>

--- 

## 📡 Consulta à API ViaCEP

Criei a classe responsável por **fazer a requisição à API** com base na URL gerada anteriormente.  
Essa classe realiza a consulta no banco de dados da ViaCEP e retorna os dados em formato JSON.

```java
package BuscaApi;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// Criei a Busca para realizar a consulta em uma API e me retornar um valor STRING
// Usei a biblioteca http
public class Busca {

    // Criei um método que recebe um valor STRING e retorna uma STRING em formato de JSON
    public String retornoApi(String cep) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(cep))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        return json;
    }
}
```

### 📝 Explicação:

- A aplicação envia uma requisição para a **URL da API (ViaCEP)**.
- O programa espera a resposta do servidor (isso pode levar alguns milissegundos ou segundos).
- Ao receber a resposta, ela é **armazenada como texto**, geralmente no formato **JSON**.

<br>

---

## 🧪 Classe Principal – Entrada de Dados e Exportação

No meu `Principal` criei um **laço de repetição**, onde aguarda o texto "sair" para encerrar, criei meus objetos e uma lista, com base nessa lista ele me retorna um arquivo ´.json´.


```java
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
```

### 📝 O que este código faz:

- Pede para o usuário digitar um CEP no console.
- Monta a URL da API do ViaCEP com base nesse CEP.
- Usa a classe `Busca` para fazer a requisição à API e obter os dados do endereço em formato JSON.
- Exibe o JSON na tela.
- Salva todos os JSONs buscados pelo usuário em uma lista.
- Quando o usuário digita `"sair"`, ele:
  - Para o loop
  - Mostra todos os dados coletados
  - Salva tudo em um arquivo chamado `cep.json`

<br>

---

## 📦 Resultado Esperado

Passei os seguintes CEPs:
- `"35669-000"` – Primeiro da minha **cidade natal** *(Papagaios - MG)*
- `"35661-028"` – Outro da minha **moradia atual** *(Pará de Minas - MG)*

### 🧾 Resultado esperado (JSON gerado):

```json
[
  {
    "cep": "35669-000",
    "logradouro": "",
    "complemento": "",
    "unidade": "",
    "bairro": "",
    "localidade": "Papagaios",
    "uf": "MG",
    "estado": "Minas Gerais",
    "regiao": "Sudeste",
    "ibge": "3146909",
    "gia": "",
    "ddd": "37",
    "siafi": "4937"
  },
  {
    "cep": "35661-028",
    "logradouro": "Rua Doutor Aloísio Procópio Lobato Menezes",
    "complemento": "",
    "unidade": "",
    "bairro": "Senador Valadares",
    "localidade": "Pará de Minas",
    "uf": "MG",
    "estado": "Minas Gerais",
    "regiao": "Sudeste",
    "ibge": "3147105",
    "gia": "",
    "ddd": "37",
    "siafi": "4941"
  }
]
```

<br>

---

## ✅ Resumindo

A API é um meio **seguro**, **padronizado** e **eficiente** de integrar diferentes sistemas.

No meu exemplo com a API **ViaCEP**, estou criando um programa que consome a API para obter **dados reais de endereços a partir de um CEP** — sem precisar de banco de dados próprio e sem acessar manualmente um site.
