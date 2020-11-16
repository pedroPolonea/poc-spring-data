package com.psd.filter.repository.spec;

import com.psd.filter.entity.ProductEntity;
import com.psd.filter.map.dto.ProductDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductSpecification {

    public static Specification<ProductEntity> getProductSpecification(final ProductDTO productDTO){
        return new Specification<ProductEntity>() {
            @Override
            public Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (!StringUtils.isEmpty(productDTO.getName())) {
                    Predicate tenantIdPredicate = criteriaBuilder.equal(root.get("name"), productDTO.getName());
                    predicates.add(tenantIdPredicate);
                }

                if (Objects.nonNull(productDTO.getActive())) {
                    Predicate tenantIdPredicate = criteriaBuilder.equal(root.get("active"), productDTO.getActive());
                    predicates.add(tenantIdPredicate);
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
            }
        };
    }
}
