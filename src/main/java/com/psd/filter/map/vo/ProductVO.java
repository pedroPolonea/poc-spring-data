package com.psd.filter.map.vo;

import com.psd.filter.entity.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVO {
    private Long id;

    private String name;

    private String description;

    private Integer amount;

    private BigDecimal unitaryValue;

    private BigDecimal acquisitionValue;

    private LocalDateTime dateAcquisition;

    private LocalDateTime dateCreation;

    private Boolean active;

    private ProductType productType;
}
