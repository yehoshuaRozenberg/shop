package openLegacy.shop.services;

import openLegacy.shop.Exceptions.IllegalRequestException;
import openLegacy.shop.Exceptions.NotFoundException;
import openLegacy.shop.beans.Product;
import openLegacy.shop.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {
    @Autowired
    private ProductRepo productRepo;

    public void addProduct(int productId, int amount) throws NotFoundException, IllegalRequestException {
        Product product;
        try {
            product = productRepo.findById(productId);
            if (amount<= 0){
                throw new IllegalRequestException("wrong details. you can add only a Positive number");
            }
            if (2147483647-amount<product.getAmount()){
                throw new IllegalRequestException("can't add so much");
            }
            product.setAmount(product.getAmount() + amount);
        } catch (NullPointerException err) {
            throw new NotFoundException("wrong details. product not found");
        }
        productRepo.saveAndFlush(product);
        System.out.println("Adding goods completed");
    }
}
