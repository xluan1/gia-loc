package com.gialoc.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gialoc.springboot.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}
