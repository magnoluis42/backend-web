package com.project.appjava.entity.product;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pedidos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant moment;
    private OrderStatus status;
    private Double total;
    @ManyToMany
    @JoinTable(name = "pedido_produto",
            joinColumns = @JoinColumn(name = "pedido_id")
            ,inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private Set<Product> products = new HashSet<>();
    public Order(Long id, Instant moment, OrderStatus status, Double total) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.total = total;
    }
}
