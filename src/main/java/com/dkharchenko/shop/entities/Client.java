package com.dkharchenko.shop.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone_number")
    private long phoneNumber;

    public Client(String name, long phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
