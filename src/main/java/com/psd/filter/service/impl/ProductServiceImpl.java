package com.psd.filter.service.impl;

import com.psd.filter.entity.ProductEntity;
import com.psd.filter.map.mapping.ProductMapping;
import com.psd.filter.map.vo.ProductVO;
import com.psd.filter.repository.ProductRepository;
import com.psd.filter.repository.spec.ProductSpecification;
import com.psd.filter.service.ProductService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;

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
    public List<ProductEntity> findAllActiveQueryNative() {
        return productRepository.findByActiveQueryNative();
    }

    @Override
    public List<ProductEntity> findAllActiveQuery() {
        return productRepository.findByActiveQuery();
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

    @Override
    public List<ProductEntity> findProductsSpecificationClass(ProductVO productVO) {
        return productRepository.findAll(where(ProductSpecification.getProductSpecification(productVO)));
    }

    public List<ProductEntity> findProductsExample(final ProductVO productVO){
        log.info("M=findProducts, productVO={}", productVO);

        ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("amount", ExampleMatcher.GenericPropertyMatchers.exact());

        Example<ProductEntity> example = Example.of(ProductEntity.builder()
                .name(productVO.getName())
                .description(productVO.getDescription())
                .amount(productVO.getAmount())
                .build(), customExampleMatcher);

        return productRepository.findAll(example);
    }

    public List<ProductEntity> findProductsSpecification(final ProductVO productVO){
        log.info("M=findProducts, productVO={}", productVO);

        return productRepository.findAll(where(getEntityFieldsSpec(productVO)));
    }

    private Specification<ProductEntity> getEntityFieldsSpec(final ProductVO productVO) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!StringUtils.isEmpty(productVO.getName())) {
                Predicate tenantIdPredicate = criteriaBuilder.equal(root.get("name"), productVO.getName());
                predicates.add(tenantIdPredicate);
            }

            if (Objects.nonNull(productVO.getActive())) {
                Predicate tenantIdPredicate = criteriaBuilder.equal(root.get("active"), productVO.getActive());
                predicates.add(tenantIdPredicate);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        };
    }

}
