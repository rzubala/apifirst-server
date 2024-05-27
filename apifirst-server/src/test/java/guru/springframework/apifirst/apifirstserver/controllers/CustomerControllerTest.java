package guru.springframework.apifirst.apifirstserver.controllers;

import guru.springframework.apifirst.model.Address;
import guru.springframework.apifirst.model.Customer;
import guru.springframework.apifirst.model.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by jt, Spring Framework Guru.
 */
@SpringBootTest
public class CustomerControllerTest extends BaseTest {

    @Test
    void testCreateNewCustomer() throws Exception {
        Customer newCustomer = Customer.builder()
                .name(Name.builder()
                        .firstName("New")
                        .lastName("Customer")
                        .build())
                .shipToAddress(
                        Address.builder()
                            .addressLine1("Test street 1")
                            .city("City")
                            .state("PL")
                            .zip("000000")
                            .build()
                )
                .billToAddress(
                        Address.builder()
                                .addressLine1("Test street 1")
                                .city("City")
                                .state("PL")
                                .zip("000000")
                                .build()
                )
                .build();

        mockMvc.perform(post(CustomerController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCustomer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @DisplayName("Get by Id")
    @Test
    void testGetCustomerById() throws Exception {
        mockMvc.perform(get(CustomerController.BASE_URL + "/{customerId}", testCustomer.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testCustomer.getId().toString()));
    }

    @DisplayName("Test List Customers")
    @Test
    void testListCustomers() throws Exception {
        mockMvc.perform(get(CustomerController.BASE_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)));
    }
}
