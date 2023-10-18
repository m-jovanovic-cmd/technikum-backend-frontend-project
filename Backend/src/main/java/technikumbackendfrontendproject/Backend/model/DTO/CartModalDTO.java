package technikumbackendfrontendproject.Backend.model.DTO;

import java.util.List;

public class CartModalDTO {
    List<CartProductDTO> productList;
    double total;

    public CartModalDTO(List<CartProductDTO> productList, double total) {
        this.productList = productList;
        this.total = total;
    }

    public List<CartProductDTO> getProductList() {
        return productList;
    }

    public void setProductList(List<CartProductDTO> productList) {
        this.productList = productList;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
