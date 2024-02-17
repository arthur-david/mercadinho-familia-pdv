package br.com.mercadinhofamilia.pdv.models.category;

import br.com.mercadinhofamilia.pdv.entities.category.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CategoryCheckOutputDTO {

    private List<Category> existingCategories;
    private List<Long> nonExistingCategories;
}
