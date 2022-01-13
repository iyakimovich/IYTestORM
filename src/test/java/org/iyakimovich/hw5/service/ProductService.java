package org.iyakimovich.hw5.service;

import okhttp3.ResponseBody;
import org.iyakimovich.hw5.dto.ProductDTO;
import retrofit2.Call;
import retrofit2.http.*;

public interface ProductService {
    @POST("products")
    Call<ProductDTO> addProduct(@Body ProductDTO createProductRequest);

    @PUT("products")
    Call<ProductDTO> updateProduct(@Body ProductDTO createProductRequest);

    @GET("products/{id}")
    Call<ProductDTO> getProduct(@Path("id") int id);

    @DELETE("products/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") int id);
}
