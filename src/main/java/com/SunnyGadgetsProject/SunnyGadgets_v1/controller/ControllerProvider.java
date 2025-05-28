package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Provider;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return serviceProvider.getProviderById(id);
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Provider>> getAllProviders() {
        return serviceProvider.allProviders();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Provider> createProvider(@RequestBody Provider provider) {
        return serviceProvider.createProvider(provider);
    }

    @PostMapping("/createBatch")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<Provider>> createProviders(@RequestBody List<Provider> providers) {
        return serviceProvider.createProvider(providers);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Provider> deleteProvider(@PathVariable Long id) {
        return serviceProvider.deleteProvider(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Provider> updateProvider(@RequestBody Provider provider, @PathVariable Long id) {
        return serviceProvider.updateProvider(provider, id);
    }
}
