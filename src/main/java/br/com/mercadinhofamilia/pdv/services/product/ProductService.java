package br.com.mercadinhofamilia.pdv.services.product;

import br.com.mercadinhofamilia.pdv.dtos.input.product.ProductInputDTO;
import br.com.mercadinhofamilia.pdv.dtos.output.product.CreateProductOutputDTO;
import br.com.mercadinhofamilia.pdv.entities.category.Category;
import br.com.mercadinhofamilia.pdv.entities.product.Product;
import br.com.mercadinhofamilia.pdv.repositories.product.ProductRepository;
import br.com.mercadinhofamilia.pdv.services.category.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    public CreateProductOutputDTO create(@Valid ProductInputDTO productInputDTO) {
        List<Category> categoriesFounded = categoryService.findAllCategoriesByIds(productInputDTO.getCategories());

        verifyIfAlreadyExists(productInputDTO.getName(), productInputDTO.getBarcode());

        Product product = save(new Product(productInputDTO, categoriesFounded));

        return new CreateProductOutputDTO(product);
    }

    private void verifyIfAlreadyExists(String name, String barcode) {
        Product product = productRepository.findByNameOrBarcode(name, barcode);
        if (nonNull(product))
            throw new IllegalArgumentException(String.format("Um produto já está cadastrado com nome %s ou código de barra %s", name, barcode));
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
}
