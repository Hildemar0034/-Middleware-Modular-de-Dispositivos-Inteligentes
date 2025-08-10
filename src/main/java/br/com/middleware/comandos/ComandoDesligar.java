package br.com.middleware.comandos;
import br.com.middleware.dispositivos.Device;
public class ComandoDesligar implements Comando {
    private final Device device;
    public ComandoDesligar(Device device){ this.device = device; }
    @Override public void executar(){ device.realizarAcao("desligar"); }
}
