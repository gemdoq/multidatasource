package com.example.multidatasource.domain.provider.controller;

import com.example.multidatasource.domain.provider.model.dto.ProviderCreateRequest;
import com.example.multidatasource.domain.provider.model.dto.ProviderResponse;
import com.example.multidatasource.domain.provider.model.dto.ProviderUpdateRequest;
import com.example.multidatasource.domain.provider.service.ProviderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/provider")
public class ProviderController {

	private final ProviderService providerService;

	public ProviderController(ProviderService providerService) {
		this.providerService = providerService;
	}

	@PostMapping
	public ResponseEntity<ProviderResponse> createProvider(@RequestBody ProviderCreateRequest req) {
		return ResponseEntity.status(HttpStatus.CREATED).body(providerService.createProvider(req.getName(), req.getProductId()));
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<ProviderResponse> getProviderByProductId(@PathVariable Long productId) {
		return ResponseEntity.ok(providerService.getProviderByProductId(productId));
	}

	@PutMapping("/product/{productId}")
	public ResponseEntity<ProviderResponse> updateProvider(@PathVariable Long productId, @RequestBody ProviderUpdateRequest req) {
		return ResponseEntity.ok(providerService.updateProvider(productId, req.getName(), req.getProductId()));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProvider(@PathVariable Long id) {
		providerService.deleteProvider(id);
		return ResponseEntity.noContent().build();
	}
}
