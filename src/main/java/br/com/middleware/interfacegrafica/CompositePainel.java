package br.com.middleware.interfacegrafica;
import java.util.ArrayList; import java.util.List;
public class CompositePainel implements UiComponente {
    private final List<UiComponente> filhos = new ArrayList<>();
    public void adicionar(UiComponente c){ filhos.add(c); }
    @Override public String render(){ StringBuilder sb = new StringBuilder(); for(UiComponente c: filhos) sb.append(c.render()).append("\n"); return sb.toString(); }
}
