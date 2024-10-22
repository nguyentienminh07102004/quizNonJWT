package com.quiz.Service.Question;

import com.quiz.Model.DTO.QuestionDTO;
import com.quiz.Model.Entity.QuestionEntity;
import com.quiz.Model.Response.QuestionResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IQuestionService {
    QuestionResponse save(QuestionDTO questionDTO);
    void deleteByIds(List<String> ids);
    Page<QuestionResponse> getAll(Integer page, Integer limit);
    QuestionEntity getQuestionEntityById(String id);
}
