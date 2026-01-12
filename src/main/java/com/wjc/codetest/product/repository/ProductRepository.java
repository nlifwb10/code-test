package com.wjc.codetest.product.repository;

import com.wjc.codetest.product.model.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

   // Page<Product> findAllByCategoryContaining(String name, Pageable pageable);
    
    // 수정시 Check 로직 자기자신은 제외
    boolean existsByCategoryAndNameAndIdNot(String category, String name, Long id);
    
    // 등록시 저장로직
    boolean existsByCategoryAndName(String category, String name);

    @Query("SELECT DISTINCT p.category FROM Product p")
    List<String> findDistinctCategories();
}
