package br.com.mercadinhofamilia.pdv.services.product;

import br.com.mercadinhofamilia.pdv.dtos.input.product.ProductInputDTO;
import br.com.mercadinhofamilia.pdv.dtos.output.product.ProductOutputDTO;
import br.com.mercadinhofamilia.pdv.entities.product.Product;
import br.com.mercadinhofamilia.pdv.repositories.product.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductOutputDTO create(@Valid ProductInputDTO productInputDTO) {
        verifyIfAlreadyExists(productInputDTO.getName(), productInputDTO.getBarcode());

        Product product = save(new Product(productInputDTO));

        return new ProductOutputDTO(product);
    }

    private void verifyIfAlreadyExists(String name, String barcode) {
        Product product = productRepository.findByName(name);
        if (nonNull(product))
            throw new IllegalArgumentException("Produto já cadastrado com o nome: ".concat(name));

        product = productRepository.findByBarcode(barcode);
        if (nonNull(product))
            throw new IllegalArgumentException("Produto já cadastrado com o código de barra: ".concat(barcode));
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
}
