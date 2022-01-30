package openLegacy.shop.services;

import openLegacy.shop.Exceptions.IllegalRequestException;
import openLegacy.shop.Exceptions.NotFoundException;
import openLegacy.shop.beans.Product;
import openLegacy.shop.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private ProductRepo productRepo;

    public void updateProduct(Product product) throws NotFoundException, IllegalRequestException {
        if (!productRepo.existsById(product.getId())) {
            throw new IllegalRequestException("wrong details. can't update product that not exist");
        }
        if (product.getAmount() <= 0) {
            throw new IllegalRequestException("wrong details. you can update amount only a Positive number ");
        }
        if (product.getPrice() <= 0) {
            throw new IllegalRequestException("wrong details. you can update price only a Positive number ");
        }
        try {
            productRepo.saveAndFlush(product);
        } catch (NullPointerException err) {
            throw new NotFoundException("wrong details. product not found");
        }
        System.out.println("product successfully updated!");
    }
}
