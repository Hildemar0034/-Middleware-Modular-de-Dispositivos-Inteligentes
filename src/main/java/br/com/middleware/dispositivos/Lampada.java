package br.com.middleware.dispositivos;

public class Lampada implements Device {
    private final String id;
    private final String nome;
    private boolean ligada = false;
    public Lampada(String id, String nome){ this.id = id; this.nome = nome; }
    @Override public String getId(){ return id; }
    @Override public String getNome(){ return nome; }
    @Override public String getTipo(){ return "lampada"; }
    @Override public void realizarAcao(String acao){
        switch(acao.toLowerCase()){
            case "ligar": case "on": ligada = true; System.out.println("Lâmpada "+nome+" ligada."); break;
            case "desligar": case "off": ligada = false; System.out.println("Lâmpada "+nome+" desligada."); break;
            case "toggle": ligada = !ligada; System.out.println("Lâmpada "+nome+(ligada? " ligada.":" desligada.")); break;
            default: System.out.println("Ação '"+acao+"' não suportada pela lâmpada.");
        }
    }
}
