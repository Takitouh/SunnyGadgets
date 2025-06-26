package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Customer;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryCustomer;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceCustomer implements IServiceCustomer {

    private static final Logger logger = LoggerFactory.getLogger(ServiceCustomer.class);
    private final IRepositoryCustomer repositoryCustomer;

    // Inyección de dependencias por constructor
    public ServiceCustomer(IRepositoryCustomer repositoryCustomer) {
        this.repositoryCustomer = repositoryCustomer;
    }

    @Override
    public Customer createCustomer(Customer customer) {

        repositoryCustomer.save(customer);
        logger.info("Customer created: {}", customer.getEmail());
        return customer;
    }

    @Override
    public List<Customer> createCustomer(List<Customer> customers) {
        repositoryCustomer.saveAll(customers);
        logger.info("Customer's created: {}", customers);
        return customers;
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return repositoryCustomer.findById(id);
    }

    @Override
    public List<Customer> allCustomers() {
        List<Customer> customers = repositoryCustomer.findAll();
        if (customers.isEmpty()) {
            return null; //Exception not found
        }
        return customers;
    }

    @Override
    public Customer updateCustomer(Customer customer, Long id) {
        Optional<Customer> optionalCustomer = repositoryCustomer.findById(id);
        if (optionalCustomer.isEmpty()) {
            throw new EntityNotFoundException("Customer with id " + id + " not found"); //Exception Not found
        }

        // Actualizar solo campos permitidos (evitar sobrescribir campos sensibles como 'password')
        Customer cu = optionalCustomer.get();
        cu.setName(customer.getName());
        cu.setEmail(customer.getEmail());
        cu.setAddress(customer.getAddress());
        cu.setPhone(customer.getPhone());
        cu.setAge(customer.getAge());


        repositoryCustomer.save(cu);
        logger.info("Customer updated: {}", cu.getName());
        return cu;
    }

    @Override
    public void deleteCustomer(Long id) {
        Optional<Customer> customer = repositoryCustomer.findById(id);
        if (customer.isEmpty()) {
            return; //Exception not found
        }
        repositoryCustomer.deleteById(id);
        logger.info("Customer deleted: {}", customer.get().getEmail());
    }
}