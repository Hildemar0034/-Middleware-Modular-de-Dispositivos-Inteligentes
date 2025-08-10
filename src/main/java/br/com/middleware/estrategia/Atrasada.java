package br.com.middleware.estrategia;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class Atrasada implements EstrategiaResposta {
    private final long atrasoMs; private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    public Atrasada(long atrasoMs){ this.atrasoMs = atrasoMs; }
    @Override public void responder(Runnable acao){ scheduler.schedule(acao, atrasoMs, TimeUnit.MILLISECONDS); }
}
