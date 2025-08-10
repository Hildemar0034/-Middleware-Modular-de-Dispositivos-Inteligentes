package br.com.middleware.log;
import java.time.LocalDateTime;
public class LogEntrada {
    private final LocalDateTime instante; private final String mensagem;
    public LogEntrada(String mensagem){ this.instante = LocalDateTime.now(); this.mensagem = mensagem; }
    public java.time.LocalDateTime getInstante(){ return instante; }
    public String getMensagem(){ return mensagem; }
}
