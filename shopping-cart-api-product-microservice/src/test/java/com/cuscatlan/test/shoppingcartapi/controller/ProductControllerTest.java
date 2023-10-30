package com.cuscatlan.test.shoppingcartapi.controller;
import com.cuscatlan.test.shoppingcartapi.model.Product;
import com.cuscatlan.test.shoppingcartapi.model.Rating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        Product[] expectedProducts = new Product[] {
                new Product(1, "Product 1", 10.0f, "Description 1", "Category 1", "Image 1", new Rating(1, 4.5f, 10)),
                new Product(2, "Product 2", 20.0f, "Description 2", "Category 2", "Image 2", new Rating(2, 4.0f, 5))
        };
        when(restTemplate.getForObject("https://fakestoreapi.com/products", Product[].class)).thenReturn(expectedProducts);
        ResponseEntity<Product[]> response = productController.getAllProducts();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    void testGetProductById() {
        int productId = 1;
        Product product = new Product(1, "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops", 109.95f, "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday", "men's clothing", "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg", new Rating(1, 3.9f, 120));
        when(restTemplate.getForObject("uri" + "/" + productId, Product.class)).thenReturn(product);
        ResponseEntity<Product> response = productController.getProductById(productId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
