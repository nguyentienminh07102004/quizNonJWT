package com.quiz.Service.Test;

import com.quiz.Convertor.TestConvertor;
import com.quiz.ExceptionHandlerCustomer.DataInvalidException;
import com.quiz.Model.DTO.TestDTO;
import com.quiz.Model.Entity.TestEntity;
import com.quiz.Model.Response.TestResponse;
import com.quiz.Repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements ITestService {
    private final TestConvertor testConvertor;
    private final TestRepository testRepository;

    @Override
    public TestResponse save(TestDTO testDTO) {
        TestEntity testEntity = testConvertor.dtoToEntity(testDTO);
        TestEntity test = testRepository.save(testEntity);
        return testConvertor.entityToResponse(test);
    }

    @Override
    public void deleteTest(List<String> ids) {
        ids.forEach(this::getTestEntityById);
        testRepository.deleteByIdIn(ids);
    }

    @Override
    public TestResponse getTestById(String id) {
        return testConvertor.entityToResponse(this.getTestEntityById(id));
    }

    @Override
    public TestEntity getTestEntityById(String id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new DataInvalidException("Test not found!"));
    }
}
