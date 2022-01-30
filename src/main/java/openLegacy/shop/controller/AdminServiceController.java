package openLegacy.shop.controller;

import openLegacy.shop.Exceptions.AlreadyExistsException;
import openLegacy.shop.Exceptions.IllegalRequestException;
import openLegacy.shop.Exceptions.NotFoundException;
import openLegacy.shop.beans.Product;
import openLegacy.shop.repositories.ProductRepo;
import openLegacy.shop.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")  //http://localhost:8080/
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminServiceController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private ProductRepo productRepo;

    @PostMapping("update")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) throws NotFoundException, IllegalRequestException {
        try {
            adminService.updateProduct(product);
        } catch (NotFoundException err) {
            throw new NotFoundException(err.getMessage());
        } catch (IllegalRequestException err) {
            throw new IllegalRequestException(err.getMessage());
        }
        System.out.println(productRepo.findById(product.getId()));
        return ResponseEntity.accepted()
                .body("product successfully updated");
    }

    @PostMapping("addNew")
    public ResponseEntity<?> addNewProduct(@RequestBody Product product) throws AlreadyExistsException, IllegalRequestException {
        try {
            adminService.addNewProduct(product);
        }catch (AlreadyExistsException err) {
            throw new AlreadyExistsException(err.getMessage());
        } catch (IllegalRequestException err) {
            throw new IllegalRequestException(err.getMessage());
        }
        System.out.println(product.toString());
        return ResponseEntity.accepted()
                .body("product successfully added");
    }
}
