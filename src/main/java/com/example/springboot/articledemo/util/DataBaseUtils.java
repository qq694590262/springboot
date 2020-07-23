package com.example.springboot.articledemo.util;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.*;
import java.util.*;

public class DataBaseUtils {
	private static String username; // �û���
	private static String password; // ����
	private static String url; // ���ݿ���

	static {
		config("jdbc.properties");
	}

	/**
	 * �������ݿ�Ļ�����Ϣ
	 * 
	 * @return void
	 */
	public static void config(String path) {
		InputStream inputStream = DataBaseUtils.class.getClassLoader().getResourceAsStream(path);
		Properties p = new Properties();
		try {
			p.load(inputStream);
			username = p.getProperty("db.username");
			password = p.getProperty("db.password");
			url = p.getProperty("db.url");
//	        System.out.println(username);
//	        System.out.println(password+"111");
//	        System.out.println(url+"111111");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ���ݿ�����
	 * 
	 * @return Connection
	 */
	@Test
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(connection);
		return connection;
	}

	/**
	 * �ر���Դ
	 * 
	 * @param connection
	 * @param statement
	 * @param rs
	 */
	public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			e.fillInStackTrace();
		}
	}

	/**
	 * DML����
	 * 
	 * @param sql
	 * @param objects
	 */
	public static void update(String sql, Object... objects) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			statement = (PreparedStatement) connection.prepareStatement(sql);
			for (int i = 0; i < objects.length; i++) {
				statement.setObject(i + 1, objects[i]);
			}
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection, statement, null);
		}
	}

	/**
	 * ��ѯ�����ݣ�����list����
	 * 
	 * @param sql
	 * @param objects
	 * @return
	 * @throws SQLException
	 */
	public static List<Map<String, Object>> queryForList(String sql, Object... objects) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.prepareStatement(sql);
			for (int i = 0; i < objects.length; i++) {
				statement.setObject(i + 1, objects[i]);
			}

			rs = statement.executeQuery();
			while (rs.next()) {
				ResultSetMetaData resultSetMetaData = rs.getMetaData();
				int count = resultSetMetaData.getColumnCount(); // ��ȡ����
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < count; i++) {
					map.put(resultSetMetaData.getColumnName(i + 1),
							rs.getObject(resultSetMetaData.getColumnName(i + 1)));
				}
				result.add(map);
			}
			;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection, statement, rs);
		}

		return result;
	}

	/**
	 * ��ѯ�����ݣ�����map����
	 * 
	 * @param sql
	 * @param objects
	 * @return
	 * @throws SQLException
	 */
	public static Map<String, Object> queryForMap(String sql, Object... objects) throws SQLException {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> list = queryForList(sql, objects);
		if (list.size() != 1) {
			return null;
		}
		result = list.get(0);
		return result;
	}

	/**
	 * ��ѯ�����ݣ����ҷ���һ��JavaBean
	 * 
	 * @param sql
	 * @param clazz
	 * @param objects
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	public static <T> T queryForBean(String sql, Class clazz, Object... objects) {
		T obj = null;
		Map<String, Object> map = null;
		Field field = null;
		try {
			obj = (T) clazz.newInstance(); // ����һ���µ�Beanʵ��
			map = queryForMap(sql, objects); // �Ƚ����������һ��Map��
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (map == null) {
			return null;
		}
		// ����Map
		for (String columnName : map.keySet()) {
			Method method = null;
			String propertyName = StringUtils.columnToProperty(columnName); // ��������

			try {
				field = clazz.getDeclaredField(propertyName);
			} catch (NoSuchFieldException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			}
			// ��ȡJavaBean�е��ֶ�
			String fieldType = field.toString().split(" ")[1]; // ��ȡ���ֶε�����
			// System.out.println(fieldType);
			Object value = map.get(columnName);
			if (value == null) {
				continue;
			}
			/** ��ȡset����������* */
			String setMethodName = "set" + StringUtils.upperCaseFirstCharacter(propertyName);
			// System.out.println(setMethodName);
			try {
				/** ��ȡֵ������* */
				String valueType = value.getClass().getName();

				/** �鿴�����Ƿ�ƥ��* */
				if (!fieldType.equalsIgnoreCase(valueType)) {
					// System.out.println("���Ͳ�ƥ��");
					if (fieldType.equalsIgnoreCase("java.lang.Integer")) {
						value = Integer.parseInt(String.valueOf(value));
					} else if (fieldType.equalsIgnoreCase("java.lang.String")) {
						value = String.valueOf(value);
					} else if (fieldType.equalsIgnoreCase("java.util.Date")) {
						valueType = "java.util.Date";
						// ��valueת����java.util.Date
						String dateStr = String.valueOf(value);
						Timestamp ts = Timestamp.valueOf(dateStr);
						Date date = new Date(ts.getTime());
						value = date;
					}
				}

				/** ��ȡset����* */
				// System.out.println(valueType);
				method = clazz.getDeclaredMethod(setMethodName, Class.forName(fieldType));
				/** ִ��set����* */
				method.invoke(obj, value);
			} catch (Exception e) {
				e.printStackTrace();
				/** �����������������Ϊ���Ͳ�ƥ��* */
			}
		}
		// System.out.println(obj);
		return obj;
	}

}
