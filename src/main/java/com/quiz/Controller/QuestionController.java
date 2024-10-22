package com.quiz.Controller;

import com.quiz.Model.DTO.PaginationDTO;
import com.quiz.Model.Entity.QuestionEntity;
import com.quiz.Model.Response.QuestionResponse;
import com.quiz.Service.Category.ICategoryService;
import com.quiz.Service.Question.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/questions")
public class QuestionController {
    private final ICategoryService categoryService;
    private final IQuestionService questionService;

    @GetMapping(value = "/create")
    public ModelAndView addQuestion() {
        ModelAndView view = new ModelAndView("/admin/questions/create");
        Map<String, String> categories = categoryService.getAllCategories();
        view.addObject("listCategories", categories);
        return view;
    }

    @GetMapping(value = "/list")
    public ModelAndView indexQuestion(@RequestParam(value = "page", required = false) Integer page,
                                      @RequestParam(value = "limit", required = false) Integer limit) {
        ModelAndView view = new ModelAndView("/admin/questions/list");
        Page<QuestionResponse> pageList = questionService.getAll(page, limit);
        List<QuestionResponse> list = pageList.getContent();
        PaginationDTO pagination = PaginationDTO.builder()
                .page(pageList.getPageable().getPageNumber() + 1)
                .limit(pageList.getPageable().getPageSize())
                .totalPages(pageList.getTotalPages())
                .build();
        view.addObject("pagination", pagination);
        view.addObject("listQuestions", list);
        return view;
    }

    @GetMapping(value = "/edit-{id}")
    public ModelAndView updateQuestion(@PathVariable(value = "id") String id) {
        ModelAndView view  = new ModelAndView("/admin/questions/edit");
        QuestionEntity question = questionService.getQuestionEntityById(id);
        Map<String, String> categories = categoryService.getAllCategories();
        view.addObject("listCategories", categories);
        view.addObject("question", question);
        return view;
    }
}
