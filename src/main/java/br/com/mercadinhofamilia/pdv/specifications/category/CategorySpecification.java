package br.com.mercadinhofamilia.pdv.specifications.category;

import br.com.mercadinhofamilia.pdv.entities.category.Category;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Getter
@Setter
@AllArgsConstructor
public class CategorySpecification implements Specification<Category> {

    private String idOrName;

    @Override
    public Predicate toPredicate(@NonNull Root<Category> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (isNotBlank(idOrName)) {
            if (StringUtils.isNumeric(idOrName))
                predicates.add(criteriaBuilder.equal(root.get("id"), idOrName));
            else
                predicates.add(criteriaBuilder.like(root.get("name").as(String.class), idOrName + "%"));
        }

        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }
}
