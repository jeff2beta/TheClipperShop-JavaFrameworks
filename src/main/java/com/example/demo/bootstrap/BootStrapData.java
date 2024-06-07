package com.example.demo.bootstrap;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {
       //  Check if there is existing data
        if (outsourcedPartRepository.count() == 0 && productRepository.count() == 0) {
            addSampleInventory();
        } else {
            System.out.println("Inventory already exists.");
        }
    }
    private void addSampleInventory() {
        addPartIfNotExist("Blades", "Sample Company", 25, 45.0, 1, 200);
        addPartIfNotExist("Batteries", "Sample Company", 20, 40.0, 1, 125);
        addPartIfNotExist("Motors", "Sample Company", 15, 55.0, 1, 50);
        addPartIfNotExist("Levers", "Sample Company", 10, 25.0, 1, 150);
        addPartIfNotExist("Covers", "Sample Company", 30, 35.0, 1, 200);

        productRepository.saveAll(List.of(
                new Product("Cordless Clippers", 200.0, 30),
                new Product("Non-Cordless Clippers", 120.0, 15),
                new Product("Blending Clippers", 250.0, 20),
                new Product("Balding Clippers", 100.0, 12),
                new Product("Bulk Removal Clippers", 300.0, 10)
        ));


        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products " + productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts " + outsourcedPartRepository.count());
        System.out.println(outsourcedPartRepository.findAll());
    }
    private void addPartIfNotExist(String name, String companyName, int inv, double price, int minInv, int maxInv) {
        Optional<OutsourcedPart> existingPart = outsourcedPartRepository.findByName(name);
        OutsourcedPart part = existingPart.orElseGet(OutsourcedPart::new);
        if (existingPart.isPresent()) {
            part.setName("Mulit-pack "+ name);
        } else {
            part.setName(name);
        }
        part.setCompanyName(companyName);
        part.setInv(inv);
        part.setPrice(price);
        part.setMinInv(minInv);
        part.setMaxInv(maxInv);
        outsourcedPartRepository.save(part);
    }

}

