package com.example.diet.model.list;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BaseListResponse<T> {

    @JsonProperty("total_elements")
    private Long totalElements;

    @JsonProperty("total_pages")
    private Long totalPages;

    @JsonProperty("page_number")
    private Long pageNumber;

    @JsonProperty("page_size")
    private Long pageSize;

    @JsonProperty("items")
    private List<T> items;
}
