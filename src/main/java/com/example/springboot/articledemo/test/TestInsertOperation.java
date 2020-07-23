package com.example.springboot.articledemo.test;

import com.example.springboot.articledemo.bean.Article;
import com.example.springboot.articledemo.util.DataBaseUtils;
import org.junit.Test;

import java.util.List;
import java.util.UUID;


public class TestInsertOperation {
	/**
	 * ���ԣ������²�������
	 */
	@Test
	public void insertArticle() {
		String sql = "INSERT INTO t_article(id,header,name,content,author,"
				+ "description,is_published,is_delete,create_time,update_time"
				+ ",user_id,category_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ";
		String id = UUID.randomUUID().toString(); // ����
		String header = "Java Webʵ�ü���";
		String name = "��ν�MyEclipse��Ŀ����eclipse";
		String content = "���Ǿ���������������һЩ��Դ��Ŀ�����ߴӱ�ĵط�Ǩ��һЩ��Ŀ�������������ᷢ�ֵ������ֱ������ǳ�ѧjava�϶������������⣬���Ķ�һЩ�����Ĵ�������һ���ܽᡣ";
		String author = "Jack";
		String description = "�����Ŀ����ĳ�ͻ����...";
		int isPublished = 1;
		int isDelete = 0;
		String create_time = "2016-10-19 10:43:10";
		String update_time = "2016-10-19 10:43:10";
		String userId = "319600c3-550a-4f9f-80cf-deebe2376528";
		int categoryId = 2;
		DataBaseUtils.update(sql, id, header, name, content, author, description, isPublished, isDelete, create_time,
				update_time, userId, categoryId);
		System.out.println("�����ɹ���");
	}

	@Test
	public void getArticle(){
	    String sql = "select * from t_article where id = ?";
	    Article article = DataBaseUtils.queryForBean(sql, Article.class, "5587dafa-bb4d-4642-ac07-e26677014138");
	    System.out.println(article);
	    Article article1 = DataBaseUtils.queryForBean(sql, Article.class, "68dde068-b8ee-4a3b-9032-daead02e62a3");
	    System.out.println(article1);
	}
	
	/**
	 * ��ȡ�����б�
	 */
	@Test
	public void getCategoryList(){
	    String sql = "select * from t_category where 1 = 1";
	    List list = DataBaseUtils.queryForList(sql);
	    System.out.println(list);
	}

	
}
