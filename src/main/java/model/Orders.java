package main.java.model;

public class Orders {

    private int id;
    private int idClient;
    private int idProduct;
    private int quantity;
    private float totalPrice;

    public Orders(int id, int idClient, int idProduct, int quantity, float totalPrice){
        this.id = id;
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Orders(int idClient, int idProduct, int quantity, float totalPrice){
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public int getIdClient() {
        return idClient;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

}
