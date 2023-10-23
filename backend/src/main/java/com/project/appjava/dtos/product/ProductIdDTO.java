package com.project.appjava.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductIdDTO {

    private Long id;
    private String name;
    private Double price;
    private String image;
}
