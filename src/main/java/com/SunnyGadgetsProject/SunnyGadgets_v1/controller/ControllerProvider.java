package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.ProviderCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.ProviderResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Provider;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity()
@PreAuthorize("denyAll()")
@RequestMapping("/api/v1/provider")
public class ControllerProvider {
    private final IServiceProvider serviceProvider;


    public ControllerProvider(IServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<ProviderResponseDTO> getProvider(@PathVariable Long id) {
        return ResponseEntity.ok(serviceProvider.getProviderById(id));
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<ProviderResponseDTO>> getAllProviders() {
        return ResponseEntity.ok(serviceProvider.allProviders());
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<ProviderResponseDTO> createProvider(@RequestBody ProviderCreateDTO provider) {
        return new ResponseEntity<>(serviceProvider.createProvider(provider), HttpStatus.CREATED);
    }

    @PostMapping("/createBatch")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<ProviderResponseDTO>> createProviders(@RequestBody List<ProviderCreateDTO> providers) {
        return new ResponseEntity<>(serviceProvider.createProvider(providers), HttpStatus.CREATED);
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
