package br.com.middleware.interfacegrafica;
public class TextLabel implements UiComponente {
    private final String texto;
    public TextLabel(String texto){ this.texto = texto; }
    @Override public String render(){ return texto; }
}
