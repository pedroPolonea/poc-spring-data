package com.psd.filter.map.mapping;


import com.psd.filter.entity.ProductEntity;
import com.psd.filter.map.vo.ProductSummaryVO;
import com.psd.filter.map.vo.ProductVO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapping {

    ProductVO productEntityToProductVO(ProductEntity product);

    ProductEntity productVOToProductEntity(ProductVO vo);

    List<ProductSummaryVO> productEntityToProductSummaryVO(List<ProductEntity> products);

}
