package com.quiz.Utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class Pagination {
    public Pageable pageUtil(Integer page, Integer limit, Integer pageDefault, Integer limitDefault) {
        if(page == null || page < 1) {
            page = pageDefault;
        }
        if(limit == null || limit < 1) {
            limit = limitDefault;
        }
        Pageable pageable = PageRequest.of(page - 1, limit);
        return pageable;
    }

    public Pageable pageUtil(Integer page, Integer limit) {
        if(page == null || page < 1) {
            page = 0;
        }
        if(limit == null || limit < 1) {
            limit = 3;
        }
        Pageable pageable = PageRequest.of(page, limit);
        return pageable;
    }
}
