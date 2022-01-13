package org.iyakimovich.hw5.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDTO {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("products")
    private List<ProductDTO> products = new ArrayList<>();
}
