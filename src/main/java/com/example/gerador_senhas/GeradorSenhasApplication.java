package com.example.gerador_senhas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

@SpringBootApplication
public class GeradorSenhasApplication {
    public static void main(String[] args) {
        SpringApplication.run(GeradorSenhasApplication.class, args);
    }
}

// --- CONTROLLER ---
@RestController
@RequestMapping("/api/senhas")
class SenhaController {
    private final SenhaService service;

    public SenhaController(SenhaService service) {
        this.service = service;
    }

    @GetMapping("/gerar")
    public String gerarSenha(
            @RequestParam(defaultValue = "12") int tamanho,
            @RequestParam(defaultValue = "true") boolean usarMaiusculas,
            @RequestParam(defaultValue = "true") boolean usarNumeros,
            @RequestParam(defaultValue = "true") boolean usarSimbolos) {
        
        return service.gerarSenhaSegura(tamanho, usarMaiusculas, usarNumeros, usarSimbolos);
    }
}

// --- SERVICE ---
@Service
class SenhaService {
    private static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    private static final String MAIUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMEROS = "0123456789";
    private static final String SIMBOLOS = "!@#$%^&*()-_=+[]{}";

    public String gerarSenhaSegura(int tamanho, boolean maiusculas, boolean numeros, boolean simbolos) {
        StringBuilder caracteresPossiveis = new StringBuilder(MINUSCULAS);
        if (maiusculas) caracteresPossiveis.append(MAIUSCULAS);
        if (numeros) caracteresPossiveis.append(NUMEROS);
        if (simbolos) caracteresPossiveis.append(SIMBOLOS);

        SecureRandom random = new SecureRandom();
        
        return random.ints(tamanho, 0, caracteresPossiveis.length())
                .mapToObj(caracteresPossiveis::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}