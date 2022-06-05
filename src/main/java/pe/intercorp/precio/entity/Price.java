package pe.intercorp.precio.entity;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Price {
    private Product product;
    private List<StorePrice> storePrices;
}
