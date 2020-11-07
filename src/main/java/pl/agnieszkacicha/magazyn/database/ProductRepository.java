package pl.agnieszkacicha.magazyn.database;

import org.springframework.stereotype.Component;
import pl.agnieszkacicha.magazyn.model.AGD;
import pl.agnieszkacicha.magazyn.model.Furniture;
import pl.agnieszkacicha.magazyn.model.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {

    private static final ProductRepository productRepository = new ProductRepository();
    private List<Product> products = new ArrayList();

    public ProductRepository() {
        this.products.add(new Furniture(101,"Fotel",1,400.0));
        this.products.add(new Furniture(102,"Szafka",2,80.0));
        this.products.add(new AGD(201,"Płyta indukcyjna",1,1200.0,4.2 ));
        this.products.add(new AGD(202,"Zmywarka",1,1150.0,1.5));
    }

    public static ProductRepository getInstance() { return productRepository; }

    public List<Product> getAllProducts() { return this.products; }

    public boolean deliverProduct(int code, int pieces) {
        for (Product currentProduct : this.products)  {
            if(currentProduct.getCode() == code && currentProduct.getPieces() >= pieces) {
                currentProduct.setPieces(currentProduct.getPieces() - pieces);
                return true;
            }
        }
        return false;
    }

    public Product findProduct (int code) {
        for (Product currentProduct : this.products) {
            if (currentProduct.getCode() == code) {
                return currentProduct;
            }
        }
        return null;
    }

    public void addProductToDatabase(Product product) { this.products.add(product); }

}
