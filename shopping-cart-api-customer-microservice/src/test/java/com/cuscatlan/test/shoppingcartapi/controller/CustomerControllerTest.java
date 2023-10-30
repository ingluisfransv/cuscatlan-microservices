package com.cuscatlan.test.shoppingcartapi.controller;

import com.cuscatlan.test.shoppingcartapi.model.Customer;
import com.cuscatlan.test.shoppingcartapi.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private CustomerController customerController;
    @Mock
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCustomer() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/api/auth/token").with(httpBasic("sa", "password"))).andReturn();
        String jwt = result.getResponse().getContentAsString();
        assertThat(jwt).isNotEmpty();

        Customer newCustomer = new Customer();
        newCustomer.setId(1L);
        newCustomer.setName("Luis");
        newCustomer.setSurname("Gonzalez");
        newCustomer.setAge(30);
        newCustomer.setPhone("123-456-7890");
        newCustomer.setAddress("123 Main St");
        newCustomer.setDui("12345678-9");

        when(customerService.createCustomer(any(Customer.class))).thenReturn(newCustomer);

        mockMvc.perform(post("/customers/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                        .content("{\n" +
                                "  \"id\": \"1\",\n" +
                                "  \"name\": \"Luis\",\n" +
                                "  \"surname\": \"Gonzalez\",\n" +
                                "  \"age\": 30,\n" +
                                "  \"phone\": \"123-456-7890\",\n" +
                                "  \"address\": \"123 Main St\",\n" +
                                "  \"dui\": \"12345678-9\"\n" +
                                "}\n")
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L)) // Verifica el ID generado
                .andExpect(jsonPath("$.name").value("Luis"));
    }


    @Test
    void testUpdateCustomer() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/api/auth/token").with(httpBasic("sa", "password"))).andReturn();
        String jwt = result.getResponse().getContentAsString();
        assertThat(jwt).isNotEmpty();
        Customer updatedCustomer = new Customer();
        updatedCustomer.setId(1L);
        updatedCustomer.setName("Updated Name");

        when(customerService.updateCustomer(eq(1L), any(Customer.class))).thenReturn(updatedCustomer);

        mockMvc.perform(put("/customers/1/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                        .content("{\"name\":\"Updated Name\"}")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    void testDeleteCustomer() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/api/auth/token").with(httpBasic("sa", "password"))).andReturn();
        String jwt = result.getResponse().getContentAsString();
        assertThat(jwt).isNotEmpty();
        Customer deletedCustomer = new Customer();
        deletedCustomer.setId(1L);

        when(customerService.deleteCustomer(eq(1L))).thenReturn(deletedCustomer);

        mockMvc.perform(delete("/customers/1/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt))
                .andExpect(status().isNoContent());
    }
}
