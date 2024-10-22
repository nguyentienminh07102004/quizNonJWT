package com.quiz.Convertor;

import com.quiz.Model.DTO.CategoryDTO;
import com.quiz.Model.Entity.CategoryEntity;
import com.quiz.Model.Response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryConvertor {
    private final ModelMapper modelMapper;

    public CategoryEntity dtoToEntity(CategoryDTO categoryDTO) {
        CategoryEntity category = modelMapper.map(categoryDTO, CategoryEntity.class);
        category.setRating(0.00D);
        return category;
    }

    public CategoryResponse entityToResponse(CategoryEntity categoryEntity) {
        CategoryResponse categoryResponse = modelMapper.map(categoryEntity, CategoryResponse.class);
        return categoryResponse;
    }
}
