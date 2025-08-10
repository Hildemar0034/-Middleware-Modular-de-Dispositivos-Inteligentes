package br.com.middleware.estrategia;
public class Imediata implements EstrategiaResposta { @Override public void responder(Runnable acao){ acao.run(); } }
