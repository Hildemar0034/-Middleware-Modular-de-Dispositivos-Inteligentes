package br.com.middleware.dispositivos;

public interface Device {
    String getId();
    String getNome();
    String getTipo();
    void realizarAcao(String acao);
}
