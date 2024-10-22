package com.quiz.Repository;

import com.quiz.Model.Entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, String> {
    void deleteByIdIn(List<String> ids);
}
