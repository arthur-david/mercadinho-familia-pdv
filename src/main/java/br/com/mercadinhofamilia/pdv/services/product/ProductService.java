package br.com.mercadinhofamilia.pdv.services.product;

import br.com.mercadinhofamilia.pdv.dtos.input.product.ProductInputDTO;
import br.com.mercadinhofamilia.pdv.dtos.output.product.CreateProductOutputDTO;
import br.com.mercadinhofamilia.pdv.models.category.CategoryCheckOutputDTO;
import br.com.mercadinhofamilia.pdv.entities.product.Product;
import br.com.mercadinhofamilia.pdv.repositories.product.ProductRepository;
import br.com.mercadinhofamilia.pdv.services.category.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public CreateProductOutputDTO create(@Valid ProductInputDTO productInputDTO) {
        CategoryCheckOutputDTO categoryCheckOutputDTO = categoryService.verifyCategories(productInputDTO.getCategories());

        verifyIfAlreadyExists(productInputDTO.getName(), productInputDTO.getBarcode());

        Product product = save(new Product(productInputDTO, categoryCheckOutputDTO.getExistingCategories()));

        return new CreateProductOutputDTO(product, categoryCheckOutputDTO.getNonExistingCategories());
    }

    private void verifyIfAlreadyExists(String name, String barcode) {
        Product product = productRepository.findByName(name);
        if (nonNull(product))
            throw new IllegalArgumentException(String.format("O produto %s já está cadastrado.", name));

        product = productRepository.findByBarcode(barcode);
        if (nonNull(product))
            throw new IllegalArgumentException(String.format("Um produto já está cadastrado com o código de barra %s", barcode));
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
}
