package openLegacy.shop.services;

import openLegacy.shop.Exceptions.IllegalRequestException;
import openLegacy.shop.Exceptions.NotFoundException;
import openLegacy.shop.beans.Product;
import openLegacy.shop.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private ProductRepo productRepo;

    public void withDrawProduct(int productID, int amount) throws IllegalRequestException, NotFoundException {
        Product product;
        try {
            product = productRepo.findById(productID);
            if (amount <= 0) {
                throw new IllegalRequestException("wrong details. you can purchase only a Positive number");
            }
            if (product.getAmount() <= 0) {
                throw new IllegalRequestException("amount is zero, can't complete purchase");
            }
            if (product.getAmount() - amount < 0) {
                throw new IllegalRequestException("amount is only " + product.getAmount() + "can't complete purchase because can't buy more from this");
            }
        } catch (NullPointerException err) {
            throw new NotFoundException("wrong details. product not found");
        }
        product.setAmount(product.getAmount() - amount);
        productRepo.saveAndFlush(product);
        System.out.println("purchase completed");
    }
}
