package com.quiz.Service.Category;

import com.quiz.Convertor.CategoryConvertor;
import com.quiz.ExceptionHandlerCustomer.DataInvalidException;
import com.quiz.Model.DTO.CategoryDTO;
import com.quiz.Model.Entity.CategoryEntity;
import com.quiz.Model.Response.CategoryResponse;
import com.quiz.Repository.CategoryRepository;
import com.quiz.Utils.Pagination;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final CategoryConvertor categoryConvertor;
    private final CategoryRepository categoryRepository;
    private final Pagination pagination;

    @Override
    public Page<CategoryResponse> getAllCategory(Integer page, Integer limit) {
        Pageable pageable = pagination.pageUtil(page, limit, 1, 3);
        Page<CategoryEntity> categoryListPage = categoryRepository.findAll(pageable);
        return categoryListPage.map(categoryConvertor::entityToResponse);
    }

    @Override
    @Transactional
    public CategoryResponse save(CategoryDTO categoryDTO) {
        if(categoryDTO.getId() != null) {
            // update category
            categoryRepository.findById(categoryDTO.getId())
                    .orElseThrow(() -> new DataInvalidException("Category is not exists!"));
        } else {
            // Thêm => set nums of rating là 0
            categoryDTO.setNumsOfRating(0L);
        }
        CategoryEntity category = categoryConvertor.dtoToEntity(categoryDTO);
        CategoryEntity categoryEntity = categoryRepository.save(category);
        return categoryConvertor.entityToResponse(categoryEntity);
    }

    @Override
    @Transactional
    public void deleteByIds(List<String> ids) {
        for(String id : ids) {
            categoryRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Category is not exists!"));
        }
        categoryRepository.deleteByIdIn(ids);
    }

    @Override
    public Map<String, String> getAllCategories() {
        Map<String, String> response = new TreeMap<>();
        categoryRepository.findAll()
                .forEach(item -> response.put(item.getCode(), item.getName()));
        return response;
    }
}
