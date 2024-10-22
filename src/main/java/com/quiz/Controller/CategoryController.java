package com.quiz.Controller;

import com.quiz.Model.DTO.PaginationDTO;
import com.quiz.Model.Response.CategoryResponse;
import com.quiz.Service.Category.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/admin/categories")
@PreAuthorize(value = "hasRole('ADMIN')")
public class CategoryController {
    private final ICategoryService categoryService;

    @GetMapping(value = "/list")
    public ModelAndView indexCategory(@RequestParam(value = "page", required = false) Integer page,
                                      @RequestParam(value = "limit", required = false) Integer limit) {
        ModelAndView modelAndView = new ModelAndView("/admin/categories/list");
        Page<CategoryResponse> categoryResponses = categoryService.getAllCategory(page, limit);
        modelAndView.addObject("listCategories", categoryResponses.getContent());
        PaginationDTO pagination = new PaginationDTO(categoryResponses.getPageable().getPageNumber() + 1, categoryResponses.getPageable().getPageSize(), categoryResponses.getTotalPages());
        modelAndView.addObject("pagination", pagination);
        return modelAndView;
    }

    @GetMapping(value = "/create")
    public ModelAndView addCategory() {
        return new ModelAndView("/admin/categories/create");
    }
}
