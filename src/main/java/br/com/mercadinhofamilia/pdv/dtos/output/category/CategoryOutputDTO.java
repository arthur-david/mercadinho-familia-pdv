package br.com.mercadinhofamilia.pdv.dtos.output.category;

import br.com.mercadinhofamilia.pdv.entities.category.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CategoryOutputDTO {

    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CategoryOutputDTO(Category category) {
        setName(category.getName());
        setCreatedAt(category.getCreatedAt());
        setUpdatedAt(category.getUpdatedAt());
    }
}
