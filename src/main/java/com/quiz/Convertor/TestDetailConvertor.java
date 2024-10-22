package com.quiz.Convertor;

import com.quiz.ExceptionHandlerCustomer.DataInvalidException;
import com.quiz.Model.DTO.TestDetailDTO;
import com.quiz.Model.Entity.AnswerEntity;
import com.quiz.Model.Entity.AnswerSelectedEntity;
import com.quiz.Model.Entity.QuestionEntity;
import com.quiz.Model.Entity.TestDetailEntity;
import com.quiz.Model.Entity.TestEntity;
import com.quiz.Model.Response.TestDetailResponse;
import com.quiz.Repository.TestDetailRepository;
import com.quiz.Repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TestDetailConvertor {
    private final ModelMapper modelMapper;
    private final TestRepository testRepository;

    public TestDetailEntity dtoToEntity(TestDetailDTO testDetailDTO) {
        TestDetailEntity testDetailEntity = modelMapper.map(testDetailDTO, TestDetailEntity.class);
        Map<String, String> answerSelected = testDetailDTO.getResults();
        TestEntity testEntity = testRepository.findById(testDetailDTO.getTestId())
                .orElseThrow(() -> new DataInvalidException("Test not found"));
        List<List<AnswerEntity>> answerTrue = testEntity.getQuestions().stream()
                .map(QuestionEntity::getAnswers)
                .map(answers -> answers.stream().filter(AnswerEntity::getIsTrue).toList())
                .toList();
        return testDetailEntity;
    }

    public TestDetailResponse entityToResponse(TestDetailEntity detailEntity) {
        TestDetailResponse testDetailResponse = modelMapper.map(detailEntity, TestDetailResponse.class);
        Map<String, String> results = new HashMap<>();
        detailEntity.getAnswerSelected()
                .forEach(answer -> results.put(answer.getId(), answer.getSelected()));
        testDetailResponse.setResults(results);
        return testDetailResponse;
    }
}
