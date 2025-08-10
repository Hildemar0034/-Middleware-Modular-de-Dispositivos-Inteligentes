package br.com.middleware.dispositivos;

import java.util.ArrayList;
import java.util.List;

public class DeviceManager {
    private final List<Device> dispositivos = new ArrayList<>();

    public void cadastrar(Device device) {
        dispositivos.add(device);
        System.out.println("Dispositivo cadastrado: " + device.getNome());
    }

    public List<Device> listar() {
        return dispositivos;
    }

    public Device buscarPorId(String id) {
        return dispositivos.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}

