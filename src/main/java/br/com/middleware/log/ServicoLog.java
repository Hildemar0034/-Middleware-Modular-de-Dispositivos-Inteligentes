package br.com.middleware.log;
import java.util.ArrayList; import java.util.List;
public class ServicoLog {
    private final List<LogEntrada> entradas = new ArrayList<>();
    private FormatadorLog formatador;
    public ServicoLog(FormatadorLog f){ this.formatador = f; }
    public void setFormatador(FormatadorLog f){ this.formatador = f; }
    public void registrar(String msg){ LogEntrada e = new LogEntrada(msg); entradas.add(e); System.out.println(formatador.formatar(e)); }
    public List<LogEntrada> getEntradas(){ return entradas; }
}
