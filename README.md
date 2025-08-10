# Middleware Inteligente - Projeto Completo

Este projeto foi desenvolvido para atender aos requisitos da atividade de middleware modular da materia Padrões de Projeto, aplicando padrões , princípios SOLID e separação em camadas. A aplicação permite o cadastro e controle de dispositivos inteligentes, comunicação por eventos e personalização de configurações.

Funcionalidades Implementadas 
– Cadastro de diferentes tipos de dispositivos: sensores, atuadores, lâmpadas e ventiladores 
– Monitoramento de temperatura: se o sensor detectar mais de 30°C, o ventilador é acionado automaticamente 
– Baixo acoplamento e fácil substituição de componentes utilizando padrões como Factory e Dependency Inversion Principle (DIP) 
– Alteração de tema (claro e escuro) no frontend – Sistema de logging com suporte a múltiplos formatos (texto e JSON) 
– Aplicação de padrões de projeto: Factory, Singleton, Composite + Decorator, Observer, Command e Strategy 
– Estrutura organizada em pacotes seguindo os princípios SOLID

Tecnologias Utilizadas 
– Java 17 
– Spring Boot (API REST e injeção de dependência) – Maven (gerenciamento de dependências) 
– Thymeleaf (frontend com suporte a temas) 
– Lombok (redução de código boilerplate)

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
- POST /api/config/tema            -> troca tema { tema: "claro""escuro" }

Atividade feita por mim Hildemar (SOLO)
