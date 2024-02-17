package br.com.mercadinhofamilia.pdv.services.category;

import br.com.mercadinhofamilia.pdv.dtos.input.category.CreateCategoryInputDTO;
import br.com.mercadinhofamilia.pdv.dtos.input.category.UpdateCategoryInputDTO;
import br.com.mercadinhofamilia.pdv.dtos.output.category.CategoryOutputDTO;
import br.com.mercadinhofamilia.pdv.dtos.output.page.PageResultOutputDTO;
import br.com.mercadinhofamilia.pdv.entities.category.Category;
import br.com.mercadinhofamilia.pdv.repositories.category.CategoryRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryOutputDTO create(@Valid CreateCategoryInputDTO createCategoryInputDTO) {
        verifyIfAlreadyExists(createCategoryInputDTO.getName());

        Category category = save(new Category(createCategoryInputDTO));

        return new CategoryOutputDTO(category);
    }

    public PageResultOutputDTO<CategoryOutputDTO> search(String idOrName, Pageable pageable) {
        List<Category> categories = new ArrayList<>();
        Page<Category> categoryPage;

        if (StringUtils.isNumeric(idOrName)) {
            findById(Long.getLong(idOrName)).ifPresent(categories::add);
            categoryPage = new PageImpl<>(categories, pageable, 1);
        } else {
            categoryPage = findByNameContaining(idOrName, pageable);
            categories = categoryPage.getContent();
        }

        List<CategoryOutputDTO> categoryOutputDTOS = categories.stream().map(CategoryOutputDTO::new).collect(Collectors.toList());

        return new PageResultOutputDTO<>(categoryPage.getSize(), categoryPage.getTotalElements(), categoryPage.getNumber(), categoryPage.getTotalPages(), categoryOutputDTOS);
    }

    public CategoryOutputDTO update(@Valid UpdateCategoryInputDTO updateCategoryInputDTO) {
        Category category = findById(updateCategoryInputDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada, tente fazer um novo cadastro para essa categoria."));
        category.update(updateCategoryInputDTO.getName());

        return new CategoryOutputDTO(category);
    }

    public void delete(@NotNull Long id) {
        findById(id).orElseThrow(() -> new IllegalArgumentException("A categoria que você está tentando deletar não foi encontrada."));
        deleteById(id);
    }

    private void verifyIfAlreadyExists(String name) {
        Category category = findByName(name);
        if (nonNull(category))
            throw new IllegalArgumentException("Categoria já cadastrada com o nome: ".concat(name));
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Page<Category> findByNameContaining(String name, Pageable pageable) {
        return categoryRepository.findByNameContaining(name, pageable);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    private void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
