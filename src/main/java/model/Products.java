package main.java.model;

public class Products {

    private int id;
    private String name;
    private int stock;
    private float price;

    //default constructor
    public Products(){};

    public Products(int id, String name, int stock, float price){
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public Products(String name, int stock, float price){
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public float getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "name=" + name + "\n" +
                "price=" + price + "\n";
    }
}
