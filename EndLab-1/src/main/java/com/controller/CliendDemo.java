package com.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.model.Customer;
import com.model.CustomerServices;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CliendDemo {

	

	    @Autowired
	    private CustomerServices customerService;

	    @GetMapping("/location/{location}")
	    public List<Customer> getCustomersByLocation(@PathVariable String location) {
	        return customerService.getCustomersByLocation(location);
	    }

	    @GetMapping("/age")
	    public List<Customer> getCustomersByAgeRange(@RequestParam int minAge, @RequestParam int maxAge) {
	        return customerService.getCustomersByAgeRange(minAge, maxAge);
	    }

	    @GetMapping("/email")
	    public List<Customer> getCustomersWithEmailContaining(@RequestParam String emailPattern) {
	        return customerService.getCustomersWithEmailContaining(emailPattern);
	    }
	}


