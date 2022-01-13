package org.iyakimovich.hw5.utils;

import db.dao.CategoriesMapper;
import db.dao.ProductsMapper;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@UtilityClass
public class APITestUtils {
    Properties props = new Properties();
    FileInputStream fis;

    private static String API_PROPS = "src/test/resources/api.properties";
    private static String MYBATIS_CONFIG = "mybatis-config.xml";

    InputStream inputStream;
    SqlSessionFactory sqlSessionFactory;
    SqlSession session;

    static {
        try {
            fis = new FileInputStream(API_PROPS);

            inputStream = Resources.getResourceAsStream(MYBATIS_CONFIG);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            session = sqlSessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @SneakyThrows
    public String getBaseUrl() {
        props.load(fis);
        return props.getProperty("url");
    }

    @SneakyThrows
    public CategoriesMapper getCategoriesMapper() {
        return session.getMapper(CategoriesMapper.class);
    }

    @SneakyThrows
    public ProductsMapper getProductsMapper() {
        return session.getMapper(ProductsMapper.class);
    }
}
