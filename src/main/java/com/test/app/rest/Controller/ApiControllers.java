package com.test.app.rest.Controller;

import com.test.app.rest.Models.Customer;
import com.test.app.rest.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControllers {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping(value = "/create")
    public String createNewCustomer(@RequestBody Customer customer) {
        customerRepository.save(customer);
        return "New User Successfully Added to Database";
    }

    @GetMapping(value = "/read")
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping(value = "/readById/{id}")
    public Customer getCustomer(@PathVariable long id) {
        Customer readCustomer = customerRepository.findById(id).get();
        return readCustomer;
    }

    @PutMapping(value = "update/{id}")
    public String updateCustomer(@PathVariable long id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerRepository.findById(id).get();
        updatedCustomer.setFull_name(customer.getFull_name());
        updatedCustomer.setPhone(customer.getPhone());
        updatedCustomer.setUpdated(System.currentTimeMillis());
        customerRepository.save(updatedCustomer);

        return "You updated customer with ID : " + id;
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteCustomer(@PathVariable long id, @RequestBody Customer customer) {
        Customer deletedCustomer = customerRepository.findById(id).get();
        deletedCustomer.setIs_active(false);
        customerRepository.save(deletedCustomer);
        return "You deleted customer with ID : " + id;
    }
}
