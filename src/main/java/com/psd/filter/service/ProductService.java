package com.psd.filter.service;

import com.psd.filter.entity.ProductEntity;
import com.psd.filter.map.dto.ProductDTO;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductEntity> getAll();

    List<ProductEntity> findProductsExample(final ProductDTO productDTO);

    List<ProductEntity> findProductsSpecification(final ProductDTO productDTO);

    Optional<ProductEntity> getId(Long id);

    List<ProductEntity> findAllActive();

    List<ProductEntity> findAllActiveQueryNative();

    List<ProductEntity> findAllActiveQuery();

    ProductDTO save(ProductDTO productVO);

    ProductDTO update(Optional<ProductDTO> productVO) throws Exception;

    void delete(final Optional<Long> id) throws NotFoundException;

    List<ProductEntity> findProductsSpecificationClass(final ProductDTO productVO);
}
