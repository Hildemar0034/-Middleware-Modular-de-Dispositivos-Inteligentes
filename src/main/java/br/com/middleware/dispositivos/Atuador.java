package br.com.middleware.dispositivos;

public class Atuador implements Device {
    private final String id;
    private final String nome;
    public Atuador(String id, String nome){ this.id = id; this.nome = nome; }
    @Override public String getId(){ return id; }
    @Override public String getNome(){ return nome; }
    @Override public String getTipo(){ return "atuador"; }
    @Override public void realizarAcao(String acao){ System.out.println("Atuador "+nome+" executando acao: "+acao); }
}
