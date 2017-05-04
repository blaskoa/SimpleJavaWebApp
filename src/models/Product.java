package models;

/**
 * Created by Adam Blasko on 03.05.2017.
 */
public class Product {
    private int identificator;
    private int price;
    private String name;

    public int getIdentificator() {
        return identificator;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return identificator == product.identificator;
    }

    @Override
    public int hashCode() {
        return identificator;
    }

    public Product(int identificator, int price, String name) {
        this.identificator = identificator;
        this.price = price;
        this.name = name;
    }
}
