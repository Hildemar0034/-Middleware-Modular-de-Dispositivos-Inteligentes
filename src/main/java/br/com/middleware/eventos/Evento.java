package br.com.middleware.eventos;
import java.time.LocalDateTime;
public class Evento {
    private final String tipo;
    private final String origemId;
    private final Object payload;
    private final LocalDateTime dataHora;
    public Evento(String tipo, String origemId, Object payload){ this.tipo=tipo; this.origemId=origemId; this.payload=payload; this.dataHora=LocalDateTime.now(); }
    public String getTipo(){ return tipo; }
    public String getOrigemId(){ return origemId; }
    public Object getPayload(){ return payload; }
    public LocalDateTime getDataHora(){ return dataHora; }
}
