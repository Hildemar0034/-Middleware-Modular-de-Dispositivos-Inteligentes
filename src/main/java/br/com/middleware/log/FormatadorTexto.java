package br.com.middleware.log;
public class FormatadorTexto implements FormatadorLog {
    @Override public String formatar(LogEntrada entrada){ return "["+entrada.getInstante()+"] "+entrada.getMensagem(); }
}
