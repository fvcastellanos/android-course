package net.cavitos.android.product.app.domain;

public class Product {

    private int id;
    private final String name;
    private final double price;
    private final double quantity;

    public Product(final String name, final double price, final double quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(final int id, final String name, final double price, final double quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getQuantity() {
        return quantity;
    }
}
