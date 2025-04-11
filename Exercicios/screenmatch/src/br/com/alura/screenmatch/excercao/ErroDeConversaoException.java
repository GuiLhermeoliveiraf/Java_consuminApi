package br.com.alura.screenmatch.excercao;

public class ErroDeConversaoException extends RuntimeException {
    private String mensagem;

    public ErroDeConversaoException(String mesagem) {
        this.mensagem = mesagem;
    }

    @Override
    public String getMessage() {
        return this.mensagem;
    }
}
