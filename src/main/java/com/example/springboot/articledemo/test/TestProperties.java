package com.example.springboot.articledemo.test;

import com.example.springboot.articledemo.service.ArticleService;

import java.util.List;
import java.util.UUID;


public class TestProperties {

	public static void main(String[] args) throws Exception {
//		DataBaseUtils.config("jdbc.properties");
//		Connection conn = DataBaseUtils.getConnection();
//		System.out.println(conn);

//		String id = UUID.randomUUID() + "";
//		String createTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//		DataBaseUtils.update("INSERT INTO t_user(id,username,password,sex,create_time,is_delete,address,telephone) "
//		        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",id,"张三",123456,0,createTime,0,"保密","保密");

//		DataBaseUtils.config("jdbc.properties");
//		List list = DataBaseUtils.queryForList("select * from t_user");
//		System.out.println(list);

//		返回为null，因为返回不止一条数据
//		Map map = DataBaseUtils.queryForMap("select * from t_user");
//		System.out.println(map);

//		Map map = DataBaseUtils.queryForMap("select * from t_user where username = ?","p2");
//		System.out.println(map);

//		User user = DataBaseUtils.queryForBean("select * from t_user  limit 1", User.class);
//		System.out.println(user);

//		String sql = TableUtils.getCreateTableSQl(Article.class);
//		System.out.println(sql);
		
//		String sql = TableUtils.getCreateTableSQl(User.class);
//		System.out.println(sql);
		
		ArticleService ArticleService = new ArticleService();
		List list = ArticleService.getArticlesByCategoryId(2,0,10);
		List list1 = ArticleService.getContentByArticleId(UUID.randomUUID().toString());
		System.out.println(list);
		System.out.println(list1);
		
	}
}