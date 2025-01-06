package com.example.multidatasource.domain.provider.repository;

import com.example.multidatasource.domain.provider.model.entity.ProviderEntity;
import com.example.multidatasource.global.annotation.Db2Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Db2Repository
public interface ProviderRepository extends JpaRepository<ProviderEntity, Long> {
}
