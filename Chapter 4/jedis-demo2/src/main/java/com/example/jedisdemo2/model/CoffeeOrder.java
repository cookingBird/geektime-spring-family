package com.example.jedisdemo2.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "t_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
public class CoffeeOrder extends BaseEntity implements Serializable {
    private String customer;
    @ManyToMany
    @JoinTable(name = "t_coffee_order")
    @OrderBy("id")
    private List<Coffee> items;

    @Enumerated()
    @Column(nullable = false)
    private OrderState state;
}
