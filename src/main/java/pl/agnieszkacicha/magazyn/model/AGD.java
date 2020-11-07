package pl.agnieszkacicha.magazyn.model;

public class AGD extends Product {

    private Double power;

    public AGD(int code, String name, int pieces, double price, Double power) {
        super(code, name, pieces, price);
        this.power = power;
    }

    public AGD(Double power) {
        this.power = power;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "AGD{" +
                super.toString() +
                ", power=" + power +
                '}';
    }
}
