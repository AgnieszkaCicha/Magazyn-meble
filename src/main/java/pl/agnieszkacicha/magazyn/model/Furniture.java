package pl.agnieszkacicha.magazyn.model;

public class Furniture extends Product {

    public Furniture(int code, String name, int pieces, double price) {
        super(code, name, pieces, price);
    }

    public Furniture() {
    }

    @Override
    public String toString() {
        return "Furniture{" +
                super.toString() + "}";
    }
}
