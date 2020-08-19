package com.sfac.javaSpringBoot.modules.test.repository;

import com.sfac.javaSpringBoot.modules.test.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository   //持久层注解
public interface CardRepository extends JpaRepository<Card,Integer> {
}
