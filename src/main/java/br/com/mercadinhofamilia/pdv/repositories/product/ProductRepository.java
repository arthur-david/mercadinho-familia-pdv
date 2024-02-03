package br.com.mercadinhofamilia.pdv.repositories.product;

import br.com.mercadinhofamilia.pdv.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);
    Product findByBarcode(String barcode);
}
