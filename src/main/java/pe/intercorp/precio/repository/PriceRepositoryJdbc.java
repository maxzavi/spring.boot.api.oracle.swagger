package pe.intercorp.precio.repository;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.intercorp.precio.entity.Price;
import pe.intercorp.precio.entity.Product;
import pe.intercorp.precio.entity.StorePrice;

@Repository
public class PriceRepositoryJdbc {

    Logger _log = LoggerFactory.getLogger(PriceRepositoryJdbc.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Price get(String sku, String storeId) {
        _log.info("Sku {}", sku);
        String query = """
            select trim(mp.prd_lvl_number) sku,
                    mp.prd_name_full description,
                    ms.org_lvl_number,
                    ms.org_name_short,
                    ms.org_name_full,
                    p.prc_price,
                    dp.ip,
                    dp.puerto
            from tpprcvig p, prdmstee mp, orgmstee ms, hp_promo_suc dp
            where mp.prd_lvl_number = rpad(?,15,' ')
                and mp.prd_lvl_child = p.prd_lvl_child
                and p.org_lvl_child = ms.org_lvl_child
                and ms.org_lvl_number = dp.cod_suc(+)
                and ms.org_lvl_number = nvl(?,ms.org_lvl_number)
                and p.prc_price > 0
             """;
        Price priceResult=jdbcTemplate.query(query, 
            new Object[] { sku , storeId},
            new int[] { Types.NVARCHAR , Types.NVARCHAR },
            (ResultSet rs)->{
                Price price = null;
                while(rs.next()){
                    if (price==null){
                        price= new Price();
                        price.setProduct(new Product(rs.getString("sku"), 
                            rs.getString("description")));
                        price.setStorePrices(new ArrayList<>());
                    }
                    price.getStorePrices().add(new StorePrice(rs.getInt("org_lvl_number"), 
                        rs.getString("org_name_short"), 
                        rs.getString("org_name_full"), 
                        rs.getDouble("prc_price"))); 
                }
                return price;                     
            }
            );
        return priceResult;
    }    
}
