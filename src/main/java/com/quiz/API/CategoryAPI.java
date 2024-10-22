package com.quiz.API;

import com.quiz.Model.DTO.CategoryDTO;
import com.quiz.Model.Response.APIResponse;
import com.quiz.Model.Response.CategoryResponse;
import com.quiz.Service.Category.ICategoryService;
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
@RequestMapping(value = "/api/admin/categories")
@RequiredArgsConstructor
@PreAuthorize(value = "hasRole('ADMIN')")
public class CategoryAPI {
    private final ICategoryService categoryService;

    @PostMapping(value = "/")
    @ResponseStatus(value = HttpStatus.CREATED)
    public APIResponse createCategory(@RequestBody CategoryDTO category) {
        CategoryResponse categoryResponse = categoryService.save(category);
        return APIResponse.builder()
                .message("CREATE SUCCESS!")
                .response(categoryResponse)
                .build();
    }

    @PutMapping(value = "/")
    @ResponseStatus(value = HttpStatus.OK)
    public APIResponse updateCategory(@RequestBody CategoryDTO category) {
        CategoryResponse categoryResponse = categoryService.save(category);
        return APIResponse.builder()
                .message("UPDATE SUCCESS!")
                .response(categoryResponse)
                .build();
    }

    @DeleteMapping(value = "/{ids}")
    @ResponseStatus(value = HttpStatus.OK)
    public APIResponse deleteCategory(@PathVariable(value = "ids") List<String> ids) {
        categoryService.deleteByIds(ids);
        return APIResponse.builder()
                .message("DELETE SUCCESS!")
                .build();
    }
}
