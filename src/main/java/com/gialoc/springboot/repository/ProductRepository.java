package com.gialoc.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gialoc.springboot.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByCategoryid(int categoryid);
	
	List<Product> findByBrandid(int brandid);
	
	@Query("SELECT s FROM Product s WHERE name LIKE %?1%")
		List<Product> findByName(String name);
}

