package br.com.mercadinhofamilia.pdv.dtos.output.page;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PageResultOutputDTO<T> {

    private Integer pageSize;
    private Long totalSize;
    private Integer pageNumber;
    private Integer totalPages;
    private List<T> content;

    public PageResultOutputDTO(Integer pageSize, Long totalSize, @NotNull Integer pageNumber, Integer totalPages, List<T> content) {
        setPageSize(pageSize);
        setTotalSize(totalSize);
        setPageNumber(pageNumber + 1);
        setTotalPages(totalPages);
        setContent(content);
    }
}