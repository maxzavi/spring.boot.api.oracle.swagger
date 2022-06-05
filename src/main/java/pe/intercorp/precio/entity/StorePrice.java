package pe.intercorp.precio.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StorePrice {
    private int code;
    private String name;
    private String shortName;
    private double price;
}
