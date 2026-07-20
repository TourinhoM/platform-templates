package com.exemplo.servico;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

// TODO: substituir por rotas reais do serviço.
@RestController
public class DemoController {

    @GetMapping("/pedidos/{id}")
    public Map<String, Object> pedido(@PathVariable String id) throws InterruptedException {
        Thread.sleep(ThreadLocalRandom.current().nextInt(20, 150));
        return Map.of("id", id, "status", "processado");
    }
}
