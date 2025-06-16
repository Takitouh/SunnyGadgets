package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Provider;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceProvider;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@EnableMethodSecurity(prePostEnabled = true)
@PreAuthorize("denyAll()")
@RequestMapping("/api/v1/provider")
public class ControllerProvider {
    private final IServiceProvider serviceProvider;

    public ControllerProvider(IServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Provider> getProvider(@PathVariable Long id) {
        Optional<Provider> providerOptional = serviceProvider.getProviderById(id);
        if (providerOptional.isEmpty()){
            throw new EntityNotFoundException("Provider with ID " + id + " not found");
        }
        return ResponseEntity.ok(providerOptional.get());
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Provider>> getAllProviders() {
        List<Provider> providers = serviceProvider.allProviders();
        if (providers.isEmpty()){
            throw new EntityNotFoundException("Provider list is empty");
        }
        return ResponseEntity.ok(providers);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Provider> createProvider(@RequestBody Provider provider) {
        serviceProvider.createProvider(provider);

        return new ResponseEntity<>(provider, HttpStatus.CREATED);
    }

    @PostMapping("/createBatch")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<Provider>> createProviders(@RequestBody List<Provider> providers) {
        serviceProvider.createProvider(providers);

        return new ResponseEntity<>(providers, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Provider> deleteProvider(@PathVariable Long id) {
        serviceProvider.deleteProvider(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Provider> updateProvider(@RequestBody Provider provider, @PathVariable Long id) {
        serviceProvider.updateProvider(provider, id);
        return ResponseEntity.ok(provider);
    }
}
