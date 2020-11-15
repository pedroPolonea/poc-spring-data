package com.psd.filter.service;

import com.psd.filter.entity.ProductEntity;
import com.psd.filter.map.vo.ProductVO;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductEntity> getAll();

    List<ProductEntity> findProductsExample(final ProductVO productVO);

    List<ProductEntity> findProductsSpecification(final ProductVO productVO);

    Optional<ProductEntity> getId(Long id);

    List<ProductEntity> findAllActive();

    ProductVO save(ProductVO productVO);

    ProductVO update(Optional<ProductVO> productVO) throws Exception;

    void delete(final Optional<Long> id) throws NotFoundException;
}
