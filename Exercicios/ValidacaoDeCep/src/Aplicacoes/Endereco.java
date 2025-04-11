package Aplicacoes;
// Criei uma classe  para me ajudar a criar uma URL para fazer a consulta a API
public class Endereco {
    private String url;

    // Metado que recebe um valor STRING e retorna outro a URL na forma de STRING
    public void setUrl(String cep){
        String retorno = "";
        retorno = "https://viacep.com.br/ws/" + cep.replace("-","") + "/json/";
        this.url = retorno;
    }

    // Metado para me retornar o valor da URL
    public String getUrl() {
        return url;
    }
}
