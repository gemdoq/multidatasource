package com.example.multidatasource.domain.provider.service;

import com.example.multidatasource.domain.provider.model.dto.ProviderResponse;
import com.example.multidatasource.domain.provider.model.entity.ProviderEntity;
import com.example.multidatasource.domain.provider.repository.ProviderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProviderService {

	private final ProviderRepository providerRepository;

	public ProviderService(ProviderRepository providerRepository) {
		this.providerRepository = providerRepository;
	}

	public ProviderResponse createProvider(String name, Long productId) {
		ProviderEntity savedProviderEntity = providerRepository.save(ProviderEntity.create(name, productId));
		ProviderResponse res = ProviderResponse.toDto(savedProviderEntity);
		return res;
	}

	public ProviderResponse getProviderByProductId(Long id) {
		ProviderEntity foundProviderEntity = providerRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("해당 id의 납품업체를 찾을 수 없습니다: " + id));
		ProviderResponse res = ProviderResponse.toDto(foundProviderEntity);
		return res;
	}

	public ProviderResponse updateProvider(Long id, String name, Long productId) {
		ProviderEntity foundProviderEntity = providerRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("해당 id의 납품업체를 찾을 수 없습니다: " + id));
		foundProviderEntity.update(name, productId);
		ProviderEntity updatedProviderEntity = providerRepository.save(foundProviderEntity);
		ProviderResponse res = ProviderResponse.toDto(updatedProviderEntity);
		return res;
	}

	public void deleteProvider(Long id) {
		providerRepository.deleteById(id);
	}
}
