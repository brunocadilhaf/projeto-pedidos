package br.com.tech4me.pedidos.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.tech4me.pedidos.shared.PedidoCompletoDto;
import br.com.tech4me.pedidos.shared.PedidoDto;

@Document("pedidos")

public class Pedido {
    @Id
    private String id;
    private String nomeCliente;
    private String idPizza;
    private Double valor;

    public Pedido() {}

    public Pedido(PedidoCompletoDto dto) {
        this.id = dto.id();
        this.nomeCliente = dto.nomeCliente();
        this.idPizza = dto.idPizza();
        this.valor = dto.valor();
    }

    public Pedido(PedidoDto dto) {
        this.nomeCliente = dto.nomeCliente();
        this.idPizza = dto.idPizza();
        this.valor = dto.valor();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNomeCliente() {
        return nomeCliente;
    }
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    public String getIdPizza() {
        return idPizza;
    }
    public void setIdPizza(String idPizza) {
        this.idPizza = idPizza;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
}
