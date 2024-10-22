package com.quiz.Convertor;

import com.quiz.Model.DTO.QuestionDTO;
import com.quiz.Model.Entity.AnswerEntity;
import com.quiz.Model.Entity.CategoryEntity;
import com.quiz.Model.Entity.QuestionEntity;
import com.quiz.Model.Response.AnswerResponse;
import com.quiz.Model.Response.QuestionResponse;
import com.quiz.Repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionConvertor {
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    public QuestionEntity dtoToEntity(QuestionDTO questionDTO) {
        QuestionEntity question = modelMapper.map(questionDTO, QuestionEntity.class);
        List<AnswerEntity> answers = questionDTO.getAnswers().stream()
                .map(item -> modelMapper.map(item, AnswerEntity.class))
                .toList();
        answers.forEach(answer -> answer.setQuestion(question));
        question.setAnswers(answers);
        CategoryEntity category = categoryRepository.findByCode(questionDTO.getCategory())
                .orElseThrow(() -> new EntityNotFoundException("Category not found!"));
        question.setCategory(category);
        return question;
    }

    public QuestionResponse entityToResponse(QuestionEntity questionEntity) {
        QuestionResponse questionResponse = modelMapper.map(questionEntity, QuestionResponse.class);
        List<AnswerResponse> answers = questionEntity.getAnswers().stream()
                .map(item -> modelMapper.map(item, AnswerResponse.class))
                .toList();
        questionResponse.setAnswers(answers);
        if(questionEntity.getCategory() != null) {
            questionResponse.setCategory(questionEntity.getCategory().getName());
        }
        return questionResponse;
    }
}
