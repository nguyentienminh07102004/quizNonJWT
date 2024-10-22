package com.quiz.Service.TestDetail;

import com.quiz.Model.DTO.TestDetailDTO;
import com.quiz.Model.Response.TestDetailResponse;

public interface ITestDetailService {
    TestDetailResponse getTestDetailById(String id);
    TestDetailResponse save(TestDetailDTO testDetailDTO);
}
