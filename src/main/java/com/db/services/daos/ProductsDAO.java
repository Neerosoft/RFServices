package com.db.services.daos;
import org.springframework.data.jpa.repository.JpaRepository;
import com.db.services.entitys.Products;

public interface ProductsDAO extends JpaRepository<Products,Long> {

}
