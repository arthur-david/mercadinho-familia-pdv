package br.com.mercadinhofamilia.pdv.dtos.input.category;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateCategoryInputDTO {

    @NotBlank(message = "O campo \"Nome\" não pode estar vazio")
    private String name;
}
