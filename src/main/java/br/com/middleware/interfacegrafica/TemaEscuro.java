package br.com.middleware.interfacegrafica;
public class TemaEscuro extends DecoradorTema { public TemaEscuro(UiComponente inner){ super(inner); } @Override public String render(){ return "[MODO ESCURO] \n" + super.render(); } }
