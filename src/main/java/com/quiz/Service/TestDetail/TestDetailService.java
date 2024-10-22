package com.quiz.Service.TestDetail;

import com.quiz.Convertor.TestDetailConvertor;
import com.quiz.ExceptionHandlerCustomer.DataInvalidException;
import com.quiz.Model.DTO.TestDetailDTO;
import com.quiz.Model.Entity.TestDetailEntity;
import com.quiz.Model.Entity.TestEntity;
import com.quiz.Model.Response.TestDetailResponse;
import com.quiz.Repository.TestDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestDetailService implements ITestDetailService {
    private final TestDetailRepository testDetailRepository;
    private final TestDetailConvertor testDetailConvertor;

    @Override
    public TestDetailResponse getTestDetailById(String id) {
        TestDetailEntity detailEntity = testDetailRepository.findById(id)
                .orElseThrow(() -> new DataInvalidException("Id is invalid!"));
        return testDetailConvertor.entityToResponse(detailEntity);
    }

    @Override
    public TestDetailResponse save(TestDetailDTO testDetailDTO) {
        TestDetailEntity testDetailEntity = testDetailConvertor.dtoToEntity(testDetailDTO);
        TestDetailEntity response = testDetailRepository.save(testDetailEntity);
        return testDetailConvertor.entityToResponse(response);
    }
}
