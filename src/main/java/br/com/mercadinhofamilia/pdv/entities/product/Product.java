package br.com.mercadinhofamilia.pdv.entities.product;

import br.com.mercadinhofamilia.pdv.dtos.input.product.ProductInputDTO;
import br.com.mercadinhofamilia.pdv.entities.category.Category;
import br.com.mercadinhofamilia.pdv.enums.product.ProductStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String barcode;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToMany
    private List<Category> categories;

    public Product(ProductInputDTO productInputDTO) {
        setName(productInputDTO.getName());
        setBarcode(productInputDTO.getBarcode());
        setPrice(productInputDTO.getPrice());
        setStatus(ProductStatus.AVAILABLE);
    }
}
