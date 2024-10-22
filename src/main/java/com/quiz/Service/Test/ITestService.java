package com.quiz.Service.Test;

import com.quiz.Model.DTO.TestDTO;
import com.quiz.Model.Entity.TestEntity;
import com.quiz.Model.Response.TestResponse;

import java.util.List;

public interface ITestService {
    TestResponse save(TestDTO testDTO);
    void deleteTest(List<String> ids);
    TestResponse getTestById(String id);

    TestEntity getTestEntityById(String id);
}
