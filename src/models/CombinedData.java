package models;

import java.util.List;

/**
 * Created by Adam Blasko on 04.05.2017.
 */
public class CombinedData {
    private List<Product> products;
    private List<Integer> quantities;

    public CombinedData(List<Product> products, List<Integer> quantities) {
        this.products = products;
        this.quantities = quantities;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<Integer> quantities) {
        this.quantities = quantities;
    }
}
