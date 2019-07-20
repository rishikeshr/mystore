package com.shopping.mystore;

import com.shopping.mystore.domain.Customer;
import com.shopping.mystore.domain.Product;
import com.shopping.mystore.repository.CustomerRepository;
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

    @Autowired
    private CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(MystoreApplication.class, args);
	}

    @Override
    public void run(String... args) {

        log.info("StartApplication...");

        Product ipxr = new Product();
        ipxr.setName("iPhone XR");
        ipxr.setProductcode("ixr");
        ipxr.setDescription("Blue Color iPhone XR 256 GB");
        ipxr.setPrice(new BigDecimal("500"));
        ipxr.setQuantity(10);

        log.info(" Saving --> " + ipxr);
        productRepository.save(ipxr);

        Product ipxs = new Product();
        ipxs.setName("iPhone Xs");
        ipxs.setProductcode("ixs");
        ipxs.setDescription("Golden Color iPhone Xs 256 GB");
        ipxs.setPrice(new BigDecimal("600"));
        ipxs.setQuantity(10);

        productRepository.save(ipxs);


        Product ipxsm = new Product();
        ipxsm.setName("iPhone Xs Max");
        ipxsm.setProductcode("ixsm");
        ipxsm.setDescription("Golden Color iPhone Xs Max 256 GB");
        ipxsm.setPrice(new BigDecimal("800"));
        ipxsm.setQuantity(10);

        productRepository.save(ipxsm);

        Product ipadp = new Product();
        ipadp.setName("iPad Pro 12");
        ipadp.setProductcode("ipad");
        ipadp.setDescription("iPad Pro \" with Cellular.");
        ipadp.setPrice(new BigDecimal("1500"));
        ipadp.setQuantity(10);

        productRepository.save(ipadp);

        Customer customer1 = new Customer();
        customer1.setFirstName("scott");
        customer1.setLastName("tiger");
        customer1.setAddressLine1(" Address 1 String ");
        customer1.setAddressLine2(" Address 2 String ");
        customer1.setCity(" Customer City ");
        customer1.setEmail("scott.tiger@shopper.com");
        customer1.setPhone("991-991-9999");
        customer1.setCountry(" Customer Country ");

        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("john");
        customer2.setLastName("snow");
        customer2.setAddressLine1(" North Of The Wall ");
        customer2.setAddressLine2(" North Of The Wall ");
        customer2.setCity(" Winterfell ");
        customer2.setEmail("john.snow@got.com");
        customer2.setPhone("991-991-9999");
        customer2.setCountry(" Seven Kingdoms ");

        customerRepository.save(customer2);
    }

}
