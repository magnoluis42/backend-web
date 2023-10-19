package com.project.appjava.entity.product;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "produtos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private String image;
    @ManyToMany
    private List<Category> categories = new ArrayList<>();

    public Product(String name, Double price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }
}
