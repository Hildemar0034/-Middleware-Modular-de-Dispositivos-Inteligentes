package br.com.middleware.fabrica;

import br.com.middleware.dispositivos.*;
import br.com.middleware.eventos.EventBus;

public class FabricaPadrao implements FabricaDispositivo {
    private final EventBus bus;
    public FabricaPadrao(EventBus bus){ this.bus = bus; }
    @Override public Device criar(String tipo, String id, String nome){
        switch(tipo.toLowerCase()){
            case "lampada": case "lâmpada": return new Lampada(id,nome);
            case "sensor": return new SensorTemperatura(id,nome,bus, null);
            case "ventilador": return new Ventilador(id,nome);
            case "atuador": return new Atuador(id,nome);
            default: throw new IllegalArgumentException("Tipo desconhecido: "+tipo);
        }
    }
}
