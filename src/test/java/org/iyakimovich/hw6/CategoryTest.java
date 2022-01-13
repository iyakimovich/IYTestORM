package org.iyakimovich.hw6;


import db.dao.CategoriesMapper;
import db.dao.ProductsMapper;
import db.model.Categories;
import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.iyakimovich.hw5.dto.CategoryDTO;
import org.iyakimovich.hw5.service.CategoryService;
import org.iyakimovich.hw5.utils.APITestUtils;
import org.iyakimovich.hw5.utils.RetrofitUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryTest.class);
    static CategoryService categoryService;
    static final HashMap<Integer, String> categoriesMap = new HashMap<Integer, String>();

    static CategoriesMapper categoriesMapper;

    @BeforeAll
    public static void runBeforeAllTests() throws IOException {
        LOGGER.info("runBeforeAllTests() - started");

        categoryService = RetrofitUtils.getRetrofit().create(CategoryService.class);

        categoriesMap.put(1, "Food");
        categoriesMap.put(2, "Electronic");
        categoriesMap.put(3, "Furniture");

        categoriesMapper = APITestUtils.getCategoriesMapper();

        LOGGER.info("runBeforeAllTests() - done");
    }


    @SneakyThrows
    @Test
    void getCategoryByIdPositiveTest() {
        LOGGER.info("getCategoryByIdPositiveTest() - started");

        for (Map.Entry mapElement : categoriesMap.entrySet()) {
            int categoryID = ((Integer) mapElement.getKey()).intValue();
            String expectedCategoryName = (String) mapElement.getValue();

            Response<CategoryDTO> response = categoryService.getCategory(categoryID).execute();
            assertTrue(response.isSuccessful());

            int productCount = response.body().getProducts().size();
            String categoryName = response.body().getTitle();
            LOGGER.info("Category {} title is : {}", categoryID, categoryName);

            assertEquals(expectedCategoryName, categoryName);

            LOGGER.info("Amount of products in Category {}}: {}", categoryName, productCount);
            assertTrue(productCount > 0);

            Categories category = categoriesMapper.selectByPrimaryKey(Integer.valueOf(categoryID));
            categoryName = category.getTitle();
            LOGGER.info("Extracted category from ORM: {}", categoryName);
            assertEquals(expectedCategoryName, categoryName);

        }
    }

    @SneakyThrows
    @Test
    void getCategoryByIdNegativeTest() {
        LOGGER.info("getCategoryByIdNegativeTest() - started");

        Response<CategoryDTO> response = categoryService.getCategory(4).execute();

        boolean isSuccessful = response.isSuccessful();
        int httpCode = response.code();
        LOGGER.info("isSuccessful: {}, httpCode: {}", isSuccessful, httpCode);

        assertFalse(isSuccessful);
        assertTrue(httpCode == 404);

        Categories category = categoriesMapper.selectByPrimaryKey(Integer.valueOf(4));
        assertNull(category);


        LOGGER.info("getCategoryByIdNegativeTest() - stopped");
    }
}
