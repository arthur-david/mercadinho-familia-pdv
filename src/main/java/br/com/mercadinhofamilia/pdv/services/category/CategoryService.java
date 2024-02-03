package br.com.mercadinhofamilia.pdv.services.category;

import br.com.mercadinhofamilia.pdv.dtos.input.category.CategoryInputDTO;
import br.com.mercadinhofamilia.pdv.dtos.output.category.CategoryOutputDTO;
import br.com.mercadinhofamilia.pdv.entities.category.Category;
import br.com.mercadinhofamilia.pdv.repositories.category.CategoryRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryOutputDTO create(@Valid CategoryInputDTO categoryInputDTO) {
        verifyIfAlreadyExists(categoryInputDTO.getName());

        Category category = save(new Category(categoryInputDTO));

        return new CategoryOutputDTO(category);
    }

    private void verifyIfAlreadyExists(String name) {
        Category category = categoryRepository.findByName(name);
        if (nonNull(category))
            throw new IllegalArgumentException("Categoria já cadastrada com o nome: ".concat(name));
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}
