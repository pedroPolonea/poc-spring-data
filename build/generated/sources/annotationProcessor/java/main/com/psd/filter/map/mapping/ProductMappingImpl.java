package com.psd.filter.map.mapping;

import com.psd.filter.entity.ProductEntity;
import com.psd.filter.map.vo.ProductSummaryVO;
import com.psd.filter.map.vo.ProductVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-08T22:23:24-0300",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.6.1.jar, environment: Java 15-ea (Azul Systems, Inc.)"
)
@Component
public class ProductMappingImpl implements ProductMapping {

    @Override
    public ProductVO productEntityToProductVO(ProductEntity product) {
        if ( product == null ) {
            return null;
        }

        ProductVO productVO = new ProductVO();

        productVO.setId( product.getId() );
        productVO.setName( product.getName() );
        productVO.setDescription( product.getDescription() );
        productVO.setAmount( product.getAmount() );
        productVO.setUnitaryValue( product.getUnitaryValue() );
        productVO.setAcquisitionValue( product.getAcquisitionValue() );
        productVO.setDateAcquisition( product.getDateAcquisition() );
        productVO.setDateCreation( product.getDateCreation() );
        productVO.setActive( product.getActive() );
        productVO.setProductType( product.getProductType() );

        return productVO;
    }

    @Override
    public ProductEntity productVOToProductEntity(ProductVO vo) {
        if ( vo == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setId( vo.getId() );
        productEntity.setName( vo.getName() );
        productEntity.setDescription( vo.getDescription() );
        productEntity.setAmount( vo.getAmount() );
        productEntity.setUnitaryValue( vo.getUnitaryValue() );
        productEntity.setAcquisitionValue( vo.getAcquisitionValue() );
        productEntity.setDateAcquisition( vo.getDateAcquisition() );
        productEntity.setDateCreation( vo.getDateCreation() );
        productEntity.setActive( vo.getActive() );
        productEntity.setProductType( vo.getProductType() );

        return productEntity;
    }

    @Override
    public List<ProductSummaryVO> productEntityToProductSummaryVO(List<ProductEntity> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductSummaryVO> list = new ArrayList<ProductSummaryVO>( products.size() );
        for ( ProductEntity productEntity : products ) {
            list.add( productEntityToProductSummaryVO1( productEntity ) );
        }

        return list;
    }

    protected ProductSummaryVO productEntityToProductSummaryVO1(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }

        ProductSummaryVO productSummaryVO = new ProductSummaryVO();

        productSummaryVO.setId( productEntity.getId() );
        productSummaryVO.setName( productEntity.getName() );
        productSummaryVO.setAmount( productEntity.getAmount() );
        productSummaryVO.setUnitaryValue( productEntity.getUnitaryValue() );
        productSummaryVO.setProductType( productEntity.getProductType() );

        return productSummaryVO;
    }
}
