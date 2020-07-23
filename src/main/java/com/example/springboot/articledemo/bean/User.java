package com.example.springboot.articledemo.bean;

import com.example.springboot.articledemo.annotation.Column;
import com.example.springboot.articledemo.annotation.Table;

@Table(tableName = "t_user1")
public class User {
	// ����
	@Column(type = "varchar(100)", field = "id", primaryKey = true, defaultNull = false)
	private String id; // ����������UUID

	@Column(type = "VARCHAR(20)", field = "username")
	private String username; // �û���

	@Column(type = "VARCHAR(20)", field = "password")
	private String password; // ����

	@Column(type = "VARCHAR(60)", field = "headerPic")
	private String headerPic; // ͷ��

	@Column(type = "VARCHAR(60)", field = "email")
	private String email; // ��������

	@Column(type = "VARCHAR(2)", field = "sex")
	private Integer sex; // �Ա� 0�� 1Ů 2����

	@Column(type = "datetime", field = "create_time")
	private String createTime;// ����ʱ��

	@Column(type = "timestamp", field = "update_time")
	private String updateTime;// ������ʱ��

	@Column(type = "int(1)", field = "is_delete")
	private Integer isDelete; // ɾ��״̬ 0δɾ�� 1ɾ��

	@Column(type = "VARCHAR(200)", field = "address")
	private String address; // ��ַ

	@Column(type = "VARCHAR(11)", field = "telephone")
	private String telephone; // �绰

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHeaderPic() {
		return headerPic;
	}

	public void setHeaderPic(String headerPic) {
		this.headerPic = headerPic;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public String toString() {
		return "User [toString()=" + super.toString() + "]";
	}
	
	
}