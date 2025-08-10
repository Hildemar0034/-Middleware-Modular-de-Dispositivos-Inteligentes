# Middleware-Modular-de-Dispositivos-Inteligentes-Hildemar

Middleware Inteligente

Este projeto foi desenvolvido para atender aos requisitos da atividade de middleware modular da materia Padrões de Projeto, aplicando padrões , princípios SOLID e separação em camadas.
A aplicação permite o cadastro e controle de dispositivos inteligentes, comunicação por eventos e personalização de configurações.

Funcionalidades Implementadas
– Cadastro de diferentes tipos de dispositivos: sensores, atuadores, lâmpadas e ventiladores
– Monitoramento de temperatura: se o sensor detectar mais de 30°C, o ventilador é acionado automaticamente
– Baixo acoplamento e fácil substituição de componentes utilizando padrões como Factory e Dependency Inversion Principle (DIP)
– Alteração de tema (claro e escuro) no frontend
– Sistema de logging com suporte a múltiplos formatos (texto e JSON)
– Aplicação de padrões de projeto: Factory, Singleton, Composite + Decorator, Observer, Command e Strategy
– Estrutura organizada em pacotes seguindo os princípios SOLID

Tecnologias Utilizadas
– Java 17
– Spring Boot (API REST e injeção de dependência)
– Maven (gerenciamento de dependências)
– Thymeleaf (frontend com suporte a temas)
– Lombok (redução de código boilerplate)

Como Executar
Para executar o projeto é necessário ter o Java 17 e o Maven instalados.
Abra o zip no VSCode ou outro editor e rode o código.
Vá no navegador e abra o endereço http://localhost:8080 para utilizar o sistema.

Endpoints Principais
GET /api/dispositivos → Lista todos os dispositivos
POST /api/dispositivos → Cadastra um novo dispositivo
POST /api/dispositivo/{id}/acao → Executa uma ação em um dispositivo
GET /api/logs → Lista os logs registrados
POST /api/log/formato → Altera o formato de saída dos logs
POST /api/config/tema → Troca o tema da interface

Atividade feita por mim Hildemar (SOLO)
