package com.gialoc.springboot.repository;

import com.gialoc.springboot.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,String> {

}
