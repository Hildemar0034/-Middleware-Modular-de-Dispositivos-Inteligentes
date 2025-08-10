package br.com.middleware.configuracao;

import java.util.Properties;

public class ConfiguracaoSistema {
    private static final Properties props = new Properties();
    static {
        props.setProperty("tema","claro");
        props.setProperty("formatoLog","texto");
    }
    public static String get(String chave){ return props.getProperty(chave); }
    public static void set(String chave, String valor){ props.setProperty(chave, valor); }
}
