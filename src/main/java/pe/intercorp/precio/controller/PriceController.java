package pe.intercorp.precio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import pe.intercorp.precio.entity.Price;
import pe.intercorp.precio.repository.PriceRepositoryJdbc;

@RestController
@RequestMapping("/price/api/v1")
@Tag(name = "Price", description = "Price API")
public class PriceController {
    @Autowired
    PriceRepositoryJdbc priceRepositoryJdbc ;
    @GetMapping("/{sku}")
    public ResponseEntity<Price> get(@PathVariable("sku") String sku, @RequestParam(value = "storeId", required = false) String storeId) {
        Price price = priceRepositoryJdbc.get(sku, storeId);
        if(price!=null) return ResponseEntity.ok(price);
        else return ResponseEntity.notFound().build();
    }
}
