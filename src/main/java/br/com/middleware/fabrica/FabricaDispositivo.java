package br.com.middleware.fabrica;

import br.com.middleware.dispositivos.Device;

public interface FabricaDispositivo {
    Device criar(String tipo, String id, String nome);
}
