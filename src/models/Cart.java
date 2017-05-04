package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Adam Blasko on 03.05.2017.
 */
public class Cart {
    private Map<Product, Integer> productQuantities;

    public Cart(){
        productQuantities = new HashMap<>();
    }

    public void changeProductQuantity(Product product, int changeQuantityBy) {
        if (productQuantities.containsKey(product)){
            Integer oldQuantity = productQuantities.get(product);
            if (oldQuantity + changeQuantityBy <= 0){
                productQuantities.remove(product);
            }
            else{
                Integer newQuantity = oldQuantity + changeQuantityBy;
                productQuantities.replace(product, newQuantity);
            }
        }
        else{
            productQuantities.put(product, changeQuantityBy);
        }
    }

    public void removeProduct(Product product){
        if (productQuantities.containsKey(product)){
            productQuantities.remove(product);
        }
    }

    public int getProductsPriceSum(){
        int sum = 0;
        for (Product product : productQuantities.keySet()) {
            sum += product.getPrice() * productQuantities.get(product);
        }
        return sum;
    }

    public int getQuantitySum(){
        int sum = 0;
        for (Integer quantity : productQuantities.values()) {
            sum += quantity;
        }
        return sum;
    }

    public List<ProductWithQuantity> getProductsWithQuantities(){
        List<ProductWithQuantity> result = new ArrayList<>();
        for (Product product : productQuantities.keySet()) {
            result.add(new ProductWithQuantity(product, productQuantities.get(product)));
        }
        return result;
    }
}
