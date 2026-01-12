package com.wjc.codetest.product.model.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "product",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_product_category_name",
                        columnNames = {"category", "name"}
                )
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "category")
    private String category;

    @Column(name = "name")
    private String name;

    public Product(String category, String name) {
        this.category = category;
        this.name = name;
    }

    public void updateData (String category, String name) {
        this.category = category;
        this.name = name;
    }

    public static Product createProduct(String category, String name) {
        return new Product(category, name);
    }

}
