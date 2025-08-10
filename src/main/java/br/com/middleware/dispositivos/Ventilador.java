package br.com.middleware.dispositivos;

public class Ventilador implements Device {
    private final String id;
    private final String nome;
    private boolean ligado = false;
    public Ventilador(String id, String nome){ this.id = id; this.nome = nome; }
    @Override public String getId(){ return id; }
    @Override public String getNome(){ return nome; }
    @Override public String getTipo(){ return "ventilador"; }
    @Override public void realizarAcao(String acao){
        switch(acao.toLowerCase()){
            case "ligar": case "on": ligado = true; System.out.println("Ventilador "+nome+" ligado."); break;
            case "desligar": case "off": ligado = false; System.out.println("Ventilador "+nome+" desligado."); break;
            case "toggle": ligado = !ligado; System.out.println("Ventilador "+nome+(ligado? " ligado.":" desligado.")); break;
            default: System.out.println("Ação '"+acao+"' não suportada pelo ventilador.");
        }
    }
    public boolean isLigado(){ return ligado; }
}
