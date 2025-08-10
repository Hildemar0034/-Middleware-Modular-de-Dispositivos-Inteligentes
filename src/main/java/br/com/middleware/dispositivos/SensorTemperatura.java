package br.com.middleware.dispositivos;

import br.com.middleware.eventos.EventBus;
import br.com.middleware.eventos.Evento;

public class SensorTemperatura implements Device {
    private final String id;
    private final String nome;
    private double ultimoValor = 0.0;
    private final EventBus bus;
    public SensorTemperatura(String id, String nome, EventBus bus, Ventilador vent){ this.id = id; this.nome = nome; this.bus = bus; }
    @Override public String getId(){ return id; }
    @Override public String getNome(){ return nome; }
    @Override public String getTipo(){ return "sensor"; }
    @Override public void realizarAcao(String acao){ System.out.println("Sensor não executa ação direta: "+acao); }
    public void simularLeitura(double valor){
        this.ultimoValor = valor;
        System.out.println("[SENSOR] " + nome + " leu: " + valor);
        if(valor > 30.0){
            bus.emitir(new Evento("TEMPERATURA_ALTA", id, valor));
            bus.emitir(new Evento("LIGAR_VENTILADOR", id, valor));
        } else {
            bus.emitir(new Evento("TEMPERATURA_OK", id, valor));
            bus.emitir(new Evento("DESLIGAR_VENTILADOR", id, valor));
        }
    }
    public double getUltimoValor(){ return ultimoValor; }
}
