package com.quiz.API;

import com.quiz.Model.DTO.QuestionDTO;
import com.quiz.Model.Response.APIResponse;
import com.quiz.Model.Response.QuestionResponse;
import com.quiz.Service.Question.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${api.prefix}${admin.prefix}/questions")
@PreAuthorize(value = "hasRole('ADMIN')")
public class QuestionAPI {
    private final IQuestionService questionService;

    @PostMapping(value = "/")
    @ResponseStatus(value = HttpStatus.CREATED)
    public APIResponse createQuestion(@RequestBody QuestionDTO questionDTO) {
        QuestionResponse response = questionService.save(questionDTO);
        return APIResponse.builder()
                .message("CREATE QUESTION SUCCESS")
                .response(response)
                .build();
    }

    @PutMapping(value = "/")
    @ResponseStatus(value = HttpStatus.OK)
    public APIResponse updateQuestion(@RequestBody QuestionDTO questionDTO) {
        QuestionResponse response = questionService.save(questionDTO);
        return APIResponse.builder()
                .message("UPDATE QUESTION SUCCESS")
                .response(response)
                .build();
    }

    @DeleteMapping(value = "/{ids}")
    @ResponseStatus(value = HttpStatus.OK)
    public APIResponse deleteQuestion(@PathVariable(value = "ids") List<String> ids) {
        questionService.deleteByIds(ids);
        return APIResponse.builder()
                .message("DELETE QUESTION SUCCESS")
                .build();
    }
}
