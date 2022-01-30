package openLegacy.shop.CLR;


import openLegacy.shop.beans.Product;
import openLegacy.shop.repositories.ProductRepo;
import openLegacy.shop.services.AdminService;
import openLegacy.shop.services.CustomerService;
import openLegacy.shop.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)

public class Tester implements CommandLineRunner {
    @Autowired
    private AdminService adminService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private ProductRepo productRepo;

    @Override
    public void run(String... args) throws Exception {
        Product product1 = Product.builder()
                .id(1)
                .amount(20)
                .image("Minced Turkey.jpg")
                .price(30)
                .name("Minced Turkey")
                .Department("Poultry")
                .company("Zoglobek")
                .description("1 kg")
                .build();
        productRepo.save(product1);
        System.out.println(productRepo.findById(1).toString());
        customerService.withDrawProduct(1, 5);
        System.out.println(productRepo.findById(1).toString());
        supplierService.addProduct(1, 10);
        System.out.println(productRepo.findById(1).toString());
        product1 = productRepo.findById(1);
        product1.setName("milk1");
        adminService.updateProduct(product1);
        System.out.println(productRepo.findById(1).toString());
        Product product2 = Product.builder()
                .id(2)
                .amount(50)
                .image("bamba.jpg")
                .price(100)
                .name("bamba")
                .company("osem")
                .Department("snacks")
                .description("80 grams")
                .build();
        System.out.println(product2);
        productRepo.save(product2);

    }
}
