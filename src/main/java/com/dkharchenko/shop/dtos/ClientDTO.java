package com.dkharchenko.shop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientDTO {
    private String name;
    private long phoneNumber;
}
