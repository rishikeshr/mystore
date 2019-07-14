package com.shopping.mystore;

import com.shopping.mystore.domain.Product;
import com.shopping.mystore.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class MystoreApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(MystoreApplication.class);

    @Autowired
    private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(MystoreApplication.class, args);
	}

    @Override
    public void run(String... args) {

        log.info("StartApplication...");

        Product ipxr = new Product();
        ipxr.setName("iPhone XR");
        ipxr.setDescription("Blue Color iPhone XR 256 GB");
        ipxr.setPrice(new BigDecimal("500"));
        ipxr.setQuantity(10);

        log.info(" Saving --> " + ipxr);
        productRepository.save(ipxr);

        Product ipxs = new Product();
        ipxs.setName("iPhone Xs");
        ipxs.setDescription("Golden Color iPhone Xs 256 GB");
        ipxs.setPrice(new BigDecimal("600"));
        ipxs.setQuantity(10);

        productRepository.save(ipxs);


        Product ipxsm = new Product();
        ipxsm.setName("iPhone Xs Max");
        ipxsm.setDescription("Golden Color iPhone Xs Max 256 GB");
        ipxsm.setPrice(new BigDecimal("800"));
        ipxsm.setQuantity(10);

        productRepository.save(ipxsm);

        Product ipadp = new Product();
        ipadp.setName("iPad Pro 12");
        ipadp.setDescription("iPad Pro \" with Cellular.");
        ipadp.setPrice(new BigDecimal("1500"));
        ipadp.setQuantity(10);

        productRepository.save(ipadp);


    }

}
