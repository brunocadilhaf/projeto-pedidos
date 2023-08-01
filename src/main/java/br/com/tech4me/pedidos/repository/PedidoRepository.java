package br.com.tech4me.pedidos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.tech4me.pedidos.model.Pedido;

public interface PedidoRepository extends MongoRepository<Pedido, String> {
    
}
