package br.com.tech4me.pedidos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.pedidos.service.PedidoServico;
import br.com.tech4me.pedidos.shared.PedidoCompletoDto;
import br.com.tech4me.pedidos.shared.PedidoDto;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoServico servico;

    @PostMapping
    public ResponseEntity<PedidoCompletoDto> cadastrarPedido(@RequestBody PedidoCompletoDto pedido) {
        return new ResponseEntity<>(servico.CadastrarPedido(pedido), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PedidoCompletoDto>> obterPedidos() {
        return new ResponseEntity<>(servico.obterPedidos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> obterPedidoPorId(@PathVariable String id) {
        Optional<PedidoDto> retorno = servico.obterPedidosPorId(id);

        if (retorno.isPresent()) {
            return new ResponseEntity<>(retorno.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPedido(@PathVariable String id) {
        servico.excluirPedido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDto> atualizarPedido(@PathVariable String id, @RequestBody PedidoDto pedido) {
        Optional<PedidoDto> retorno = servico.atualizarPedidoPorId(id, pedido);

        if (retorno.isEmpty()) {
            return new ResponseEntity<>(retorno.get(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
