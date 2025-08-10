package br.com.middleware.eventos;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
public class EventBus {
    private final List<EventListener> listeners = new CopyOnWriteArrayList<>();
    public void registrar(EventListener l){ listeners.add(l); }
    public void remover(EventListener l){ listeners.remove(l); }
    public void emitir(Evento e){ for(EventListener l: listeners){ try{ l.onEvento(e); }catch(Exception ex){ ex.printStackTrace(); } } }
}
