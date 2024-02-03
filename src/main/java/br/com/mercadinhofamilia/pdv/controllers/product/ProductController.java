package br.com.mercadinhofamilia.pdv.controllers.product;

import br.com.mercadinhofamilia.pdv.dtos.input.product.ProductInputDTO;
import br.com.mercadinhofamilia.pdv.dtos.output.product.ProductOutputDTO;
import br.com.mercadinhofamilia.pdv.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductOutputDTO> create(@RequestBody ProductInputDTO productInputDTO) {
        ProductOutputDTO productOutputDTO = productService.create(productInputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productOutputDTO);
    }
}
