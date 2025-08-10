package br.com.middleware.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import br.com.middleware.dispositivos.Ventilador;
import br.com.middleware.dispositivos.SensorTemperatura;
import br.com.middleware.eventos.EventBus;

@Component
public class SimulacaoRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        EventBus bus = new EventBus();

        Ventilador vent = new Ventilador("vent1", "Ventilador Sala");
        SensorTemperatura sensor = new SensorTemperatura("temp1", "Sensor Sala", bus, vent);

        sensor.simularLeitura(28.0); // Não liga
        sensor.simularLeitura(31.5); // Liga
    }
}
