package com.quiz.Service.Category;

import com.quiz.Model.DTO.CategoryDTO;
import com.quiz.Model.Response.CategoryResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ICategoryService {
    Page<CategoryResponse> getAllCategory(Integer page, Integer limit);
    CategoryResponse save(CategoryDTO categoryDTO);
    void deleteByIds(List<String> ids);
    Map<String, String> getAllCategories();
}
