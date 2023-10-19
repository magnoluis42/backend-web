package com.project.appjava.entity.product;

public class TestProdutoCategory {

    public static void main(String[] args) {

        Product coca = new Product("coca cola",3.45, "img-coca");
        Product pizza = new Product("pizza calabresa", 28.55, "img-pizza");

        Category bebidas = new Category("bebidas");
        Category lanches = new Category("lanches");

        coca.getCategories().add(bebidas);
        bebidas.getProducts().add(coca);
    }
}
