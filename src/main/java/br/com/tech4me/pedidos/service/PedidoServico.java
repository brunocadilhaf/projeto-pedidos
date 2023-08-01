package br.com.tech4me.pedidos.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.pedidos.shared.PedidoCompletoDto;
import br.com.tech4me.pedidos.shared.PedidoDto;

public interface PedidoServico {
    PedidoCompletoDto CadastrarPedido(PedidoCompletoDto dto);
    List<PedidoCompletoDto> obterPedidos();
    Optional<PedidoDto> obterPedidosPorId(String id);
    void excluirPedido(String id);
    Optional<PedidoDto> atualizarPedidoPorId(String id, PedidoDto dto);
}
