package BuscaApi;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// Criei a Busca para realizar a consulta em uma API e me retonrar um valor STRING
// Usei a biblioteca http
public class Busca {

    // Criei um metado que recebe um valor STRING e retorna uma STRING em formato de JSON
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
