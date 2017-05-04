package models;

/**
 * Created by Adam Blasko on 04.05.2017.
 */
public class ProductWithQuantity {
    private Product product;
    private int quantity;

    public ProductWithQuantity(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
