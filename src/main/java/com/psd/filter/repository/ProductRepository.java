package com.psd.filter.repository;

import com.psd.filter.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> , JpaSpecificationExecutor<ProductEntity> {

    List<ProductEntity> findByActive(Boolean active);
}
