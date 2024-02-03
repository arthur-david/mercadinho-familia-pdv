package br.com.mercadinhofamilia.pdv.services.category;

import br.com.mercadinhofamilia.pdv.dtos.input.category.CategoryInputDTO;
import br.com.mercadinhofamilia.pdv.dtos.output.category.CategoryOutputDTO;
import br.com.mercadinhofamilia.pdv.dtos.output.page.PageResultOutputDTO;
import br.com.mercadinhofamilia.pdv.entities.category.Category;
import br.com.mercadinhofamilia.pdv.repositories.category.CategoryRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public PageResultOutputDTO<CategoryOutputDTO> search(String idOrName, Pageable pageable) {
        List<Category> categories = new ArrayList<>();
        Page<Category> categoryPage;

        if (StringUtils.isNumeric(idOrName)) {
            categoryRepository.findById(Long.getLong(idOrName)).ifPresent(categories::add);
            categoryPage = new PageImpl<>(categories, pageable, 1);
        } else {
            categoryPage = categoryRepository.findByNameContaining(idOrName, pageable);
            categories = categoryPage.getContent();
        }

        List<CategoryOutputDTO> categoryOutputDTOS = categories.stream().map(CategoryOutputDTO::new).collect(Collectors.toList());

        return new PageResultOutputDTO<>(categoryPage.getSize(), categoryPage.getTotalElements(), categoryPage.getNumber(), categoryPage.getTotalPages(), categoryOutputDTOS);
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
