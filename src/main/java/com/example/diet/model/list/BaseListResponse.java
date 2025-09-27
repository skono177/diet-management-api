package com.example.diet.model.list;

import java.util.List;

import com.example.diet.model.validation.ValidationErrResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

// @formatter:off
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
// @formatter:on
public class BaseListResponse<T> {

    @JsonProperty("total_elements")
    private Long totalElements;

    @JsonProperty("total_pages")
    private Long totalPages;

    @JsonProperty("page_size")
    private Long pageSize;

    @JsonProperty("page_number")
    private Long pageNumber;

    @JsonProperty("items")
    private List<T> items;

    @JsonProperty("errors")
    private List<ValidationErrResponse> errors;
}
