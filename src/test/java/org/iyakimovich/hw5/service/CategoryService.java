package org.iyakimovich.hw5.service;

import org.iyakimovich.hw5.dto.CategoryDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoryService {
    @GET("categories/{id}")
    Call<CategoryDTO> getCategory(@Path("id") int id);
}
