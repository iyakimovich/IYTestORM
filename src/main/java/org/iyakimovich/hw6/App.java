package org.iyakimovich.hw6;

import db.dao.CategoriesMapper;
import db.model.Categories;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;


public class App
{
    //public static String CONFIG = "src/main/resources/mybatis-config.xml";
    static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws IOException {
        LOGGER.info("Test");
/*
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        CategoriesMapper categoriesMapper =
                sqlSessionFactory.openSession().getMapper(CategoriesMapper.class);

        Categories categories = categoriesMapper.selectByPrimaryKey(3);
        System.out.println("Title: " + categories.getTitle());

        categories = categoriesMapper.selectByPrimaryKey(4);
        System.out.println("Title: " + categories.getTitle());


 */
    }


}

