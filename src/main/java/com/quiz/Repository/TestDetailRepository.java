package com.quiz.Repository;

import com.quiz.Model.Entity.TestDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDetailRepository extends JpaRepository<TestDetailEntity, String> {
}
