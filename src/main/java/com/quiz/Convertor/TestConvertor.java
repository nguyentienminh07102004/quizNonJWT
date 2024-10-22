package com.quiz.Convertor;

import com.quiz.Model.DTO.TestDTO;
import com.quiz.Model.Entity.QuestionEntity;
import com.quiz.Model.Entity.TestEntity;
import com.quiz.Model.Response.QuestionResponse;
import com.quiz.Model.Response.TestResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TestConvertor {
    private final ModelMapper modelMapper;
    private final QuestionConvertor questionConvertor;

    public TestEntity dtoToEntity(TestDTO testDTO) {
        TestEntity testEntity = modelMapper.map(testDTO, TestEntity.class);
        List<QuestionEntity> questionEntities = testDTO.getQuestions().stream()
                .map(questionConvertor::dtoToEntity)
                .toList();
        testEntity.setQuestions(questionEntities);
        return testEntity;
    }

    public TestResponse entityToResponse(TestEntity test) {
        TestResponse testResponse = modelMapper.map(test, TestResponse.class);
        List<QuestionEntity> questionEntities = test.getQuestions();
        List<QuestionResponse> questionResponses = questionEntities.stream()
                .map(questionConvertor::entityToResponse)
                .toList();
        testResponse.setQuestions(questionResponses);
        return testResponse;
    }
}
