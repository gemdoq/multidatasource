package com.example.multidatasource.domain.product.controller;

import com.example.multidatasource.domain.product.model.dto.ProductCreateRequest;
import com.example.multidatasource.domain.product.model.dto.ProductResponse;
import com.example.multidatasource.domain.product.model.dto.ProductUpdateRequest;
import com.example.multidatasource.domain.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping
	public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductCreateRequest req) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(req.getName(), req.getPrice(), req.getAmount()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
		return ResponseEntity.ok(productService.getProduct(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateRequest req) {
		return ResponseEntity.ok(productService.updateProduct(id, req.getName(), req.getPrice(), req.getAmount()));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
}
