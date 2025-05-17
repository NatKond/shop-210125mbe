package de.telran.shop210125mbe;

import de.telran.shop210125mbe.pojo.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Shop210125MBeApplication {

    public static void main(String[] args) {
        //SpringApplication.run(Shop210125MBeApplication.class, args);

        ApplicationContext applicationContext = SpringApplication.run(Shop210125MBeApplication.class, args);
        Product product = (Product) applicationContext.getBean(Product.class);

        // Product product = new Product();
        product.setProductId(1L);
        product.setName("Carrot");
        System.out.println(product);

        // поиск по имени bean
        Product productName = (Product) applicationContext.getBean("product");
        productName.setProductId(2L);
        product.setName("Broccoli");
        System.out.println(productName);
    }

}
