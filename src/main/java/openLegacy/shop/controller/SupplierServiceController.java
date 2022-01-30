package openLegacy.shop.controller;

import openLegacy.shop.Exceptions.IllegalRequestException;
import openLegacy.shop.Exceptions.NotFoundException;
import openLegacy.shop.repositories.ProductRepo;
import openLegacy.shop.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("supplier")  //http://localhost:8080/

@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SupplierServiceController {
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private ProductRepo productRepo;

    @PostMapping("add")
    public ResponseEntity<?> addProduct(@RequestBody int productId, int amount) throws IllegalRequestException, NotFoundException {
        try {
            supplierService.addProduct(productId, amount);
        }catch (NotFoundException err){
            throw new NotFoundException(err.getMessage());
        } catch (IllegalRequestException error ) {
        throw new IllegalRequestException(error.getMessage());
    }
        System.out.println(productRepo.findById(1));
        return ResponseEntity.accepted()
                .body("successfully Adding goods");
    }
}
