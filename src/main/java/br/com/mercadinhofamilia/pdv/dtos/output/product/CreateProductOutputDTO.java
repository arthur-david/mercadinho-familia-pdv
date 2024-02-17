package br.com.mercadinhofamilia.pdv.dtos.output.product;

import br.com.mercadinhofamilia.pdv.dtos.output.category.CategoryOutputDTO;
import br.com.mercadinhofamilia.pdv.entities.product.Product;
import br.com.mercadinhofamilia.pdv.enums.product.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.mercadinhofamilia.pdv.helpers.CollectionHelper.isNotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class CreateProductOutputDTO {

    private String name;
    private String barcode;
    private BigDecimal price;
    private ProductStatus status;
    private List<CategoryOutputDTO> categories;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Long> categoriesNotFound;

    public CreateProductOutputDTO(Product product, List<Long> nonExistingCategories) {
        setName(product.getName());
        setBarcode(product.getBarcode());
        setPrice(product.getPrice());
        setStatus(product.getStatus());

        if (isNotEmpty(product.getCategories()))
            setCategories(product.getCategories().stream().map(CategoryOutputDTO::new).collect(Collectors.toList()));

        setCreatedAt(product.getCreatedAt());
        setUpdatedAt(product.getUpdatedAt());
        setCategoriesNotFound(nonExistingCategories);
    }
}
