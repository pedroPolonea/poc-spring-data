package com.psd.filter.resources;


import com.psd.filter.map.vo.ProductVO;
import com.psd.filter.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import javax.validation.Valid;

@Api(
        tags = "products",
        value="/v1"
)
@RestController
@RequestMapping(value = "products", produces = MediaType.APPLICATION_JSON_VALUE, headers ="version=V1" )
public class ProductResources {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "example")
    public ResponseEntity<?> findProductsExample(@RequestBody ProductVO productVO){
        return new ResponseEntity<>(productService.findProductsExample(productVO), HttpStatus.OK);
    }

    @GetMapping(value = "specification")
    public ResponseEntity<?> findProductsSpecification(@RequestBody ProductVO productVO){
        return new ResponseEntity<>(productService.findProductsSpecification(productVO), HttpStatus.OK);
    }

    @GetMapping(value = "specification-class")
    public ResponseEntity<?> findProductsSpecificationClass(@RequestBody ProductVO productVO){
        return new ResponseEntity<>(productService.findProductsSpecificationClass(productVO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllProduts(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "active")
    public ResponseEntity<?> getAllActiveProduts(){
        return new ResponseEntity<>(productService.findAllActive(), HttpStatus.OK);
    }

    @GetMapping(value = "active-query")
    public ResponseEntity<?> getAllActiveQueryProduts(){
        return new ResponseEntity<>(productService.findAllActive(), HttpStatus.OK);
    }

    @GetMapping(value = "active-query-native")
    public ResponseEntity<?> getAllActiveQueryNativeProduts(){
        return new ResponseEntity<>(productService.findAllActive(), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get all products.",
            response = ProductVO.class)
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ProductVO productVO){
        return new ResponseEntity<>(productService.save(productVO), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get all products.",
            response = ProductVO.class)
    @PutMapping
    public  ResponseEntity<?> update(@Valid @RequestBody ProductVO productVO) throws Exception {
        return new ResponseEntity<>(productService.update(Optional.ofNullable(productVO)), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public  ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws NotFoundException {
        productService.delete(Optional.ofNullable(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public  ResponseEntity<?> getById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(productService.getId(id), HttpStatus.OK);
    }
}
