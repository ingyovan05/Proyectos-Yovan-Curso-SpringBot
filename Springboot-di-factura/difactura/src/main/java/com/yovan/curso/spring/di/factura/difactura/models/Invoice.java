package com.yovan.curso.spring.di.factura.difactura.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
@RequestScope
// @JsonIgnoreProperties({"advisors","targetSource"})
public class Invoice {

  @Autowired
  private Client client;

  @Value("${invoice.description}")
  private String description;

  @Autowired
  @Qualifier("default")
  private List<Item> items;

  public Invoice() {
    System.out.println("1 .Creando el componente de la factura");
    System.out.println(client);
  }

  @PostConstruct
  public void init() {
    System.out.println("2 .Creando el componente de la factura");
    System.out.println(client.getName().concat(" pepe2"));
    client.setName(client.getName().concat(" pepe2"));
  }

  @PreDestroy
  public void destroy() {
    System.out.println("3 .Destruyendo el componente de la factura");
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  public int getTotal() {
    // int total = 0;
    // for (Item item : items) {
    // total += item.getImporte();

    // }
    int total = items.stream()
        .map(item -> item.getImporte())
        .reduce(0, (sum, importe) -> sum + importe);
    return total;
  }

}
