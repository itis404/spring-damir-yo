package com.itis.test4.repository;

import com.itis.test4.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
    long countById(Long id);
}