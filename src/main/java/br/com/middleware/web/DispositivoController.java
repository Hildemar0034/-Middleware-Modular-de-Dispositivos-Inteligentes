package br.com.middleware.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import br.com.middleware.fabrica.FabricaPadrao;
import br.com.middleware.fabrica.FabricaDispositivo;
import br.com.middleware.eventos.EventBus;
import br.com.middleware.dispositivos.Device;
import br.com.middleware.log.ServicoLog;
import br.com.middleware.log.FormatadorTexto;
import br.com.middleware.log.FormatadorJson;
import br.com.middleware.configuracao.ConfiguracaoSistema;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api")
public class DispositivoController {

    private final EventBus bus = new EventBus();
    private final Map<String, Device> dispositivos = new ConcurrentHashMap<>();
    private final ServicoLog servicoLog = new ServicoLog(new FormatadorTexto());
    private final FabricaDispositivo fabrica = new FabricaPadrao(bus);

    @PostConstruct
    public void init() {
        Device l1 = fabrica.criar("lampada", "lamp1", "Lâmpada Sala");
        Device s1 = fabrica.criar("sensor", "sensor1", "Sensor Temperatura");
        Device v1 = fabrica.criar("ventilador", "vent1", "Ventilador Sala");
        dispositivos.put(l1.getId(), l1);
        dispositivos.put(s1.getId(), s1);
        dispositivos.put(v1.getId(), v1);

        // Listener: quando evento de temperatura alta, procura um ventilador e liga
        bus.registrar(evento -> {
            if ("TEMPERATURA_ALTA".equals(evento.getTipo()) || "LIGAR_VENTILADOR".equals(evento.getTipo())) {
                String origem = evento.getOrigemId();
                servicoLog.registrar("Evento " + evento.getTipo() + " de " + origem + ", payload=" + evento.getPayload());
                // procura primeiro ventilador cadastrado
                for (Device d : dispositivos.values()) {
                    if ("ventilador".equalsIgnoreCase(d.getTipo())) {
                        d.realizarAcao("ligar");
                        servicoLog.registrar("Ventilador " + d.getNome() + " ligado automaticamente por evento.");
                        break;
                    }
                }
            } else if ("TEMPERATURA_OK".equals(evento.getTipo()) || "DESLIGAR_VENTILADOR".equals(evento.getTipo())) {
                servicoLog.registrar("Evento " + evento.getTipo() + " de " + evento.getOrigemId() + ", payload=" + evento.getPayload());
                for (Device d : dispositivos.values()) {
                    if ("ventilador".equalsIgnoreCase(d.getTipo())) {
                        d.realizarAcao("desligar");
                        servicoLog.registrar("Ventilador " + d.getNome() + " desligado automaticamente por evento.");
                        break;
                    }
                }
            }
        });

        servicoLog.registrar("Sistema inicializado com " + dispositivos.size() + " dispositivos.");
    }

    @GetMapping(value = "/dispositivos", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, String>> listar() {
        List<Map<String, String>> out = new ArrayList<>();
        for (Device d : dispositivos.values()) {
            Map<String, String> m = new HashMap<>();
            m.put("id", d.getId());
            m.put("nome", d.getNome());
            m.put("tipo", d.getTipo());
            out.add(m);
        }
        return out;
    }

    @PostMapping(value = "/dispositivos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> criar(@RequestBody Map<String, String> body) {
        String tipo = body.getOrDefault("tipo","").trim();
        String nome = body.getOrDefault("nome","").trim();
        String id = body.getOrDefault("id", UUID.randomUUID().toString()).trim();

        if(tipo.isEmpty()||nome.isEmpty()) return Map.of("status","erro","mensagem","Campos obrigatórios: tipo, nome");

        if(dispositivos.containsKey(id)) return Map.of("status","erro","mensagem","ID já cadastrado");

        try {
            Device novo = fabrica.criar(tipo,id,nome);
            dispositivos.put(novo.getId(), novo);
            servicoLog.registrar("Novo dispositivo cadastrado: " + nome + " ("+tipo+")");
            return Map.of("status","ok","mensagem","Dispositivo criado","id",novo.getId());
        } catch(Exception e){
            return Map.of("status","erro","mensagem","Tipo inválido: " + tipo);
        }
    }

    @PostMapping(value = "/dispositivo/{id}/acao", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,String> acionar(@PathVariable String id, @RequestBody Map<String,String> body){
        Device d = dispositivos.get(id);
        if(d==null) return Map.of("status","erro","mensagem","Dispositivo não encontrado");
        String acao = body.getOrDefault("acao","toggle");
        d.realizarAcao(acao);
        servicoLog.registrar("Ação '" + acao + "' executada em " + d.getNome());
        if("sensor".equals(d.getTipo()) && body.containsKey("valor")){
            try{
                double v = Double.parseDouble(body.get("valor"));
                if(d instanceof br.com.middleware.dispositivos.SensorTemperatura){
                    ((br.com.middleware.dispositivos.SensorTemperatura)d).simularLeitura(v);
                }
            }catch(NumberFormatException ignored){}
        }
        return Map.of("status","ok","mensagem","Ação executada");
    }

    @GetMapping(value="/logs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String,String>> logs(){
        List<Map<String,String>> out = new ArrayList<>();
        for(var e: servicoLog.getEntradas()){
            out.add(Map.of("instante", e.getInstante().toString(), "mensagem", e.getMensagem()));
        }
        return out;
    }

    @PostMapping(value="/log/formato", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,String> trocarFormatoLog(@RequestBody Map<String,String> body){
        String formato = body.getOrDefault("formato","texto");
        if("json".equalsIgnoreCase(formato)) servicoLog.setFormatador(new FormatadorJson());
        else servicoLog.setFormatador(new FormatadorTexto());
        return Map.of("status","ok","formato",formato);
    }

    @PostMapping(value="/config/tema", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,String> trocarTema(@RequestBody Map<String,String> body){
        String tema = body.getOrDefault("tema","claro");
        ConfiguracaoSistema.set("tema", tema);
        return Map.of("status","ok","tema",tema);
    }
}
