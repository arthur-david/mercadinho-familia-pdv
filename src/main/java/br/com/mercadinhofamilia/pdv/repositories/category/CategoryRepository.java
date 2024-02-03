package br.com.mercadinhofamilia.pdv.repositories.category;

import br.com.mercadinhofamilia.pdv.entities.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);
}
