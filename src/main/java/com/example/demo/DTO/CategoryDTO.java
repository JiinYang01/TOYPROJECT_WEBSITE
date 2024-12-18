package com.example.demo.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryDTO {

    private Long categoryId;
    private String categoryName;
    private String fileUrl;

    public CategoryDTO(Long categoryId, String categoryName, String fileUrl) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.fileUrl = fileUrl;
    }
}
