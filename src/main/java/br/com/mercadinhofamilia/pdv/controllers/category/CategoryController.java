package br.com.mercadinhofamilia.pdv.controllers.category;

import br.com.mercadinhofamilia.pdv.dtos.input.category.CategoryInputDTO;
import br.com.mercadinhofamilia.pdv.dtos.output.category.CategoryOutputDTO;
import br.com.mercadinhofamilia.pdv.dtos.output.page.PageResultOutputDTO;
import br.com.mercadinhofamilia.pdv.services.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryOutputDTO> create(@RequestBody CategoryInputDTO categoryInputDTO) {
        CategoryOutputDTO categoryOutputDTO = categoryService.create(categoryInputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryOutputDTO);
    }

    @GetMapping
    public ResponseEntity<PageResultOutputDTO<CategoryOutputDTO>> search(@RequestParam String idOrName, @PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        PageResultOutputDTO<CategoryOutputDTO> categories = categoryService.search(idOrName, pageable);
        return ResponseEntity.ok(categories);
    }
}
