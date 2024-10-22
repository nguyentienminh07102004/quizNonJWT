package com.quiz.Service.Question;

import com.quiz.Convertor.QuestionConvertor;
import com.quiz.ExceptionHandlerCustomer.DataInvalidException;
import com.quiz.Model.DTO.QuestionDTO;
import com.quiz.Model.Entity.QuestionEntity;
import com.quiz.Model.Response.QuestionResponse;
import com.quiz.Repository.QuestionRepository;
import com.quiz.Utils.Pagination;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements IQuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionConvertor questionConvertor;
    private final Pagination pagination;

    @Override
    @Transactional
    public QuestionResponse save(QuestionDTO questionDTO) {
        if(questionDTO.getId() != null) {
            // update question
            questionRepository.findById(questionDTO.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Question not found!"));
        }
        QuestionEntity question = questionConvertor.dtoToEntity(questionDTO);
        questionRepository.save(question);
        return questionConvertor.entityToResponse(question);
    }

    @Override
    @Transactional
    public void deleteByIds(List<String> ids) {
        ids.forEach(item -> questionRepository.findById(item)
                .orElseThrow(() -> new EntityNotFoundException("Question not found!")));
        questionRepository.deleteByIdIn(ids);
    }

    @Override
    public Page<QuestionResponse> getAll(Integer page, Integer limit) {
        Pageable pageable = pagination.pageUtil(page, limit, 1, 3);
        Page<QuestionEntity> questions = questionRepository.findAll(pageable);
        return questions.map(questionConvertor::entityToResponse);
    }

    @Override
    public QuestionEntity getQuestionEntityById(String id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new DataInvalidException("Question not found!!"));
    }
}
