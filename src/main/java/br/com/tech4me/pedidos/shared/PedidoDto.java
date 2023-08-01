package br.com.tech4me.pedidos.shared;

import br.com.tech4me.pedidos.model.Pizza;

public record PedidoDto(String nomeCliente, String idPizza, Pizza dadosPizza, Double valor) {
    
}