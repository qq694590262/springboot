package com.example.springboot.articledemo.test;

import com.example.springboot.articledemo.bean.Article;
import com.example.springboot.articledemo.util.TableUtils;

public class TestMain {
    public static void main(String[] args) {
        String sql = TableUtils.getCreateTableSQl(Article.class);
//        String sql1 = TableUtils.getCreateTableSQl(User.class);
        System.out.println(sql);
//        System.out.println(sql1);
    }
}
