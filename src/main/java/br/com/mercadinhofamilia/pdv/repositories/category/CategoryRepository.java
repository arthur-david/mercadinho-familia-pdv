package br.com.mercadinhofamilia.pdv.repositories.category;

import br.com.mercadinhofamilia.pdv.entities.category.Category;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    Category findByName(String name);

    @Nonnull
    @Override
    List<Category> findAllById(@Nonnull Iterable<Long> longs);
}
