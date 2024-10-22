package com.quiz.Repository;

import com.quiz.Model.Entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, String> {
    void deleteByIdIn(List<String> ids);
}
