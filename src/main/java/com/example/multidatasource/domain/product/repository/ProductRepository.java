package com.example.multidatasource.domain.product.repository;

import com.example.multidatasource.domain.product.model.entity.ProductEntity;
import com.example.multidatasource.global.annotation.Db1Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Db1Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
