package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Customer;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Customer> createCustomer(Customer customer) {
        if (customer == null || customer.getEmail() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        repositoryCustomer.save(customer);
        logger.info("Customer creado: {}", customer.getEmail());
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Customer>> createCustomer(List<Customer> customers) {
        if (customers == null || customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        repositoryCustomer.saveAll(customers);
        logger.info("Se crearon {} customers", customers.size());
        return new ResponseEntity<>(customers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Customer> getCustomerById(Long id) {
        Optional<Customer> customer = repositoryCustomer.findById(id);
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<List<Customer>> allCustomers() {
        List<Customer> customers = repositoryCustomer.findAll();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Customer> updateCustomer(Customer customer, Long id) {
        Optional<Customer> existingCustomer = repositoryCustomer.findById(id);
        if (existingCustomer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Actualizar solo campos permitidos (evitar sobrescribir campos sensibles como 'password')
        Customer customerToUpdate = existingCustomer.get();
        customerToUpdate.setName(customer.getName());
        customerToUpdate.setEmail(customer.getEmail());
        customerToUpdate.setAddress(customer.getAddress());

        repositoryCustomer.save(customerToUpdate);
        logger.info("Customer actualizado: {}", customerToUpdate.getEmail());
        return new ResponseEntity<>(customerToUpdate, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Customer> deleteCustomer(Long id) {
        Optional<Customer> customer = repositoryCustomer.findById(id);
        if (customer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repositoryCustomer.deleteById(id);
        logger.info("Customer eliminado: {}", customer.get().getEmail());
        return new ResponseEntity<>(customer.get(), HttpStatus.OK);
    }
}