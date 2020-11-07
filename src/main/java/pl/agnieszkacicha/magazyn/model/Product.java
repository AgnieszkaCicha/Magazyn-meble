package pl.agnieszkacicha.magazyn.model;

public class Product {

    private int code;
    private String name;
    private int pieces;
    private double price;

    public Product(int code, String name, int pieces, double price) {
        this.code = code;
        this.name = name;
        this.pieces = pieces;
        this.price = price;
    }

    public Product() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "kod: " + code +
                ", nazwa: " + name +
                ", ilość: " + pieces +
                ", cena: " + price;
    }
}
