# Middleware Inteligente - Projeto Completo

Implementa os requisitos da atividade:
- Cadastro de diferentes dispositivos (Sensor, Atuador, Lâmpada, Ventilador)
- Monitoramento por eventos (SensorTemperatura > 30°C aciona Ventilador)
- Substituição de componentes mantendo baixo acoplamento (Factory, DIP)
- Temas (claro/escuro) no frontend
- Logging em múltiplos formatos (texto/JSON)
- Padrões aplicados: Factory, Singleton, Composite+Decorator, Observer, Command, Strategy
- Organização em pacotes e princípios SOLID

## Como rodar
Pré-requisitos: Java 17, Maven

1. Compilar e rodar:
    mvn clean package
    mvn spring-boot:run

2. Abrir no navegador:
    http://localhost:8080/

## Endpoints principais (API)
- GET  /api/dispositivos           -> lista dispositivos
- POST /api/dispositivos           -> cria novo dispositivo { nome, tipo, id (opcional) }
- POST /api/dispositivo/{id}/acao  -> aciona (body { acao, valor? })
- GET  /api/logs                   -> lista logs
- POST /api/log/formato            -> troca formato log { formato: "texto"|"json" }
- POST /api/config/tema            -> troca tema { tema: "claro"|"escuro" }

O projeto usa armazenamento em memória para simplificar a execução em ambiente local.
