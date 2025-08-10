package br.com.middleware.interfacegrafica;
public abstract class DecoradorTema implements UiComponente {
    protected final UiComponente inner;
    public DecoradorTema(UiComponente inner){ this.inner = inner; }
    @Override public String render(){ return inner.render(); }
}
