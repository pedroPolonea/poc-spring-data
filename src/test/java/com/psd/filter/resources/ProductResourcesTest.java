package com.psd.filter.resources;

import com.psd.filter.config.ResetDatabaseTestExecutionListener;
import com.psd.filter.entity.ProductEntity;
import com.psd.filter.factory.ProductFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;

@TestExecutionListeners(mergeMode =
        TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS,
        listeners = {ResetDatabaseTestExecutionListener.class}
)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductResourcesTest {

    @Autowired
    private ProductFactory productFactory;

    @Test
    void contextLoads() {
        ProductEntity product = productFactory.createProductBase();

    }


}