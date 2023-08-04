package br.com.tech4me.pedidos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.pedidos.httpclient.PizzariaClient;
import br.com.tech4me.pedidos.model.Pedido;
import br.com.tech4me.pedidos.model.Pizza;
import br.com.tech4me.pedidos.repository.PedidoRepository;
import br.com.tech4me.pedidos.shared.PedidoCompletoDto;
import br.com.tech4me.pedidos.shared.PedidoDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class PedidoServicoImpl implements PedidoServico {

    @Autowired
    private PedidoRepository repositorio;

    @Autowired
    private PizzariaClient pizzariaClient;

    @Override
    public PedidoCompletoDto CadastrarPedido(PedidoCompletoDto dto) {
        Pedido pedido = new Pedido(dto);
        repositorio.save(pedido);
        return new PedidoCompletoDto(pedido.getId(), pedido.getNomeCliente(), pedido.getIdPizza(), pedido.getValor());
    }

    @Override
    public List<PedidoCompletoDto> obterPedidos() {
        return repositorio.findAll()
            .stream()
            .map(p -> new PedidoCompletoDto(p.getId(), p.getNomeCliente(), p.getIdPizza(), p.getValor()))
            .toList();
    }

    @CircuitBreaker(name = "obterPizza", fallbackMethod = "fallbackPedidosPorId")
    @Override
    public Optional<PedidoDto> obterPedidosPorId(String id) {
        Optional<Pedido> pedido = repositorio.findById(id);

        if (pedido.isPresent()) {
            Pizza pizza = pizzariaClient.obterPizza(pedido.get().getIdPizza());
            PedidoDto pedidoComPizza = new PedidoDto(pedido.get().getNomeCliente(), pedido.get().getIdPizza(), pizza, pedido.get().getValor());
            return Optional.of(pedidoComPizza);
        } else {
            return Optional.empty();
        }
    }

    public Optional<PedidoDto> fallbackPedidosPorId(String id, Exception e) {
        Optional<Pedido> pedido = repositorio.findById(id);

        if (pedido.isPresent()) {
            PedidoDto pedidoComPizza = new PedidoDto(pedido.get().getNomeCliente(), pedido.get().getIdPizza(), null, pedido.get().getValor());
            return Optional.of(pedidoComPizza);
        } else {
            return Optional.empty();
        }
    }
    

    @Override
    public void excluirPedido(String id) {
        repositorio.deleteById(id);
    }

    @Override
    public Optional<PedidoDto> atualizarPedidoPorId(String id, PedidoDto dto) {
        Optional<Pedido> pedido = repositorio.findById(id);
        
        if (pedido.isPresent()) {
            Pedido pedidoAtualizar = new Pedido(dto);
            pedidoAtualizar.setId(id);
            repositorio.save(pedidoAtualizar);
            return Optional.of(dto);
        } else {
            return Optional.empty();
        }
    }
    
}
