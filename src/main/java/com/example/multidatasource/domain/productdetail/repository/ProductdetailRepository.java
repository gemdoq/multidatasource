package com.example.multidatasource.domain.productdetail.repository;

import com.example.multidatasource.domain.productdetail.model.entity.ProductdetailEntity;
import com.example.multidatasource.global.annotation.Db2Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Db2Repository
public interface ProductdetailRepository extends JpaRepository<ProductdetailEntity, Long> {
	Optional<ProductdetailEntity> findByProductId(Long productId);
}
