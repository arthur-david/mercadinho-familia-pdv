package br.com.mercadinhofamilia.pdv.dtos.input.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductInputDTO {

    @NotBlank(message = "O campo \"Nome\" não pode estar vazio")
    private String name;

    @NotBlank(message = "O campo \"Código de Barra\" não pode estar vazio")
    private String barcode;

    @NotNull(message = "O campo \"Preço\" não pode estar vazio")
    private BigDecimal price;

    @NotEmpty(message = "O produto precisa estar vinculado a alguma categoria.")
    private List<Long> categories;
}
