package br.com.mercadinhofamilia.pdv.dtos.input.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class CategoryInputDTO {

    @NotBlank(message = "O campo \"Nome\" não pode estar vazio")
    private String name;
}
