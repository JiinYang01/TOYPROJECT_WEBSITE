package com.example.demo.service;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.DTO.SportsCourseDTO;
import com.example.demo.domain.Category;
import com.example.demo.domain.SportsCourse;
import com.example.demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> getList() {
        List<Category> categoryList = categoryRepository.findAll();

        return categoryList.stream()
                .map(this::EntityToDTO) // 메서드 레퍼런스 사용
                .collect(Collectors.toList());
    }

    private CategoryDTO EntityToDTO(Category entity) {
        return new CategoryDTO(entity.getCategoryId(), entity.getCategoryName());
    }

    //지인 코드



}
