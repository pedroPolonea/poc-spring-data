package com.psd.filter.service.impl;

import com.psd.filter.entity.ProductEntity;
import com.psd.filter.map.mapping.ProductMapping;
import com.psd.filter.map.vo.ProductVO;
import com.psd.filter.repository.ProductRepository;
import com.psd.filter.service.ProductService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapping productMapping;

    @Override
    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<ProductEntity> getId(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<ProductEntity> findAllActive() {
        return productRepository.findByActive(true);
    }

    @Override
    public ProductVO save(final ProductVO productVO) {
        log.info("M=ProductServiceImpl.save, productVO={}", productVO);

        ProductEntity productEntity = productMapping.productVOToProductEntity(productVO);
        productEntity = productRepository.save(productEntity);

        log.info("M=ProductServiceImpl.save, productEntity={}", productEntity);
        return productMapping.productEntityToProductVO(productEntity);
    }

    @Override
    public ProductVO update(final Optional<ProductVO> productVO) throws Exception {
        validateUpdate(productVO);

        log.info("M=ProductServiceImpl.update, productVO={}", productVO.get());
        ProductEntity productEntity = productMapping.productVOToProductEntity(productVO.get());
        productEntity = productRepository.save(productEntity);
        log.info("M=ProductServiceImpl.update, productEntity={}", productEntity);

        return productMapping.productEntityToProductVO(productEntity);
    }

    private void validateUpdate(final Optional<ProductVO> productVO) throws Exception {
        if (!productVO.map(vo -> vo.getId()).isPresent()) {
            log.error("M=ProductServiceImpl.validateUpdate, I=O productVO se encontra null ou sem id definido.");
            throw new Exception("Problema na criação do Statement");
        }
    }

    public void delete(final Optional<Long> id) throws NotFoundException {

        final Optional<ProductEntity> productEntity = id.map(idProduct -> productRepository.findById(idProduct))
                .orElseThrow(() -> new NotFoundException("Informe um produto valido."));

        if(productEntity.isPresent()){
            productRepository.delete(productEntity.get());
        } else {
            log.info("M=ProductServiceImpl.delete, I=Recurso não encontrado.");
            throw new NotFoundException("Recurso não encontrado.");
        }
    }
}
