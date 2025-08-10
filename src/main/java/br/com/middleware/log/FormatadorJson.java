package br.com.middleware.log;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FormatadorJson implements FormatadorLog {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String formatar(LogEntrada entrada) {
        try {
            return mapper.writeValueAsString(entrada);
        } catch (Exception e) {
            return "{\"error\":\"json\"}";
        }
    }
}
