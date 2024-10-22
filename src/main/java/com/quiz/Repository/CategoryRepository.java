package com.quiz.Repository;

import com.quiz.Model.Entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
    void deleteByIdIn(List<String> ids);
    Optional<CategoryEntity> findByCode(String code);
    Optional<CategoryEntity> findByName(String category);
}
