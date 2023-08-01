package br.com.tech4me.pedidos.httpclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.tech4me.pedidos.model.Pizza;

@FeignClient("pizzaria")
public interface PizzariaClient {
    @RequestMapping(method = RequestMethod.GET, value = "/pizzas/{id}")
    Pizza obterPizza(@PathVariable String id);
}
