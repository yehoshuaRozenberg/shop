package openLegacy.shop.controller;

import openLegacy.shop.Exceptions.IllegalRequestException;
import openLegacy.shop.Exceptions.NotFoundException;
import openLegacy.shop.repositories.ProductRepo;
import openLegacy.shop.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")

@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerServiceController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductRepo productRepo;

    @PostMapping("withDraw")
    public ResponseEntity<?> withDrawProduct(@RequestBody int productId, int amount) throws IllegalRequestException, NotFoundException {
        try {
            customerService.withDrawProduct(productId, amount);
            System.out.println(productRepo.findById(productId));
            return ResponseEntity.accepted()
                    .body("successfully purchased");
        } catch (NotFoundException error) {
            throw new NotFoundException(error.getMessage());
        } catch (IllegalRequestException err) {
            throw new IllegalRequestException(err.getMessage());
        }
    }
}