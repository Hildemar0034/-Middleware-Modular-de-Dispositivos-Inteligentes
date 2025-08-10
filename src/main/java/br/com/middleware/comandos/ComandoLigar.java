package br.com.middleware.comandos;
import br.com.middleware.dispositivos.Device;
public class ComandoLigar implements Comando {
    private final Device device;
    public ComandoLigar(Device device){ this.device = device; }
    @Override public void executar(){ device.realizarAcao("ligar"); }
}
